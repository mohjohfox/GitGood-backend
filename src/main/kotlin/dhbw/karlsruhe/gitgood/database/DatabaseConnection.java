package dhbw.karlsruhe.gitgood.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DatabaseConnection {

    private static Connection connection;
    private static Statement statement;

    public static void initConnection(String connectionUrl, String userName,  String password) {
        try {
            connection = DriverManager.getConnection(connectionUrl, userName, password);
            statement = connection.createStatement();
            log.info("Database connection sucessfull!");
        } catch (SQLException e){
            log.warn(e.getMessage());
        }
    }

    public static Connection getConnection() {
        return connection;
    }

    public static Statement getStatement() {
        return statement;
    }
}
