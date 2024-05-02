package org.insta.databaseconnection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.*;

public final class DatabaseConnection {

    private static final Logger LOGGER = LogManager.getLogger(DatabaseConnection.class);
    private static Connection connection;

    private DatabaseConnection() {
    }

    public static Connection get() {
        if (Objects.isNull(connection)) {
            final Properties properties = new Properties();

            try (FileReader fileReader = new FileReader("C:/designpp/Instagram/database/src/main/resources/db.properties")) {
                properties.load(fileReader);

                Class.forName("org.postgresql.Driver");

                connection = DriverManager.getConnection(properties.getProperty("url"),
                        properties.getProperty("username"), properties.getProperty("password"));

                LOGGER.info("Database is connected");
            } catch (final Exception exception) {
                if (LOGGER != null) {
                    LOGGER.error("Database Connection failed");
                }
            }
            return connection;
        }
        return connection;
    }
}
