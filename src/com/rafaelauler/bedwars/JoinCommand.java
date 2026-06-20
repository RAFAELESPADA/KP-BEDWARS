package com.rafaelauler.bedwars;


import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class JoinCommand implements SubCommand {
    @Override
    public String getName() {
        return "join";
    }

    @Override
    public String getPermission() {
        return "bedwars.join";
    }

    @Override
    public void execute(Player player, String[] args) {

        if(args.length < 2)
            return;

        Arena arena =
                Bedwars.getInstance()
                        .getArenaManager()
                        .getArena(args[1]);

        if(arena == null)
            return;

        BWTeam team =
                arena.getAvailableTeam();
        if(arena.getState()
                == ArenaState.PLAYING) {

            player.sendMessage(
                    "§cPartida já iniciou."
            );

            return;
        }
        if (arena.getPlayers().contains(player)) {
        	player.sendMessage(
                    "§cVocê já está nessa arena."
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
        if(team == null) {

            player.sendMessage(
                    "§cNenhum time disponível."
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

        player.teleport(
                arena.getLobby()
        );
        player.setScoreboard(
                Bukkit.getScoreboardManager()
                        .getNewScoreboard()
        );

        player.getInventory().clear();
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