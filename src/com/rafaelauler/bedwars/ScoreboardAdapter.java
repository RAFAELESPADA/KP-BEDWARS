package com.rafaelauler.bedwars;


import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ScoreboardAdapter {

    private final Player player;

    public ScoreboardAdapter(
            Player player) {

        this.player = player;
    }

    public void send() {

        GamePlayer gp =
                Bedwars.getInstance()
                        .getPlayerManager()
                        .get(player);

        if(gp.getArena() == null)
            return;

        Arena arena =
                gp.getArena();

        org.bukkit.scoreboard.ScoreboardManager manager =
                Bukkit.getScoreboardManager();

        Scoreboard board =
                manager.getNewScoreboard();

        Objective objective =
                board.registerNewObjective(
                        "bedwars",
                        "dummy"
                );

        objective.setDisplaySlot(
                DisplaySlot.SIDEBAR
        );

        objective.setDisplayName(
                "§b§lBEDWARS"
        );

        int score = 15;

        add(
                objective,
                "§7"
                + new SimpleDateFormat(
                        "dd/MM/yyyy"
                ).format(
                        new Date()
                ),
                score--
        );

        add(
                objective,
                " ",
                score--
        );
        if(arena.getState()
                == ArenaState.STARTING) {

            add(
                    objective,
                    "§fInicia em: §a"
                    + arena.getCountdown()
                    + "s",
                    score--
            );
        }
        add(
                objective,
                "§fMapa: §a"
                + arena.getName(),
                score--
        );

        add(
                objective,
                "§fJogadores: §a"
                + arena.getPlayers()
                        .size(),
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
                + gp.getKills(),
                score--
        );
        if(gp.getTeam() != null) {

            add(
                    objective,
                    "§fTime: "
                    + gp.getTeam().getColor().getColor()
                    + gp.getTeam()
                            .getColor()
                            .name(),
                    score--
            );
        }
        add(
                objective,
                "§fFinal Kills: §c"
                + gp.getFinalKills(),
                score--
        );
        add(
                objective,
                "§2",
                score--
        );
        add(
                objective,
                "  ",
                score--
        );

        for(BWTeam team :
                arena.getTeams()
                        .values()) {

            String status =
                    team.isBedAlive()

                    ?

                    "§a✔"

                    :

                    "§c✘";

            add(
                    objective,
                    team.getColor()
                            .getColor()
                    + team.getColor()
                            .name()
                    + " "
                    + status,
                    score--
            );
        }

        add(
                objective,
                "   ",
                score--
        );

        add(
                objective,
                "§eskyzermc.com.br",
                score
        );

        player.setScoreboard(
                board
        );
    }

    private void add(
            Objective objective,
            String line,
            int score) {

        objective.getScore(line)
                .setScore(score);
    }
}
