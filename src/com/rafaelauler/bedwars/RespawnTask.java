package com.rafaelauler.bedwars;

import org.bukkit.GameMode;
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
        if (player.isDead()) {
        	player.spigot().respawn();
        }
player.setGameMode(GameMode.SPECTATOR);
GamePlayer gp =
Bedwars.getInstance()
        .getPlayerManager()
        .get(player);
if(gp.getArena() == null) {
    cancel();
}
player.teleport(gp.getArena().getLobby());
        player.sendMessage(
                "§aRespawna em "
                + seconds
                + " segundos..."
        );
        TitleAPI.send(
                player,
                "§e§lRESPAWN",
                "§fVoltando em §a"
                + seconds
                + "s",
                0,
                25,
                0
        );
        seconds--;
    }
}