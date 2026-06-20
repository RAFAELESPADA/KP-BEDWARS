package com.rafaelauler.bedwars;


import org.bukkit.Bukkit;
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

        BWTeam team =
                arena.getAvailableTeam();

        if(team == null) {

            player.sendMessage(
                    "§cNenhum time disponível."
            );

            return;
        }
        if (arena.getPlayers().contains(player)) {
        	player.sendMessage(
                    "§cVocê já está nessa arena."
            );
        	return;
        }
        arena.getPlayers().add(player);

        team.getPlayers().add(
                player.getUniqueId()
        );

        GamePlayer gamePlayer =
                Bedwars.getInstance()
                        .getPlayerManager()
                        .get(player);

        gamePlayer.setArena(arena);
        gamePlayer.setTeam(team);
        player.setScoreboard(
                Bukkit.getScoreboardManager()
                        .getNewScoreboard()
        );
        player.getInventory().clear();
        player.teleport(
                arena.getLobby()
        );
        if(arena.getPlayers()
                .size() >= 2
                && !arena.isStarting()) {

            new CountdownTask(
                    arena
            ).runTaskTimer(
                    Bedwars.getInstance(),
                    20L,
                    20L
            );
        }
        player.sendMessage(
                "§aEntrou na arena."
        );
    }
    }

