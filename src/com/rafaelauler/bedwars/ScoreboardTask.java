package com.rafaelauler.bedwars;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class ScoreboardTask
        extends BukkitRunnable {

    @Override
    public void run() {

        for(Player player :
                Bukkit.getOnlinePlayers()) {

            GamePlayer gp =
                    Bedwars.getInstance()
                            .getPlayerManager()
                            .get(player);

            /*
             * Lobby
             */
            if(gp == null
                    || gp.getArena() == null) {

                Bedwars.getInstance()
                        .getLobbyScoreboard()
                        .update(player);

                continue;
            }

            /*
             * Partida
             */
            Bedwars.getInstance()
                    .getScoreboardManager()
                    .update(player);
        }
    }
}