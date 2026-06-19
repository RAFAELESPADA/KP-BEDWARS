package com.rafaelauler.bedwars;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQL {

    private Connection connection;

    public void connect() {

        try {

            String host =
                    Bedwars.getInstance()
                            .getConfig()
                            .getString("MySQL.Host");

            String port =
                    Bedwars.getInstance()
                            .getConfig()
                            .getString("MySQL.Port");

            String database =
                    Bedwars.getInstance()
                            .getConfig()
                            .getString("MySQL.Database");

            String username =
                    Bedwars.getInstance()
                            .getConfig()
                            .getString("MySQL.Username");

            String password =
                    Bedwars.getInstance()
                            .getConfig()
                            .getString("MySQL.Password");

            connection =
                    DriverManager.getConnection(
                            "jdbc:mysql://"
                                    + host
                                    + ":"
                                    + port
                                    + "/"
                                    + database
                                    + "?useSSL=false&autoReconnect=true",

                            username,
                            password
                    );

        } catch(SQLException exception) {

            exception.printStackTrace();
        }
    }

    public void createTables() {

        try {

            connection.createStatement()
                    .executeUpdate(

                            "CREATE TABLE IF NOT EXISTS bedwars_stats (" +

                                    "uuid VARCHAR(36) PRIMARY KEY," +

                                    "name VARCHAR(16)," +

                                    "kills INT DEFAULT 0," +

                                    "final_kills INT DEFAULT 0," +

                                    "deaths INT DEFAULT 0," +

                                    "wins INT DEFAULT 0," +

                                    "losses INT DEFAULT 0," +

                                    "beds_broken INT DEFAULT 0," +

                                    "level INT DEFAULT 1," +

                                    "coins INT DEFAULT 0," +

                                    "winstreak INT DEFAULT 0" +

                                    ");"

                    );

        } catch(SQLException exception) {

            exception.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public boolean isConnected() {

        try {

            return connection != null
                    && !connection.isClosed();

        } catch(SQLException exception) {

            return false;
        }
    }
}