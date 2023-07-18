package GameApp.server.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * A class to edit and get a connection to a database.
 *
 * @author Kevin Kluka
 * @version 1.0
 */
public class ConnectDatabase {

    /**
     * Gets the connection to a database.
     *
     * @return connection to a database
     * @throws SQLException if a connection to a database fails
     */
    public static Connection getConnection() throws SQLException {

        return DriverManager.getConnection("jdbc:postgresql://mouse.db.elephantsql.com:5432/lqwkcmia?currentSchema=gaming_application_database", "lqwkcmia", "Xn6mD0fRBFCMXLyPTpcgcD3rNIBcDx11");
    }
}