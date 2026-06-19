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

        Arena arena =
                Bedwars.getInstance().getArenaManager().getArena(
                        e.getPlayer()
                );

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

    	 Arena arena =
                 Bedwars.getInstance().getArenaManager().getArena(
                         e.getPlayer()
                 );

        if(arena == null)
            return;

        arena.getPlacedBlocks()
                .add(
                        e.getBlock()
                                .getLocation().getBlock()
                                .getLocation()
                );
    }
}
