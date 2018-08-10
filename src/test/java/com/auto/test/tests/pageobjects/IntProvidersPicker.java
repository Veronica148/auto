package com.auto.test.tests.pageobjects;

import com.auto.common.models.intProvider.IntProvider;
import com.auto.common.models.intProvider.IntTVECredential;
import com.auto.common.models.intProvider.ProvidersType;
import com.auto.common.utils.Config;
import com.auto.common.utils.JsonUtils;
import com.auto.test.tests.SimpleTest;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by veronica_lapunka on 8/2/18.
 */
public class IntProvidersPicker {

    private static final Logger logger = Logger.getLogger(IntProvidersPicker.class);

    private static final Map<String, IntProvider> providers;

    static {
        String json;
        InputStream is = Thread.currentThread().getContextClassLoader()
                .getResourceAsStream(Config.ConfigProps.INT_TVE_CREDENTIAL_PATH_ANDROID);
        if (is == null) {
            throw new RuntimeException(
                    "Resource data is unavailable: " + Config.ConfigProps.INT_TVE_CREDENTIAL_PATH_ANDROID);
        }
        try {
            json = IOUtils.toString(is);
        } catch (IOException e) {
            throw new RuntimeException("Reading resource data failed: " + e.getMessage());
        }

        ProvidersType providerList = JsonUtils.fromJson(json, ProvidersType.class);
        providers = Collections.synchronizedMap(new HashMap<String, IntProvider>());
        providerList.getProviders().forEach(provider -> providers.put(provider.mvpd_id(), provider));
    }

    public static Collection<IntProvider> getAllProviders() {
        return providers.values();
    }

    public String logInWithProviderForLocale(String providerMVPD, String locale, String brand, String env, Boolean... isSuccessScreenShouldBe) {

        IntProvider provider = providers.get(providerMVPD);

        String username = null, password = null, mvpdID = null;
        for (IntTVECredential credential : provider.getAuthNAuthZCredential()) {
            if (credential.getLocale().equals(locale) && credential.isValidFor(env) && credential.getBrand().equals(brand)) {
                username = credential.getLogin();
                password = credential.getPassword();
                if (credential.getMvpd_id() == null) {
                    mvpdID = provider.mvpd_id();
                } else {
                    mvpdID = credential.getMvpd_id();
                }
                break;
            }
        }
        return login(provider, username, password, mvpdID, locale, env, isSuccessScreenShouldBe);
    }

    private String login(IntProvider provider, String username, String password, String mvpdID, String locale, String env, Boolean[] isSuccessScreenShouldBe) {
        String str = mvpdID + " " + username + "  "+ password + " " + provider.getPasswordFieldAndroid();
        logger.info("login str: " + str);
        return str;
    }

}
