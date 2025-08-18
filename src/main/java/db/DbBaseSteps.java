package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbBaseSteps {
    protected Connection getConnection() throws SQLException {
         return DriverManager.getConnection("jdbc:postgresql://147.45.143.178:32200/db_movies", "test", "test");
    }
}


