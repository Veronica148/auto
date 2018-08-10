package com.auto.common.utils;

import com.auto.common.models.intProvider.BaseProvider;
import com.auto.test.tests.support.IntFailures;
import org.apache.commons.io.*;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by veronica_lapunka on 8/7/18.
 */
public class VelocityUtils {

    public static String getString(String templateName, VelocityContext vContext) {
        BufferedWriter bw = null;
        try {
            Velocity.init();
            Template template = Velocity.getTemplate(templateName);
            StringWriter sw = new StringWriter();
            bw = new BufferedWriter(sw);
            template.merge(vContext, bw);
            bw.flush();
            return sw.toString();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    throw new RuntimeException(e.getMessage(), e);
                }
            }
        }
    }

    public static void generateReport(String reportFileName, String outputDirectory) {
        VelocityContext vc = new VelocityContext();
        vc.put("reportBodyList", buildReportBody());
        try {
            org.apache.commons.io.FileUtils.writeStringToFile(
                    new File(outputDirectory, reportFileName), VelocityUtils.getString("int_report.html.vm", vc));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<String> buildReportBody() {
        List<String> report = new ArrayList<>();

        report.add("<table class=\"table_with_caption\">");
        report.add("<thead>");
        report.add("<tr>");
        report.add("<th class=\"w20\"> Locale</td>");
        report.add("<th class=\"w10\"> Brand </td>");
        report.add("<th class=\"w30\"> Provider Name </td>");
        report.add("<th class=\"w20\"> Environment</td>");
        report.add("<th class=\"w20\"> Device Type </td>");
        report.add("</tr>");
        report.add("</thead>");
        report.add("<tbody>");

        for (BaseProvider failure : IntFailures.getFailedBaseProviders()) {
            if (failure != null ) {
                report.add("<tr>");
                report.add(getRow(failure.getLocale()));
                report.add(getRow(failure.getBrand()));
                report.add(getRow(failure.getProviderName()));
                report.add(getRow(failure.getEnvironment()));
                report.add(getRow(failure.getDevice()));
                report.add("</tr>");
            }
        }

        return report;
    }

    private static String getRow(String cellValue) {
        return "<td style=\"border-style: solid; border-width: 1px; border-color: black; padding:.10pt .10pt .10pt .10pt\">\n" +
                "<p class=\"MsoNormal\"><span style=\"font-size:9.5pt\">" + cellValue + "<span class=\"apple-converted-space\">&nbsp;</span></span></p>\n" +
                "</td>";
    }
}