package carsharing;

import java.sql.Connection;
import java.sql.DriverManager;


class JDBC {

    static final String JDBC_DRIVER = "org.h2.Driver";

    //  Database credentials
    static final String USER = "";
    static final String PASS = "";
    Connection conn = null;

    public JDBC(String databaseName) {
        String path = "jdbc:h2:~/IdeaProjects/Car Sharing/Car Sharing/task/src/carsharing/db/" + databaseName;
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(path,USER,PASS);
            conn.setAutoCommit(true);
        } catch (Exception se) {
            se.printStackTrace();
        }
    }
    protected Connection getConnection() {
        return conn;
    }
}