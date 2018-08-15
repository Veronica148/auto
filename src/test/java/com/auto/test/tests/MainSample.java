package com.auto.test.tests;

import com.auto.common.models.provider.MvpdList;
import com.auto.common.models.provider.ProviderInfo;
import com.auto.common.models.provider.ProvidersList;
import com.auto.common.utils.JsonUtils;
import org.apache.log4j.Logger;
import org.testng.asserts.SoftAssert;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

/**
 * Created by veronica_lapunka on 8/1/18.
 */
public class MainSample {


    private static final Logger LOGGER = Logger.getLogger(MainSample.class);


   public static void main(String args[]){
       MainSample provider = new MainSample();

       List<String> lines = provider.getFileLines("rqsDomesticInfo.txt");

       System.out.println("==== " + lines.size());
       LOGGER.debug("debug...");
       LOGGER.info("info...");
       LOGGER.fatal("fatal....");

       SoftAssert softAssert = new SoftAssert();

       //rqsDomesticInfo

       HttpClient client = new DefaultHttpClient();

       List<ProvidersList> providersListRS = new ArrayList<ProvidersList>();
       int providerInfoRQsSize = lines.size();
       LOGGER.info("providerListRQsSize: " + providerInfoRQsSize);
       for (int i = 0; i < providerInfoRQsSize; i++) {
           try {
               HttpGet getRq = new HttpGet(lines.get(i));
               HttpResponse response = client.execute(getRq);
               HttpEntity resEntity = response.getEntity();
               String responseStr = EntityUtils.toString(resEntity).trim();
               ProvidersList providerList = JsonUtils.fromJson(responseStr, ProvidersList.class);
               providersListRS.add(providerList);
           } catch (IOException e) {
               e.printStackTrace();
           }
       }

       int rqAmountNumber = 0;
       for (ProvidersList providerInfo : providersListRS) {
           LOGGER.info("Checks for rs for rq: " + lines.get(rqAmountNumber));
           String newStrForUrl = lines.get(rqAmountNumber);

           String part1 = newStrForUrl.substring(newStrForUrl.lastIndexOf("v3") + 2).substring(0,newStrForUrl.substring(newStrForUrl.lastIndexOf("v3") + 2).indexOf("en_US") - 1);
           String part2 = newStrForUrl.substring(newStrForUrl.indexOf('?'));
           System.out.println("part1: " + part1);
           System.out.println("part2: " + part2);

           rqAmountNumber++;
           List<MvpdList> secondaryList = providerInfo.getSecondaryList();
           LOGGER.info("=secondaryList== size: " + secondaryList.size());
           List<MvpdList> providersList = providerInfo.getProvidersList();
           LOGGER.info("=providersList= size: " + providersList.size());

           List<MvpdList> primaryList = providerInfo.getPrimaryList();
           LOGGER.info("=primaryList=== size: " + primaryList.size());
           Map<String, List<MvpdList>> provMap = new HashMap<String, List<MvpdList>>();
           provMap.put("secondaryList", secondaryList);
           provMap.put("providersList", providersList);
           provMap.put("primaryList", primaryList);

           softAssert.assertTrue(!providersList.isEmpty(), "providersList is empty: " + providerInfo);
           softAssert.assertTrue(!primaryList.isEmpty(), "primaryList is empty: " + providerInfo);

           LOGGER.info("Map size: " + provMap.size());
           Iterator<Map.Entry<String, List<MvpdList>>> entries = provMap.entrySet().iterator();

           HashSet<String> uniqueIds = new HashSet<String>();
           while (entries.hasNext()) {
               Map.Entry<String, List<MvpdList>> entry = entries.next();
               LOGGER.info("Checking " + entry.getKey());

               List<MvpdList> currentList = entry.getValue();
               int listSize = currentList.size();
               for (int j = 0; j < listSize; j++) {
                   uniqueIds.add(currentList.get(j).getId());
               }
               LOGGER.info("amount of unique ids: " + uniqueIds.size());
           }

           Iterator<String> iterator = uniqueIds.iterator();
           List<String> providerInfoRQs = new ArrayList<String>();

           //https://xbox.mtvnservices-d.mtvi.com/tveauth/v3/ios/mtv/en_US/providerInfo/ATT?deviceType=tablet
           while(iterator.hasNext()) {
               String id = iterator.next();
               providerInfoRQs.add(String.format("https://xbox.mtvnservices-q.mtvi.com/tveauth/v3%s/en_US/providerInfo/%s%s", part1, id ,part2));
               //560+ urls added, then parse responses
           }

           List<ProviderInfo> responcesProviderInfo = new ArrayList<ProviderInfo>();
           for (String url : providerInfoRQs) {
               //sending rqs
               try {
                   HttpGet getRq = new HttpGet(url);
                   HttpResponse response = client.execute(getRq);
                   HttpEntity resEntity = response.getEntity();
                   String responseStr = EntityUtils.toString(resEntity).trim();
                   ProviderInfo rs = JsonUtils.fromJson(responseStr, ProviderInfo.class);
                   responcesProviderInfo.add(rs);
               } catch (IOException e) {
                   System.out.println(" Rq for " + url + "wasn't sent'");
                   e.printStackTrace();
               }
           }
           LOGGER.info("all requests sent");
           for (ProviderInfo rss : responcesProviderInfo) {
               try {
                   softAssert.assertFalse(rss.getMvpdList().getBlackLogoURL().isEmpty(), "BlackLogoURL is empty: " + rss.getMvpdList());
                   softAssert.assertNotNull(rss.getMvpdList().getBlackLogoURL(), "BlackLogoURL is null: " + rss.getMvpdList());
                   softAssert.assertFalse(rss.getMvpdList().getWhiteLogoURL().isEmpty(), "WhiteLogoURL is empty: " + rss.getMvpdList());
                   softAssert.assertNotNull(rss.getMvpdList().getWhiteLogoURL(), "WhiteLogoURL is null: " + rss.getMvpdList());
                   softAssert.assertFalse(rss.getMvpdList().getColorLogoURL().isEmpty(), "ColorLogoURL is empty: " + rss.getMvpdList());
                   softAssert.assertNotNull(rss.getMvpdList().getColorLogoURL(), "ColorLogoURL is null: " + rss.getMvpdList());
               } catch (Exception e){
                   LOGGER.info("Smth is null or empty for id: " + rss.getMvpdList().getId());
               }
           }
           System.out.println("responcesProviderInfo size: " + responcesProviderInfo.size());
       }
       softAssert.assertAll();
   }


    private List<String> getFileLines(String fileName){
        List<String> lines = new ArrayList<String>();

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());

        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
            String line = null;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return lines;
    }
}
