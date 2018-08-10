package com.auto.test.tests;

import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by veronica_lapunka on 8/1/18.
 */
public class MainSample {


    private static final Logger LOGGER = Logger.getLogger(MainSample.class);


   public static void main(String args[]){
       System.out.println("testing resources....");

       MainSample provider = new MainSample();

       List<String> lines = provider.getFileLines("rqsDomesticInfo.txt");

       System.out.println("==== " + lines.size());
       LOGGER.debug("debug...");
       LOGGER.info("info...");
       LOGGER.fatal("fatal....");
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
