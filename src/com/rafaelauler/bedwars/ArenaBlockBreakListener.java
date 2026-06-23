package com.rafaelauler.bedwars;


import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class ArenaBlockBreakListener
        implements Listener {

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onBreak(
	        BlockBreakEvent e) {

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

	    if(e.getBlock().getType()
	            == Material.BED_BLOCK)
	        return;

	    Location loc =
	            e.getBlock()
	                    .getLocation();

	    Location found = null;
	    for(Location placed : arena.getPlacedBlocks()) {

	        if(placed.getWorld().equals(loc.getWorld())
	                && placed.getBlockX() == loc.getBlockX()
	                && placed.getBlockY() == loc.getBlockY()
	                && placed.getBlockZ() == loc.getBlockZ()) {

	            found = placed;
	            break;
	        }
	    }

	    if(found != null) {

	        arena.getPlacedBlocks().remove(found);

	        e.setCancelled(false);

	        return;
	    }

	    e.setCancelled(true);
	}
	
}
