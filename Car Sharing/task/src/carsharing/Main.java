package carsharing;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main {
    static protected String databaseName = "carsharing";
    public static void main(String[] args) {
        for (int i = 0; i < args.length; i++) {
            if (args[i].equalsIgnoreCase("-databasefilename")) {
                databaseName = args[i + 1];
            }
        }
        createTables();
        Thread run = new Run();
        run.start();
        try {
            run.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //  create start tables, if not exists
    static void createTables() {
        JDBC database = new JDBC(databaseName);
        try (Connection con = database.getConnection()) {
            Statement stmt = con.createStatement();
            String sql = "CREATE table if not exists COMPANY " + "(id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT, " +
                    " NAME VARCHAR(255) NOT NULL UNIQUE)";
            stmt.executeUpdate(sql);
            sql = "CREATE TABLE if NOT EXISTS CAR " +
                    "(id INTEGER AUTO_INCREMENT, " +
                    " NAME VARCHAR(255) NOT NULL UNIQUE, " +
                    " COMPANY_ID INTEGER NOT NULL, " +
                    " CONSTRAINT pk_cars PRIMARY KEY (ID), " +
                    " CONSTRAINT fk_company FOREIGN KEY (COMPANY_ID) REFERENCES COMPANY(id) ON DELETE CASCADE)";
            stmt.executeUpdate(sql);
            sql = "CREATE TABLE if NOT EXISTS CUSTOMER " +
                    "(id INTEGER PRIMARY KEY AUTO_INCREMENT, " +
                    " NAME VARCHAR(255) NOT NULL UNIQUE, " +
                    " RENTED_CAR_ID INTEGER DEFAULT NULL, " +
                    " CONSTRAINT fk_car FOREIGN KEY (RENTED_CAR_ID) REFERENCES CAR(ID) " +
                    " ON DELETE SET NULL)";
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static void apply(String sql) {
        try {
            JDBC database = new JDBC(databaseName);
            try (Connection con = database.getConnection()) {
                Statement stmt = con.createStatement();
                stmt.executeUpdate(sql);
                stmt.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static List<String[]> getStuff(String sql) {
        List<String[]> list = new ArrayList<>();
        try {
            JDBC database = new JDBC(databaseName);
            try (Connection con = database.getConnection()) {
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    String var1 = rs.getString(1);
                    String var2 = rs.getString(2);
                    list.add(new String[]{var1, var2});
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // use a map to number company and car list
    static HashMap<Integer, Integer> useMap(int key, int value, HashMap<Integer, Integer> getMap) {
        getMap.put(key, value);
        return getMap;
    }
    static int getMapKey(int value, HashMap<Integer, Integer> map) {
        for (var m : map.entrySet()) {
            if (m.getValue().equals(value)) {
                return m.getKey();
            }
        }
        return 0;
    }
}