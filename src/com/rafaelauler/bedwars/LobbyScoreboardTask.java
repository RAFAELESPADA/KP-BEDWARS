package com.rafaelauler.bedwars;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class LobbyScoreboardTask
        extends BukkitRunnable {

    @Override
    public void run() {

        for(Player player :
                Bukkit.getOnlinePlayers()) {

            GamePlayer gp =
                    Bedwars.getInstance()
                            .getPlayerManager()
                            .get(player);

            if(gp != null
                    && gp.getArena() != null)
                continue;

            Bedwars.getInstance()
                    .getLobbyScoreboard()
                    .update(player);
        }
    }
}