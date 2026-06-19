package com.rafaelauler.bedwars;


import org.bukkit.entity.Player;

public class ArenaJoinManager {

    public static void join(
            Player player,
            Arena arena) {

        if(arena.getState()
                == ArenaState.PLAYING) {

            player.sendMessage(
                    "§cPartida já iniciou."
            );

            return;
        }
        if(arena.getPlayers()
                .size()
                >= arena.getMaxPlayers()) {

            player.sendMessage(
                    "§cArena cheia."
            );

            return;
        }
        arena.getPlayers()
                .add(player);

        player.teleport(
                arena.getLobby()
        );

        player.sendMessage(
                "§aEntrou na arena "
                + arena.getName()
        );
    }
}
