package com.rafaelauler.bedwars;


import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class ArenaLeaveManager {

    public static void leave(
            GamePlayer gp) {

        Arena arena =
                gp.getArena();

        Player player =
                Bukkit.getPlayer(
                        gp.getUuid()
                );

        if(arena == null)
            return;

        arena.getGamePlayers()
                .remove(gp);

        arena.getPlayers()
                .remove(player);

        if(gp.getTeam() != null) {

            gp.getTeam()
                    .getPlayers()
                    .remove(gp.getUuid());
        }

        gp.setArena(null);
        gp.setTeam(null);
       if (player != null&& player.isOnline()) {
        player.getEnderChest().clear();
       }
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
