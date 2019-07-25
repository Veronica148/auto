package com.auto.common.utils;

import org.testng.Assert;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import java.io.File;

/**
 * Created by veronica_lapunka on 8/1/18.
 */
public class Config {

    private static String SYSTEM_TEST_PROP = "system.test.";
    private static String USER_DIR_PROP = "user.dir";

    public interface GroupProps {
        String FULL = "Full";
        String SMOKE = "Smoke";
        String BROKEN = "Broken";
        String DEBUG = "Debug";
        String UNSTABLE = "Unstable";
    }

    public interface ConfigProps {
        // RUNTIME CONFIG
        Boolean RUN_AS_FACTORY = Config.getBoolean("RunAsFactory");
        Boolean PREINSTALLED = Config.getBoolean("Preinstalled");
        String APP_NAME = Config.getString("AppName");
        String OS = Config.getString("OS");
        String BROWSER = Config.getString("Browser");
        String BRANCH = Config.getString("Branch");
        Integer DEBUG_PROXY_PORT = Config.getInt("DebugProxyPort");


        String DEVICE_CATEGORY = Config.getString("DeviceCategory");
        Boolean UPLOAD_REPORT_JENKINS = Config.getBoolean("UploadReportToJenkins");
        Boolean SEND_REPORT_AUTOEMAILS = Config.getBoolean("SendReportAutoEmails");
        Boolean RERUN_ON_FAILURE = Config.getBoolean("ReRunOnFailure");
        Integer RERUN_ON_FAILURE_COUNT = Config.getInt("ReRunOnFailureCount");
        String SEND_REPORT_EMAIL_ADDRESS = Config.getString("SendReportEmailAddress");

        // APP CONFIG
        String ELEMENT_FILE_PATH = Config.getFilePath("PathToElements");
        String TVEDEMO_ANDROID_APP_PACKAGE = Config.getString("TVEDemoAndroidAppPackage");
        String TVEDEMO_ANDROID_LAUNCH_ACTIVITY = Config.getString("TVEDemoAndroidLaunchActivity");
        String APPLICATION_TITLE = Config.getString("ApplicationTitle");
        String BUILD_NUMBER = Config.getString("BuildNumber");
        String APP_URL = Config.getString("AppUrl");

        // REPORT CONFIG
        String EMAIL_SENDER_ADDRESS = Config.getString("EmailSenderAddress");
        String SCREENSHOT_FILE_PATH = Config.getFilePath("PathToScreenshots");
        String ALLURE_RESULTS_ANDROID_PATH = Config.getFilePath("PathToAllureAndroidResults");
        String ALLURE_RESULTS_WEB_PATH = Config.getFilePath("PathToAllureWebResults");
        String ALLURE_ANDROID_LOGO_PATH = Config.getFilePath("PathToAllureAndroidLogos");
        String ALLURE_WEB_LOGO_PATH = Config.getFilePath("PathToAllureWebLogos");

        Boolean ATTACH_APP_XMLTREE_LOGS = Config.getBoolean("AttachAppXMLTreeLogs");
        Boolean ATTACH_APP_PROXY_LOGS = Config.getBoolean("AttachAppProxyLogs");

        String JENKINS_IOS_WORKSPACE_FILE_PATH = Config.getString("PathToiOSJenkinsWorkspace");
        String JENKINS_ANDROID_WORKSPACE_FILE_PATH = Config.getString("PathToAndroidJenkinsWorkspace");
        String JENKINS_IOS_WORKSPACE_URL = Config.getString("iOSJenkinsWorkspaceUrl");
        String JENKINS_ANDROID_WORKSPACE_URL = Config.getString("AndroidJenkinsWorkspaceUrl");
        String S3_BUCKET_NAME = Config.getString("S3BucketName");

