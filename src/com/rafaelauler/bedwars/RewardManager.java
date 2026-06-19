package com.rafaelauler.bedwars;


import org.bukkit.entity.Player;

public class RewardManager {

    public void rewardKill(
            Player player) {

        PlayerStats stats =
                Bedwars.getInstance()
                        .getStatsManager()
                        .getStats(
                                player.getUniqueId()
                        );

        stats.setCoins(
                stats.getCoins() + 5
        );

        Bedwars.getInstance()
                .getLevelManager()
                .addXP(
                        stats,
                        10
                );

        player.sendMessage(
                "§6+5 Coins §7(+10 XP)"
        );
    }

    public void rewardFinalKill(
            Player player) {

        PlayerStats stats =
                Bedwars.getInstance()
                        .getStatsManager()
                        .getStats(
                                player.getUniqueId()
                        );

        stats.setCoins(
                stats.getCoins() + 10
        );

        Bedwars.getInstance()
                .getLevelManager()
                .addXP(
                        stats,
                        20
                );

        player.sendMessage(
                "§cFINAL KILL! §6+10 Coins §7(+20 XP)"
        );
    }

    public void rewardBedBreak(
            Player player) {

        PlayerStats stats =
                Bedwars.getInstance()
                        .getStatsManager()
                        .getStats(
                                player.getUniqueId()
                        );

        stats.setCoins(
                stats.getCoins() + 25
        );

        Bedwars.getInstance()
                .getLevelManager()
                .addXP(
                        stats,
                        30
                );

        player.sendMessage(
                "§eCama destruída! §6+25 Coins §7(+30 XP)"
        );
    }

    public void rewardWin(
            Player player) {

        PlayerStats stats =
                Bedwars.getInstance()
                        .getStatsManager()
                        .getStats(
                                player.getUniqueId()
                        );

        stats.setCoins(
                stats.getCoins() + 100
        );

        stats.setWinstreak(
                stats.getWinstreak() + 1
        );

        Bedwars.getInstance()
                .getLevelManager()
                .addXP(
                        stats,
                        100
                );

        player.sendMessage(
                "§aVitória! §6+100 Coins §7(+100 XP)"
        );
    }

    public void rewardLoss(
            Player player) {

        PlayerStats stats =
                Bedwars.getInstance()
                        .getStatsManager()
                        .getStats(
                                player.getUniqueId()
                        );

        stats.setWinstreak(0);

        Bedwars.getInstance()
                .getLevelManager()
                .addXP(
                        stats,
                        25
                );
    }
}
