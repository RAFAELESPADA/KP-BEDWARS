package com.rafaelauler.bedwars;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import net.md_5.bungee.api.ChatColor;

public class GameEndManager {

	public void checkWinner(
	        Arena arena) {

	    List<BWTeam> alive =
	            new ArrayList<>();

	    for (BWTeam team : arena.getTeams().values()) {
	        if (team.hasAlivePlayers()) {
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
	public void endGame(Arena arena, BWTeam winner) {

	    if (arena.getState() == ArenaState.ENDING)
	        return;

	    arena.setState(ArenaState.ENDING);

	    rewardPlayers(arena, winner);

	    Bukkit.broadcastMessage(
	            "§6§lBEDWARS §8» §aA arena será reiniciada em 5 segundos."
	    );

	    new BukkitRunnable() {

	        @Override
	        public void run() {

	            // Jogadores
	            for (Player player : new ArrayList<>(arena.getPlayers())) {
	                sendToLobby(player);
	            }

	            // Espectadores
	            for (UUID uuid : new HashSet<>(arena.getSpectators())) {

	                Player spectator = Bukkit.getPlayer(uuid);

	                if (spectator == null)
	                    continue;

	                Bedwars.getInstance()
	                        .getSpectatorManager()
	                        .removeSpectator(arena, spectator);

	                spectator.getInventory().clear();
	                spectator.getInventory().setArmorContents(null);

	                spectator.setHealth(20.0);
	                spectator.setFoodLevel(20);
	                spectator.setFireTicks(0);
	                spectator.setGameMode(GameMode.ADVENTURE);

	                spectator.teleport(
	                        Bedwars.getInstance().getLobbySpawn()
	                );

	                LobbyItems.give(spectator);

	                Bedwars.getInstance()
	                        .getLobbyScoreboard()
	                        .update(spectator);

	                spectator.sendMessage("§cA partida foi finalizada.");
	            }

	            arena.getSpectators().clear();

	            Bedwars.getInstance()
	                    .getArenaReset()
	                    .reset(arena);

	        }

	    }.runTaskLater(Bedwars.getInstance(), 100L);
	}
    private void sendToLobby(
            Player player) {

        GamePlayer gp =
                Bedwars.getInstance()
                        .getPlayerManager()
                        .get(player);
        if (gp == null)
            return;
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