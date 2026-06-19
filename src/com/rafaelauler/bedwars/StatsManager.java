package com.rafaelauler.bedwars;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class StatsManager {

    private final MySQL mysql;

    private final Map<UUID, PlayerStats> cache =
            new HashMap<>();

    public StatsManager(
            MySQL mysql) {

        this.mysql = mysql;
    }

    public PlayerStats getStats(
            UUID uuid) {

        return cache.computeIfAbsent(
                uuid,
                this::load
        );
    }

    private PlayerStats load(
            UUID uuid) {

        PlayerStats stats =
                new PlayerStats(uuid);

        try {

            PreparedStatement ps =
                    mysql.getConnection()
                            .prepareStatement(
                                    "SELECT * FROM bedwars_stats WHERE uuid=?"
                            );

            ps.setString(
                    1,
                    uuid.toString()
            );

            ResultSet rs =
                    ps.executeQuery();

            if(rs.next()) {

                stats.setName(
                        rs.getString("name")
                );

                stats.setKills(
                        rs.getInt("kills")
                );

                stats.setFinalKills(
                        rs.getInt("final_kills")
                );

                stats.setDeaths(
                        rs.getInt("deaths")
                );

                stats.setWins(
                        rs.getInt("wins")
                );

                stats.setLosses(
                        rs.getInt("losses")
                );

                stats.setBedsBroken(
                        rs.getInt("beds_broken")
                );

                stats.setLevel(
                        rs.getInt("level")
                );

                stats.setCoins(
                        rs.getInt("coins")
                );

                stats.setWinstreak(
                        rs.getInt("winstreak")
                );
            }

        } catch(SQLException exception) {

            exception.printStackTrace();
        }

        return stats;
    }

    public void save(
            PlayerStats stats) {

        try {

            PreparedStatement ps =
                    mysql.getConnection()
                            .prepareStatement(

                                    "INSERT INTO bedwars_stats " +
                                            "(uuid,name,kills,final_kills,deaths,wins,losses,beds_broken,level,coins,winstreak) " +
                                            "VALUES(?,?,?,?,?,?,?,?,?,?,?) " +
                                            "ON DUPLICATE KEY UPDATE " +
                                            "name=?, " +
                                            "kills=?, " +
                                            "final_kills=?, " +
                                            "deaths=?, " +
                                            "wins=?, " +
                                            "losses=?, " +
                                            "beds_broken=?, " +
                                            "level=?, " +
                                            "coins=?, " +
                                            "winstreak=?"

                            );

            ps.setString(1,
                    stats.getUuid()
                            .toString());

            ps.setString(2,
                    stats.getName());

            ps.setInt(3,
                    stats.getKills());

            ps.setInt(4,
                    stats.getFinalKills());

            ps.setInt(5,
                    stats.getDeaths());

            ps.setInt(6,
                    stats.getWins());

            ps.setInt(7,
                    stats.getLosses());

            ps.setInt(8,
                    stats.getBedsBroken());

            ps.setInt(9,
                    stats.getLevel());

            ps.setInt(10,
                    stats.getCoins());

            ps.setInt(11,
                    stats.getWinstreak());

            ps.setString(12,
                    stats.getName());

            ps.setInt(13,
                    stats.getKills());

            ps.setInt(14,
                    stats.getFinalKills());

            ps.setInt(15,
                    stats.getDeaths());

            ps.setInt(16,
                    stats.getWins());

            ps.setInt(17,
                    stats.getLosses());

            ps.setInt(18,
                    stats.getBedsBroken());

            ps.setInt(19,
                    stats.getLevel());

            ps.setInt(20,
                    stats.getCoins());

            ps.setInt(21,
                    stats.getWinstreak());

            ps.executeUpdate();

        } catch(SQLException exception) {

            exception.printStackTrace();
        }
    }

    public void saveAll() {

        for(PlayerStats stats :
                cache.values()) {

            save(stats);
        }
    }

    public void unload(
            UUID uuid) {

        PlayerStats stats =
                cache.remove(uuid);

        if(stats == null)
            return;

        save(stats);
    }

    public Map<UUID, PlayerStats> getCache() {
        return cache;
    }
}