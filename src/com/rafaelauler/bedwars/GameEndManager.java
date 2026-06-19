package com.rafaelauler.bedwars;


import org.bukkit.Bukkit;

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
