package com.rafaelauler.bedwars;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
	public void onBreak(
	        BlockBreakEvent e) {

	    if(e.getBlock().getType()
	            != Material.BED_BLOCK)
	        return;

	    Player player =
	            e.getPlayer();

	    GamePlayer gp =
	            Bedwars.getInstance()
	                    .getPlayerManager()
	                    .get(player);

	    if(gp == null
	            || gp.getArena() == null)
	        return;

	    Arena arena =
	            gp.getArena();

	    if(arena.getState()
	            != ArenaState.PLAYING) {

	        e.setCancelled(true);
	        return;
	    }

	    BWTeam bedTeam =
	            arena.getTeamByBed(
	                    e.getBlock()
	                            .getLocation()
	            );

	    if(bedTeam == null)
	        return;

	    if(bedTeam == gp.getTeam()) {

	        e.setCancelled(true);

	        player.sendMessage(
	                "§cVocê não pode quebrar sua própria cama."
	        );

	        return;
	    }

	    if(!bedTeam.isBedAlive()) {

	        e.setCancelled(true);
	        return;
	    }

	    bedTeam.setBedAlive(false);

	    /*
	     * Remove as duas partes da cama
	     */
	    if(bedTeam.getBed() != null) {

	        bedTeam.getBed()
	                .getBlock()
	                .setType(
	                        Material.AIR
	                );
	    }

	    if(bedTeam.getBedHead() != null) {

	        bedTeam.getBedHead()
	                .getBlock()
	                .setType(
	                        Material.AIR
	                );
	    }

	    Bedwars.getInstance()
	            .getRewardManager()
	            .rewardBedBreak(
	                    player
	            );

	    Bukkit.broadcastMessage(
	            "§6§lBEDWARS §8» §fA cama do time "
	            + bedTeam.getColor()
	                    .getColor()
	            + bedTeam.getColor()
	                    .name()
	            + " §ffoi destruída!"
	    );
e.getBlock().getDrops().clear();
	    for(UUID uuid :
	            bedTeam.getPlayers()) {

	        Player target =
	                Bukkit.getPlayer(uuid);

	        if(target == null)
	            continue;

	        TitleAPI.send(
	                target,
	                "§c§lSUA CAMA FOI DESTRUÍDA!",
	                "§7Você não poderá mais respawnar",
	                10,
	                80,
	                10
	        );

	        target.sendMessage(
	                "§cSua cama foi destruída! Agora você possui apenas uma vida."
	        );
	    }

	    Bedwars.getInstance()
	            .getGameEndManager()
	            .checkWinner(
	                    arena
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
	public void onQuit(
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
        gp.setAlive(false);
	    Bedwars.getInstance()
        .getGameEndManager()
        .checkWinner(arena);
	    Bedwars.getInstance()
        .getStatsManager()
        .unload(
                e.getPlayer()
                        .getUniqueId()
        );
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

    if(gp.getArena() == null || gp == null
            )
        return;
	e.getDrops().removeIf(item ->

    item.getType() != Material.IRON_INGOT
    && item.getType() != Material.GOLD_INGOT
    && item.getType() != Material.DIAMOND
    && item.getType() != Material.EMERALD
);
	e.setDeathMessage(null);
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
                        	gp.getArena().addTask(
                        	        new RespawnTask(player)
                        	                .runTaskTimer(
                        	                        Bedwars.getInstance(),
                        	                        20L,
                        	                        20L
                        	                )
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
