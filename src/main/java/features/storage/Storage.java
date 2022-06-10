package features.storage;

import features.prefs.Prefs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Storage {
    private static final Storage INSTANCE = new Storage();

    private Connection connection;

    private Storage() {
        try {
            String connectionUrl = new Prefs().getString(Prefs.DB_JDBC_CONNECTION_URL);
            String username = new Prefs().getString(Prefs.DB_JDBC_USERNAME);
            connection = DriverManager.getConnection(connectionUrl, username, "");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static Storage getInstance() {
        return INSTANCE;
    }

    public int executeUpdate(String sql) {
        try(Statement st = connection.createStatement()) {
            return st.executeUpdate(sql);
        } catch (Exception ex) {
            ex.printStackTrace();

            return -1;
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}