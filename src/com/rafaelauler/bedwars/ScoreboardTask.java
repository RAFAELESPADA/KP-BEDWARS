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

            Bedwars.getInstance()
                    .getScoreboardManager()
                    .update(player);
        }
    }
}