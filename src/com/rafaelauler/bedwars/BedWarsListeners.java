package com.rafaelauler.bedwars;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class BedWarsListeners implements Listener {

	@EventHandler
	public void onBreak(BlockBreakEvent e) {

	    if(e.getBlock().getType() != Material.BED_BLOCK)
	        return;

	    Player player = e.getPlayer();

	    GamePlayer gp =
	            Bedwars.getInstance()
	                    .getPlayerManager()
	                    .get(player);

	    if(gp.getArena() == null)
	        return;

	    Arena arena = gp.getArena();

	    BWTeam bedTeam =
	            arena.getTeamByBed(
	                    e.getBlock().getLocation()
	            );

	    if(bedTeam == null)
	        return;

	    if(bedTeam == gp.getTeam()) {

	        e.setCancelled(true);

	        player.sendMessage(
	                "§cVocê não pode quebrar sua cama."
	        );

	        return;
	    }

	    bedTeam.setBedAlive(false);
	    Bedwars.getInstance()
        .getRewardManager()
        .rewardBedBreak(
                player
        );
	    Bukkit.broadcastMessage(
	            "§cA cama do time "
	            + bedTeam.getColor().name()
	            + " foi destruída!"
	    );
	}
	
	@EventHandler
	public void onQuit(
	        PlayerQuitEvent e) {

	    PlayerStats stats =
	            Bedwars.getInstance()
	                    .getStatsManager()
	                    .getStats(
	                            e.getPlayer()
	                                    .getUniqueId()
	                    );

	    Bukkit.getScheduler()
	            .runTaskAsynchronously(
	                    Bedwars.getInstance(),
	                    () -> Bedwars
	                            .getInstance()
	                            .getStatsManager()
	                            .save(stats)
	            );
	    GamePlayer gp =
                Bedwars.getInstance()
                        .getPlayerManager()
                        .get(
                                e.getPlayer()
                        );

        if(gp == null)
            return;

        Arena arena =
                gp.getArena();
	    if(arena == null)
            return;

        ArenaLeaveManager.leave(
                gp
        );
	}
	@EventHandler
	public void onJoin(
	        PlayerJoinEvent e) {

	    PlayerStats stats =
	            Bedwars.getInstance()
	                    .getStatsManager()
	                    .getStats(
	                            e.getPlayer()
	                                    .getUniqueId()
	                    );

	    stats.setName(
	            e.getPlayer()
	                    .getName()
	    );
	}
	@EventHandler
	public void onQuit2(
	        PlayerQuitEvent e) {

	    Bedwars.getInstance()
	            .getStatsManager()
	            .unload(
	                    e.getPlayer()
	                            .getUniqueId()
	            );
	}
	@EventHandler
	public void onQuit5(
	        PlayerQuitEvent e) {

	    Player player =
	            e.getPlayer();

	    GamePlayer gp =
	            Bedwars.getInstance()
	                    .getPlayerManager()
	                    .get(player);

	    if(gp == null)
	        return;

	    Arena arena =
	            gp.getArena();

	    if(arena == null)
	        return;

	    ArenaLeaveManager.leave(
	            gp
	    );

	    checkWinner(
	                    arena
	            );
	}
	public void checkWinner(
	        Arena arena) {

	    List<BWTeam> alive =
	            new ArrayList<>();

	    for(BWTeam team :
	            arena.getTeams()
	                    .values()) {

	        if(team.getPlayers()
	                .isEmpty())
	            continue;

	        alive.add(team);
	    }

	    if(alive.size() != 1)
	        return;


	    Bedwars.getInstance().getGameEndManager().checkWinner(arena);
	    
	}
@EventHandler
public void onDeath(PlayerDeathEvent e) {

    Player player = e.getEntity();

    GamePlayer gp =
            Bedwars.getInstance()
                    .getPlayerManager()
                    .get(player);

    if(gp.getArena() == null)
        return;

    Bukkit.getScheduler()
            .runTaskLater(
                    Bedwars.getInstance(),
                    () -> {

                        if(gp.getTeam()
                                .isBedAlive()) {
                        	Bedwars.getInstance()
                            .getKillManager()
                            .handleDeath(
                                    player,
                                    gp
                            );
                        	new RespawnTask(player)
                            .runTaskTimer(
                                    Bedwars.getInstance(),
                                    20L,
                                    20L
                            );

                        } else {

                            EliminationTask
                                    .eliminate(
                                            player,
                                            gp
                                    );
                        }

                    },
                    2L
            );
}
}
