package com.rafaelauler.bedwars;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.util.Vector;

public class BedWarsListeners implements Listener {
	@EventHandler
	public void onPlace(BlockPlaceEvent e) {

	    if (e.getBlock().getType() != Material.TNT)
	        return;

	    GamePlayer gp = Bedwars.getInstance().getPlayerManager().get(e.getPlayer());

	    if (gp == null || gp.getArena() == null)
	        return;

	    e.setCancelled(true);

	    Location loc = e.getBlock().getLocation().add(0.5, 0, 0.5);
	    for (ItemStack item : e.getPlayer().getInventory().getContents()) {
	          if (item != null && item.getType() == Material.TNT) {
	              // Found the item, now remove one from its stack
	              if (item.getAmount() > 1) {
	                  item.setAmount(item.getAmount() - 1);
	              } else {
	                  // If only one item in the stack, set the slot to null
	            	  e.getPlayer().getInventory().removeItem(item); // Or set the specific slot to null if you know the index
	              }
	              e.getPlayer().updateInventory(); // Update the player's client-side inventory
	              break; // Stop after removing one
	          }
	      }
	    TNTPrimed tnt = loc.getWorld().spawn(loc, TNTPrimed.class);

	    tnt.setFuseTicks(40);

	    tnt.setMetadata("bedwars", new FixedMetadataValue(Bedwars.getInstance(), gp.getArena().getName()));
	}
	@EventHandler
	public void onExplode(EntityExplodeEvent e) {

	    if (!(e.getEntity() instanceof TNTPrimed))
	        return;

	    e.setCancelled(true);

	    explode(e.getLocation(), 4.5);
	}
	@EventHandler
	public void onFireball(PlayerInteractEvent e) {

	    Player player = e.getPlayer();

	    ItemStack item = player.getItemInHand();

	    if (item == null)
	        return;

	    if (item.getType() != Material.FIREBALL)
	        return;

	    e.setCancelled(true);

	    if (item.getAmount() == 1) {
	        player.setItemInHand(null);
	    } else {
	        item.setAmount(item.getAmount() - 1);
	    }

	    Fireball fireball = player.launchProjectile(Fireball.class);

	    fireball.setYield(0);

	    fireball.setIsIncendiary(false);

	    fireball.setVelocity(player.getLocation().getDirection().multiply(1.8));

	    fireball.setMetadata("bedwars", new FixedMetadataValue(Bedwars.getInstance(), true));
	}
	@EventHandler
	public void onFireballHit(ProjectileHitEvent e) {

	    if (!(e.getEntity() instanceof Fireball))
	        return;

	    if (!e.getEntity().hasMetadata("bedwars"))
	        return;

	    explode(e.getEntity().getLocation(), 3.5);

	    e.getEntity().remove();
	}
	private void explode(Location center, double radius) {

	    World world = center.getWorld();

	    world.playEffect(center, Effect.EXPLOSION_HUGE, 0);

	    world.playSound(center, Sound.EXPLODE, 1F, 1F);

	    Arena arena = Bedwars.getInstance()
	            .getArenaManager()
	            .getArena(world.getName());

	    if (arena == null)
	        return;

	    int r = (int) Math.ceil(radius);

	    for (int x = -r; x <= r; x++) {

	        for (int y = -r; y <= r; y++) {

	            for (int z = -r; z <= r; z++) {

	                Block block = center.clone().add(x, y, z).getBlock();

	                if (block.getType() == Material.AIR)
	                    continue;

	                if (!arena.getPlacedBlocks().contains(block.getLocation()))
	                    continue;

	                arena.getPlacedBlocks().remove(block.getLocation());

	                if (arena.getPlacedBlocks().remove(block.getLocation())) {
	                    block.setType(Material.AIR);
	                }
	            }
	        }
	    }

	    for (Player player : world.getPlayers()) {

	        double distance = player.getLocation().distance(center);

	        if (distance > radius)
	            continue;

	        Vector vector = player.getLocation().toVector()
	                .subtract(center.toVector())
	                .normalize();

	        vector.multiply(1.7);

	        vector.setY(0.9);

	        player.setVelocity(vector);

	        player.damage(4.0);
	    }
	}
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

	  
	            checkWinner(
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
	public void onQuit2(PlayerQuitEvent e) {

	    try {

	        Bukkit.getLogger().info("1");

	        Player player = e.getPlayer();

	        GamePlayer gp = Bedwars.getInstance()
	                .getPlayerManager()
	                .get(player);

	        Bukkit.getLogger().info("2");

	        if (gp == null)
	            return;

	        Arena arena = gp.getArena();

	        Bukkit.getLogger().info("3");

	        if (arena == null)
	            return;

	        ArenaLeaveManager.leave(gp);
	        gp.setAlive(false);
		    checkWinner(arena);
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
	        Bukkit.getLogger().info("4");

	    } catch (Exception ex) {
	        ex.printStackTrace();
	    }
	}
	
	public void checkWinner(Arena arena) {

	    List<BWTeam> aliveTeams = new ArrayList<>();

	    for (BWTeam team : arena.getTeams().values()) {

	        if (team.hasAlivePlayers(arena)) {
	            aliveTeams.add(team);
	        }
	    }

	    if (aliveTeams.size() != 1)
	        return;


	    Bedwars.getInstance().getGameEndManager().endGame(arena, aliveTeams.get(0));
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
