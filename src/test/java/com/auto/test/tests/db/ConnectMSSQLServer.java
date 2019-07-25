package com.auto.test.tests.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ConnectMSSQLServer {
    //vm:-Djava.library.path="d:\Veronica\Work\db"
    //server name 10.0.0.23, 1435
    //username: STEINPILZ\veronica.lapunka
    public static void main(String[] args) {

        String userName = "STEINPILZ\\veronica.lapunka";
        String password = "3+v!f4$k&ML\"TzX3";
//        String url = "jdbc:sqlserver://10.0.0.23:1435;databaseName=Flims;integratedSecurity=true";
        String url = "jdbc:sqlserver://10.0.0.23:1435;database=Flims;integratedSecurity=true";

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection connection = DriverManager.getConnection(url, userName, password);
            System.out.println("connected");

            Statement statement = connection.createStatement();
            String queryString = "select * from UFLRFPPR where FPPR_MAND_ID=2";
            ResultSet rs = statement.executeQuery(queryString);
            while (rs.next()) {
                System.out.println(rs.getString(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
