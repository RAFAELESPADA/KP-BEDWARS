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
        if(team == null)
            return;
        gp.setAlive(false);

        team.getPlayers()
                .remove(
                        player.getUniqueId()
                );

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
        if(!gp.hasCountedLoss()) {

            PlayerStats stats =
                    Bedwars.getInstance()
                            .getStatsManager()
                            .getStats(
                                    player.getUniqueId()
                            );

            stats.setLosses(
                    stats.getLosses() + 1
            );

            gp.setCountedLoss(true);

            Bedwars.getInstance()
                    .getRewardManager()
                    .rewardLoss(player);
        }

        TitleAPI.send(
                player,
                "§4§lELIMINADO",
                "§7Sua partida acabou",
                10,
                80,
                10
        );
        player.sendMessage("");
        player.sendMessage("§c§lELIMINADO");
        player.sendMessage("§7Você não possui mais respawns.");
        player.sendMessage("");
        Bedwars.getInstance()
                .getGameEndManager()
                .checkWinner(
                        gp.getArena()
                );
    }
}