package com.rafaelauler.bedwars;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import org.bukkit.Bukkit;

public class MySQL {

    private Connection connection;

    public  synchronized void connect() {
    	if (isConnected()) {
    	    disconnect();
    	}
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
                    				+ "?useSSL=false"
                    				+ "&characterEncoding=utf8"
                    				+ "&useUnicode=true"
                    				+ "&serverTimezone=UTC"
                    				+ "&connectTimeout=10000"
                    				+ "&socketTimeout=30000",

                            username,
                            password
                    );
            Bukkit.getLogger().info(
                    "[KPBedWars] Conectado ao MySQL."
            );
        }catch (SQLException exception) {

            Bukkit.getLogger().severe(
                    "[KPBedWars] Não foi possível conectar ao MySQL."
            );

            exception.printStackTrace();

            throw new RuntimeException(exception);
        }
    }
    public boolean execute(String sql) {

        try (Statement statement = getConnection().createStatement()) {

            statement.execute(sql);
            return true;

        } catch (SQLException exception) {

            exception.printStackTrace();
            return false;
        }
    }
    private boolean reconnecting;

    public synchronized void reconnect() {

        if (reconnecting)
            return;

        reconnecting = true;

        try {

            disconnect();

            connect();

            if (isConnected()) {

                Bukkit.getLogger().info(
                        "[KPBedWars] Reconectado ao MySQL."
                );

            } else {

                Bukkit.getLogger().warning(
                        "[KPBedWars] Falha ao reconectar ao MySQL."
                );
            }

        } finally {

            reconnecting = false;
        }
    }
    public synchronized Connection getValidConnection() {

        if (!ping()) {
            reconnect();
        }

        return connection;
    }
    public  synchronized void disconnect() {

        if (!isConnected())
            return;

        try {

            connection.close();
            connection = null;
        } catch (SQLException exception) {

            exception.printStackTrace();
        }
    }
    public PreparedStatement prepare(String sql)
            throws SQLException {

        return getConnection().prepareStatement(sql);
    }
    public void createTables() {
    	if (!ping()) {
    	    reconnect();
    	}
        try {

        	try (Statement statement = getConnection().createStatement()) {

        	    statement.executeUpdate(

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

        	                    "winstreak INT DEFAULT 0," +

        	                    "highest_combo INT DEFAULT 0" +

        	                    ");"

        	    );

        	}

            updateDatabase();
        } catch(SQLException exception) {

            exception.printStackTrace();
        }
    }
    public boolean ping() {

        if (!isConnected())
            return false;

        try (Statement statement = connection.createStatement()) {

            statement.execute("SELECT 1");
            return true;

        } catch (SQLException exception) {

            return false;
        }
    }
    public Connection getConnection() {
        return getValidConnection();
    }

    private void updateDatabase() {

        addColumn(
                "highest_combo",
                "INT NOT NULL DEFAULT 0"
        );

    }
    private void addColumn(String column, String type) {

    	try (Statement statement = getConnection().createStatement()) {

    	    statement.executeUpdate(
    	            "ALTER TABLE bedwars_stats ADD COLUMN "
    	                    + column + " " + type
    	    );

    	

            Bukkit.getLogger().info(
                    "[KPBedWars] Coluna '" + column + "' adicionada."
            );

        }catch (SQLException exception) {

        	String message = exception.getMessage();

        	if (message != null &&
        			"42S21".equals(exception.getSQLState())) {

        	    Bukkit.getLogger().info(
        	            "[KPBedWars] Coluna '" + column + "' já existe."
        	    );

        	} else {

        	    exception.printStackTrace();
        	}

        
        }
    
    }
    public boolean isConnected() {

        try {

            return connection != null
                    && !connection.isClosed()
                    && connection.isValid(2);

        } catch (SQLException exception) {

            return false;
        }
    }
}