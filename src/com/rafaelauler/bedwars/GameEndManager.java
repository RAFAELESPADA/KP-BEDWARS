package com.rafaelauler.bedwars;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;


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
	            	 GamePlayer gp2 =
		                        Bedwars.getInstance()
		                                .getPlayerManager()
		                                .get(player);
	            	 gp2.setArena(null);
		                gp2.setTeam(null);
		                player.setExp(0);
		                player.setLevel(0);
		                player.setFallDistance(0);
		                player.setVelocity(new Vector());
		                player.getActivePotionEffects().forEach(effect ->
		                        player.removePotionEffect(effect.getType()));
		                arena.getPlayers().remove(player);
		                arena.getGamePlayers().remove(gp2);
		                gp2.setAlive(true);
		                gp2.setSpectator(false);
		                gp2.setWinner(false);
		                if (gp2.getTrackerTask() != null) {
		                    gp2.getTrackerTask().cancel();
		                    gp2.setTrackerTask(null);
		                }
		                gp2.setKills(0);
		                gp2.setFinalKills(0);

		                gp2.setSwordTier(null);
		                gp2.setArmorTier(null);
		                gp2.setPickaxeTier(null);
		                gp2.setAxeTier(null);

		                player.setGameMode(GameMode.ADVENTURE);
	                sendToLobby(player);
	            }

	            // Espectadores
	            for (UUID uuid : new HashSet<>(arena.getSpectators())) {

	                Player spectator = Bukkit.getPlayer(uuid);

	                if (spectator == null)
	                    continue;
	                
	                GamePlayer gp =
	                        Bedwars.getInstance()
	                                .getPlayerManager()
	                                .get(spectator);
	                if (gp.getTrackerTask() != null) {
	                    gp.getTrackerTask().cancel();
	                    gp.setTrackerTask(null);
	                }
	                spectator.setExp(0);
	                spectator.setLevel(0);
	                spectator.setFallDistance(0);
	                spectator.setVelocity(new Vector());
	                spectator.getActivePotionEffects().forEach(effect ->
	                spectator.removePotionEffect(effect.getType()));
	                Bedwars.getInstance()
	                        .getSpectatorManager()
	                        .removeSpectator(arena, spectator);
	                spectator.getInventory().clear();
	                spectator.getInventory().setArmorContents(null);
	                gp.setArena(null);
	                gp.setTeam(null);
	                
	                arena.getPlayers().remove(spectator);
	                arena.getGamePlayers().remove(gp);
	                gp.setAlive(true);
	                gp.setSpectator(false);
	                gp.setWinner(false);

	                gp.setKills(0);
	                gp.setFinalKills(0);

	                gp.setSwordTier(null);
	                gp.setArmorTier(null);
	                gp.setPickaxeTier(null);
	                gp.setAxeTier(null);
	                spectator.setHealth(20.0);
	                spectator.setFoodLevel(20);
	                spectator.setFireTicks(0);
	                spectator.setGameMode(GameMode.ADVENTURE);

	                spectator.teleport(
	                        Bedwars.getInstance().getLobbySpawn()
	                );

	                LobbyItems.give(spectator);
	                spectator.setScoreboard(
	                        Bukkit.getScoreboardManager()
	                                .getNewScoreboard()
	                );
	                Bedwars.getInstance()
	                        .getLobbyScoreboard()
	                        .update(spectator);

	                spectator.sendMessage("§cA partida foi finalizada.");
	            }

	            arena.getSpectators().clear();
	            arena.cancelTasks();
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

            	BWTeam team = gp.getTeam();

            	if (team != null) {
            	    team.getPlayers().remove(player.getUniqueId());
            	}
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