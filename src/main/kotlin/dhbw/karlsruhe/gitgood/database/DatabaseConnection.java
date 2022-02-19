package dhbw.karlsruhe.gitgood.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DatabaseConnection {

    private static Connection connection;

    public static void initConnection(String connectionUrl, String userName,  String password) {
        try {
            connection = DriverManager.getConnection(connectionUrl, userName, password);
            log.info("Database connection sucessfull!");
        } catch (SQLException e){
            log.warn(e.getMessage());
        }
    }

    public static Connection getConnection() {
        return connection;
    }
}
