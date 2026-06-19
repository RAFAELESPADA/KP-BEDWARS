package com.rafaelauler.bedwars;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

public class EliminationManager {

    public void eliminate(
            Player player,
            GamePlayer gp) {

        BWTeam team =
                gp.getTeam();

        gp.setAlive(false);

        team.getPlayers()
                .remove(
                        player.getUniqueId()
                );

        player.getInventory()
                .clear();

        player.setGameMode(
                GameMode.CREATIVE
        );

        Bedwars.getInstance()
                .getSpectatorManager()
                .addSpectator(
                        gp.getArena(),
                        player
                );

        Bukkit.broadcastMessage(
                "§c"
                + player.getName()
                + " foi eliminado!"
        );

        Bedwars.getInstance()
                .getGameEndManager()
                .checkWinner(
                        gp.getArena()
                );
    }
}