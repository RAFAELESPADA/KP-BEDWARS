package com.rafaelauler.bedwars;


import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockTracker
        implements Listener {

    @EventHandler
    public void onBreak(
            BlockBreakEvent e) {

    	GamePlayer gp =
    	        Bedwars.getInstance()
    	                .getPlayerManager()
    	                .get(
    	                        e.getPlayer()
    	                );

    	Arena arena =
    	        gp.getArena();

        if(arena == null)
            return;

        arena.getBrokenBlocks()
                .add(
                        e.getBlock()
                                .getState()
                );
    }

    @EventHandler
    public void onPlace(
            BlockPlaceEvent e) {

    	GamePlayer gp =
    	        Bedwars.getInstance()
    	                .getPlayerManager()
    	                .get(
    	                        e.getPlayer()
    	                );

    	Arena arena =
    	        gp.getArena();

        if(arena == null)
            return;

        arena.getPlacedBlocks()
        .add(
                e.getBlock()
                        .getLocation()
                        .clone()
        );
    }
}
