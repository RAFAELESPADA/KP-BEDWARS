package com.rafaelauler.bedwars;


import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class GameEndManager {

    public void checkWinner(
            Arena arena) {

        BWTeam winner = null;

        int aliveTeams = 0;

        for(BWTeam team :
                arena.getTeams()
                        .values()) {

            if(team.getPlayers()
                    .isEmpty())
                continue;

            aliveTeams++;

            winner = team;
        }

        if(aliveTeams > 1)
            return;

        end(
                arena,
                winner
        );
    }

    private void end(
            Arena arena,
            BWTeam winner) {

        arena.setState(
                ArenaState.ENDING
        );

        Bukkit.broadcastMessage(
                "§a"
                + winner.getColor()
                        .name()
                + " venceu a partida!"
        );
        for(UUID uuid :
            winner.getPlayers()) {
        	PlayerStats stats =
        	        Bedwars.getInstance()
        	                .getStatsManager()
        	                .getStats(uuid);

        	stats.setWins(
        	        stats.getWins() + 1
        	);
        	Player player =
        	        Bukkit.getPlayer(uuid);

        	if(player != null) {

        	    Bedwars.getInstance()
        	            .getRewardManager()
        	            .rewardWin(
        	                    player
        	            );
        	}
        	for(BWTeam team :
                arena.getTeams()
                        .values()) {
        		if(team == winner)
        		    continue;
        	}
        	
        	for(UUID uuid2 :
                winner.getPlayers()) {

            PlayerStats stats2 =
                    Bedwars.getInstance()
                            .getStatsManager()
                            .getStats(uuid2);

            stats2.setLosses(
                    stats2.getLosses() + 1
            );
            if(player != null) {
            Bedwars.getInstance()
            .getRewardManager()
            .rewardLoss(
                    player
            );
        }
        	}
        }
        Bukkit.getScheduler()
                .runTaskLater(
                        Bedwars.getInstance(),

                        () -> Bedwars
                                .getInstance()
                                .getArenaReset()
                                .reset(
                                        arena
                                ),

                        200L
                );
    }
}
