package com.rafaelauler.bedwars;


import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class KillManager {

    public void handleDeath(
            Player victim,
            GamePlayer victimGP) {

        Player killer =
                victimGP.getLastDamager();

        if(killer == null)
            return;

        long difference =
                System.currentTimeMillis()
                - victimGP.getLastDamageTime();

        /*
         * 15 segundos
         */
        if(difference > 15000)
            return;

        GamePlayer killerGP =
                Bedwars.getInstance()
                        .getPlayerManager()
                        .get(killer);

        if(killerGP == null)
            return;

        killerGP.setKills(
                killerGP.getKills() + 1
        );
        PlayerStats stats =
                Bedwars.getInstance()
                        .getStatsManager()
                        .getStats(
                                killer.getUniqueId()
                        );
        PlayerStats stats2 =
                Bedwars.getInstance()
                        .getStatsManager()
                        .getStats(
                                victim.getUniqueId()
                        );
        Bedwars.getInstance()
        .getRewardManager()
        .rewardKill(
                killer
        );
        stats2.setDeaths(
                stats2.getDeaths() + 1
        );
        stats.setKills(
                stats.getKills() + 1
        );
        if(!victimGP.getTeam()
                .isBedAlive()) {

            killerGP.setFinalKills(
                    killerGP.getFinalKills() + 1
            );

            stats.setFinalKills(
                    stats.getFinalKills() + 1
            );
            stats2.setDeaths(
                    stats2.getDeaths() + 1
            );
            Bedwars.getInstance()
            .getRewardManager()
            .rewardFinalKill(
                    killer
            );
            Bukkit.broadcastMessage(
                    "§cFINAL KILL! "
                    + killer.getName()
                    + " matou "
                    + victim.getName()
            );

            return;
        }
        Bukkit.broadcastMessage(
                "§e"
                + killer.getName()
                + " matou "
                + victim.getName()
        );
    }
}