package com.rafaelauler.bedwars;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class EliminationTask {

	public static void eliminate(
	        Player player,
	        GamePlayer gp) {

	    Arena arena =
	            gp.getArena();

	    arena.getPlayers()
	            .remove(player);

	    gp.getTeam()
	            .getPlayers()
	            .remove(
	                    player.getUniqueId()
	            );

	    player.sendMessage(
	            "§cVocê foi eliminado!"
	    );
	    if (gp.getTrackerTask() != null) {
	        gp.getTrackerTask().cancel();
	        gp.setTrackerTask(null);
	    }
	    Bedwars.getInstance()
        .getSpectatorManager()
        .addSpectator(
                arena,
                player
        );

player.teleport(
        arena.getSpectator()
);

	    checkWin(arena);
	}

public static void checkWin(
        Arena arena) {

    int aliveTeams = 0;

    BWTeam winner = null;

    for(BWTeam team :
            arena.getTeams().values()) {

        if(team.getPlayers().isEmpty())
            continue;

        aliveTeams++;

        winner = team;
    }

    if(aliveTeams > 1)
        return;

    Bukkit.broadcastMessage(
            winner.getColor().getColor()
            + winner.getColor().name()
            + " venceu!"
    );

    BWTeam v = winner;
    Bukkit.getScheduler()
    .runTaskLater(
            Bedwars.getInstance(),

            () -> Bedwars
                    .getInstance()
                    .getGameEndManager()
                    .endGame(arena, v),

            200L
    );
    
}
}
