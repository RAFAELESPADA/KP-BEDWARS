package com.rafaelauler.bedwars;


import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class ArenaLeaveManager {
	public static void leave(GamePlayer gp) {

	    Arena arena = gp.getArena();

	    if (arena == null)
	        return;

	    gp.setAlive(false);

	    if (gp.getTeam() != null) {
	        gp.getTeam().getPlayers().remove(gp.getUuid());
	    }

	    arena.getGamePlayers().remove(gp);
	    Player p = Bukkit.getPlayer(gp.getUuid());
	    if (p != null) {
	    arena.getPlayers().remove(p); // ou gp.getUuid(), conforme o tipo da coleção
	    }

	    Bedwars.getInstance()
	            .getGameEndManager()
	            .checkWinner(arena);

	    gp.setArena(null);
	    gp.setTeam(null);
	    if (p != null && p.isOnline()) {
	        p.getEnderChest().clear();
	       }
	    if (gp.getTrackerTask() != null) {
	        gp.getTrackerTask().cancel();
	        gp.setTrackerTask(null);
	    }
	    gp.setCombo(0);
	    checkArena(arena);
	}
    
    private static void checkArena(
            Arena arena) {

        if(arena.getPlayers()
                .isEmpty()) {

            new ArenaReset()
                    .reset(arena);

            return;
        }

        if(arena.getState()
                == ArenaState.STARTING
                && arena.getPlayers()
                        .size() < 2) {

            arena.setStarting(false);

            arena.setState(
                    ArenaState.WAITING
            );
        }
    }
}
