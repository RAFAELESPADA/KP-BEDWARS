package com.rafaelauler.bedwars;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class RespawnTask
        extends BukkitRunnable {

    private final Player player;

    private int seconds = 5;

    public RespawnTask(
            Player player) {

        this.player = player;
    }

    @Override
    public void run() {

        if(seconds <= 0) {

            Bedwars.getInstance()
                    .getRespawnManager()
                    .respawn(
                            player
                    );

            cancel();

            return;
        }

        player.sendMessage(
                "§aRespawn em "
                + seconds
                + "..."
        );

        seconds--;
    }
}