        // WEBDRIVER CONFIG
        Integer MAX_WAIT_TIME = Config.getInt("WaitForWaitTime");
        String SERVER_COMMAND_TIMEOUT = Config.getString("ServerCommandTimeout");
        Integer POLLING_TIME = Config.getInt("PollingTime");

        // TVE DATA
        String TVE_CREDENTIAL_PATH = Config.getString("TveDataPath");
        String DOMESTIC_TVE_CREDENTIAL_PATH = Config.getString("DomesticTveDataPath");

        String INT_TVE_CREDENTIAL_PATH_ANDROID = Config.getString("IntTveDataPathAndroid");

    }

    public interface StaticProps {
        String IOS = "iOS";
        String IOS_10 = "iOS_10";
        String ANDROID = "Android";
        String WEB = "Web";
    }

    public static String getFilePath(final String parameterName) {
        String parameterValue = System.getProperty(SYSTEM_TEST_PROP + parameterName.toLowerCase());
        if (parameterValue != null) {
            return System.getProperty(USER_DIR_PROP) + parameterValue.replace("/", File.separator);
        }
        String propFromXML = getXPathValueFromFile(getConfigFileLocation(), getParameterValue(parameterName));
        System.setProperty(SYSTEM_TEST_PROP + parameterName.toLowerCase(), propFromXML);
        return System.getProperty(USER_DIR_PROP) + propFromXML.replace("/", File.separator);
    }

    public static Integer getInt(final String parameterName) {
        String parameterValue = System.getProperty(SYSTEM_TEST_PROP + parameterName.toLowerCase());
        if (parameterValue != null) {
            return Integer.parseInt(parameterValue);
        }
        String propFromXML = getXPathValueFromFile(getConfigFileLocation(), getParameterValue(parameterName));
        System.setProperty(SYSTEM_TEST_PROP + parameterName.toLowerCase(), propFromXML);
        return Integer.parseInt(propFromXML);
    }

    public static Boolean getBoolean(final String parameterName) {
        String parameterValue = System.getProperty(SYSTEM_TEST_PROP + parameterName.toLowerCase());
        if (parameterValue != null) {
            return Boolean.valueOf(parameterValue);
        }
        String propFromXML = getXPathValueFromFile(getConfigFileLocation(), getParameterValue(parameterName));
        System.setProperty(SYSTEM_TEST_PROP + parameterName.toLowerCase(), propFromXML);
        return Boolean.valueOf(propFromXML);
    }

    public static String getString(final String parameterName) {
        String parameterValue = System.getProperty(SYSTEM_TEST_PROP + parameterName.toLowerCase());
        if (parameterValue != null) {
            return parameterValue;
        }
        String propFromXML = getXPathValueFromFile(getConfigFileLocation(), getParameterValue(parameterName));
        System.setProperty(SYSTEM_TEST_PROP + parameterName.toLowerCase(), propFromXML);
        return propFromXML;
    }

    private static String getConfigFileLocation() {
        String fileLoc = System.getProperty(USER_DIR_PROP) + "/src/test/resources/TestNGSuiteConfig.xml";
        return fileLoc.replace("/", File.separator);
    }

    private static String getParameterValue(String parameterName) {
        return "//parameter[@name='" + parameterName + "']/@value";
    }

    private static String getXPathValueFromFile(final String fileLocation, final String xpathQuery) {
        String value = null;
        try {
            File file = new File(fileLocation);
            DocumentBuilderFactory xmlFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = xmlFactory.newDocumentBuilder();
            Document xmlDoc = docBuilder.parse(file);
            XPathFactory xpathFact = XPathFactory.newInstance();
            XPath xpath = xpathFact.newXPath();
            value = (String) xpath.evaluate(xpathQuery, xmlDoc, XPathConstants.STRING);
        } catch (Exception e) {
            Assert.fail("Failed to retrieve configuration value from Config File at '" + fileLocation
                    + "' with xpath query '" + xpathQuery + "'.", e);
        }
        return value;
    }

}
