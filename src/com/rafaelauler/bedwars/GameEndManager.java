package com.rafaelauler.bedwars;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class GameEndManager {

	public void checkWinner(
	        Arena arena) {

	    List<BWTeam> alive =
	            new ArrayList<>();

	    for(BWTeam team :
	            arena.getTeams()
	                    .values()) {

	        if(team.isAlive()) {

	            alive.add(team);
	        }
	    }

	    if(alive.size() != 1)
	        return;

	    BWTeam winner =
	            alive.get(0);

	    Bukkit.broadcastMessage(
	            "§6§lBEDWARS §8» §aTime "
	            + winner.getColor()
	                    .getColor()
	            + winner.getColor()
	                    .name()
	            + " venceu a partida!"
	    );

	    Bedwars.getInstance()
	            .getGameEndManager()
	            .endGame(
	                    arena,
	                    winner
	            );
	}
	public void endGame(
	        Arena arena,
	        BWTeam winner) {

	    if(arena.getState()
	            == ArenaState.ENDING)
	        return;

	    arena.setState(
	            ArenaState.ENDING
	    );

	    rewardPlayers(
	            arena,
	            winner
	    );

	    for(Player player :
	            new ArrayList<Player>(
	                    arena.getPlayers()
	            )) {

	        sendToLobby(player);
	    }
	    for(UUID spectator2 :
	        new ArrayList<>(
	                Bedwars.getInstance()
	                        .getSpectatorManager()
	                        .getSpectators(arena)
	        )) {
Player spectator = Bukkit.getPlayer(spectator2);
	    Bedwars.getInstance()
	            .getSpectatorManager()
	            .removeSpectator(
	                    arena,
	                    spectator
	            );

	    spectator.getInventory().clear();

	    spectator.teleport(
	            Bedwars.getInstance()
	                    .getLobbySpawn()
	    );

	    spectator.setHealth(20.0);

	    spectator.setFoodLevel(20);

	    spectator.setFireTicks(0);
spectator.getInventory().setArmorContents(null);
	    LobbyItems.give(
	            spectator
	    );

	    Bedwars.getInstance()
	            .getLobbyScoreboard()
	            .update(
	                    spectator
	            );
	}
	    Bedwars.getInstance()
	            .getArenaReset()
	            .reset(
	                    arena
	            );
	}
    private void sendToLobby(
            Player player) {

        GamePlayer gp =
                Bedwars.getInstance()
                        .getPlayerManager()
                        .get(player);

        Arena arena =
                gp.getArena();
        if(gp != null) {

            if(gp.getTeam() != null) {

                gp.getTeam()
                        .getPlayers()
                        .remove(
                                player.getUniqueId()
                        );
            }

            if(arena != null) {

                arena.getPlayers()
                        .remove(player);

                arena.getGamePlayers()
                        .remove(gp);
            }
        }
        if(gp != null) {

            gp.setArena(null);
            gp.setTeam(null);
            gp.setSpectator(false);
        }
        player.getInventory()
                .clear();

        player.setHealth(
                20.0
        );

player.getInventory().setArmorContents(null);
        player.setFoodLevel(
                20
        );

        player.setFireTicks(
                0
        );

        player.setGameMode(
                GameMode.ADVENTURE
        );

        
        player.teleport(
                Bedwars.getInstance()
                        .getLobbySpawn()
        );
        player.setScoreboard(
                Bukkit.getScoreboardManager()
                        .getNewScoreboard()
        );

        LobbyItems.give(
                player
        );

        Bedwars.getInstance()
                .getLobbyScoreboard()
                .update(
                        player
                );
    

    new BukkitRunnable() {

        @Override
        public void run() {

            for(Player player :
                    new ArrayList<Player>(
                            arena.getPlayers()
                    )) {

                sendToLobby(player);
            }

            Bedwars.getInstance()
                    .getArenaReset()
                    .reset(
                            arena
                    );

        }

    }.runTaskLater(
            Bedwars.getInstance(),
            60L
    );
}

    private void rewardPlayers(
            Arena arena,
            BWTeam winner) {

        /*
         * Vencedores
         */
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

                GamePlayer gp =
                        Bedwars.getInstance()
                                .getPlayerManager()
                                .get(player);

                if(gp != null) {

                    gp.setWinner(true);
                }

                Bedwars.getInstance()
                        .getRewardManager()
                        .rewardWin(player);

                TitleAPI.send(
                        player,
                        "§6§lVITÓRIA!",
                        "§eVocê venceu a partida",
                        10,
                        100,
                        10
                );
            }
        }
    
        }
    
}