package com.rafaelauler.bedwars;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockTracker implements Listener {

    @EventHandler(ignoreCancelled = true)
    public void onPlace(BlockPlaceEvent e) {

        GamePlayer gp = Bedwars.getInstance()
                .getPlayerManager()
                .get(e.getPlayer());

        if (gp == null || gp.getArena() == null)
            return;

        Arena arena = gp.getArena();

        if (arena.getState() != ArenaState.PLAYING)
            return;

        if (e.getBlock().getType() == Material.BED_BLOCK)
            return;

        arena.getPlacedBlocks().add(e.getBlock().getLocation());
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onBreak(BlockBreakEvent e) {

        GamePlayer gp = Bedwars.getInstance()
                .getPlayerManager()
                .get(e.getPlayer());

        if (gp == null || gp.getArena() == null)
            return;

        Arena arena = gp.getArena();

        if (arena.getState() != ArenaState.PLAYING)
            return;

        arena.getPlacedBlocks().remove(e.getBlock().getLocation());
    }
}