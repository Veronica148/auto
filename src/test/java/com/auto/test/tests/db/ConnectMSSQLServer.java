package com.auto.test.tests.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ConnectMSSQLServer {

    public static void main(String[] args) {

        String userName = "STEINPILZ\\veronica.lapunka";
        String password = "";
        String url = "jdbc:sqlserver://10.0.0.23:1435;database=Flims;integratedSecurity=true";

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection connection = DriverManager.getConnection(url, userName, password);
            System.out.println("connected");

            Statement statement = connection.createStatement();
            String queryString = "select top (10) * from Flims.UFLRAUFT where AUFT_MAND_ID = 2";
            ResultSet rs = statement.executeQuery(queryString);
            while (rs.next()) {
                System.out.println(rs.getString(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
