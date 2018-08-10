package com.auto.test.tests.support;

import com.auto.common.models.intProvider.IntProvider;
import com.auto.common.models.intProvider.IntTVECredential;
import com.auto.common.utils.Config;
import com.auto.test.tests.pageobjects.IntProvidersPicker;
import org.testng.annotations.DataProvider;

import java.util.*;

/**
 * Created by veronica_lapunka on 8/2/18.
 */
public class DataProviderManager {

    @DataProvider()
    static public Object[][] defaultDataProvider() {

        return new Object[][]{new Object[]{Config.ConfigProps.DEVICE_CATEGORY}};
    }

    @DataProvider()
    private static Iterator<Object[]> intTveData() {
        Collection<Object[]> results = new HashSet<>();
        Collection<IntProvider> intProvidersWithAuthZCreds = new HashSet<IntProvider>();

        for (IntProvider provider : IntProvidersPicker.getAllProviders()) {
            if (provider.getAuthNAuthZCredential() != null) {
                if (Config.ConfigProps.OS.equals(Config.StaticProps.ANDROID)) {
                    if (provider.getPasswordFieldAndroid() != null) {
                        intProvidersWithAuthZCreds.add(provider);
                    }
                } else if (provider.getPasswordFieldIOS() != null) {
                    intProvidersWithAuthZCreds.add(provider);
                }
            }
        }

        for (IntProvider p : intProvidersWithAuthZCreds) {
            for (IntTVECredential c : p.getAuthNAuthZCredential()) {
                for (String environment : c.getEnvironments()) {
                    for (String brand : c.getBrands()) {
                        for (Object[] object : defaultDataProvider()) {
                            results.add(new Object[]{object[0], p.getName(), p.mvpd_id(), c.getLocale(), brand, environment});
                        }
                    }
                }
            }
        }
        results = getRandomObjects(results, 4);

        return results.iterator();
    }

    private static Collection<Object[]> getRandomObjects(Collection from, int amount) {
        Collection<Object[]> randResults = new HashSet<>();

        List<Object> listFromCollection = new ArrayList<Object>(from);
        Collections.shuffle(listFromCollection);
        Iterator<Object> iterator = new ArrayList<Object>(listFromCollection).iterator();
        while (iterator.hasNext() && amount > 0){
            randResults.add((Object[]) iterator.next());
            amount--;
        }

        return randResults;
    }

}
