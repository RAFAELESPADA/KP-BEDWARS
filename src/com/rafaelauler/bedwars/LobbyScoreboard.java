package com.rafaelauler.bedwars;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

public class LobbyScoreboard {

    public void update(
            Player player) {

        PlayerStats stats =
                Bedwars.getInstance()
                        .getStatsManager()
                        .getStats(
                                player.getUniqueId()
                        );

        Scoreboard scoreboard =
                Bukkit.getScoreboardManager()
                        .getNewScoreboard();

        Objective objective =
                scoreboard.registerNewObjective(
                        "bw",
                        "dummy"
                );

        objective.setDisplaySlot(
                DisplaySlot.SIDEBAR
        );

        objective.setDisplayName(
                "§6§lBEDWARS"
        );

        int score = 10;

        add(
                objective,
                "§fNível: §e"
                + stats.getLevel()
                + "✫",
                score--
        );

        add(
                objective,
                "§fCoins: §6"
                + stats.getCoins(),
                score--
        );

        add(
                objective,
                "§1",
                score--
        );

        add(
                objective,
                "§fKills: §a"
                + stats.getKills(),
                score--
        );
        add(
                objective,
                "§fDeaths: §a"
                + stats.getDeaths(),
                score--
        );
        add(
                objective,
                "§fWins: §a"
                + stats.getWins(),
                score--
        );

        add(
                objective,
                "§2",
                score--
        );

        add(
                objective,
                "§fOnline: §a"
                + Bukkit.getOnlinePlayers()
                        .size(),
                score--
        );

        add(
                objective,
                "§3",
                score--
        );

        add(
                objective,
                "§eskyzermc.com.br",
                score--
        );

        player.setScoreboard(
                scoreboard
        );
    }

    private void add(
            Objective objective,
            String text,
            int score) {

        objective.getScore(
                text
        ).setScore(
                score
        );
    }
}