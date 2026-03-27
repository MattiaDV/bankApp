package DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconnection {
    private final String DB_URL = System.getenv("DB_URL");
    private final String DB_USER = System.getenv("DB_USER");
    private final String DB_PASW = System.getenv("DB_PASW");

    public Connection getConn() {
        try {
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASW);
            System.out.println("Connessione al DB Riuscita!");
            return conn;
        } catch (SQLException e) {
            System.out.println("Connessione al DB non riuscita!: " + e.getMessage());
            return null;
        }
    }
}
