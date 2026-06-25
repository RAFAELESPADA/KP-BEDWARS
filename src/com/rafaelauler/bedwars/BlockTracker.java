package com.rafaelauler.bedwars;

import org.bukkit.event.EventHandler;
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

        arena.getPlacedBlocks().add(
                e.getBlock().getLocation().clone()
        );
    }

    @EventHandler(ignoreCancelled = true)
    public void onBreak(BlockBreakEvent e) {

        GamePlayer gp = Bedwars.getInstance()
                .getPlayerManager()
                .get(e.getPlayer());

        if (gp == null || gp.getArena() == null)
            return;

        Arena arena = gp.getArena();

        // Se o bloco foi colocado durante a partida,
        // remove da lista ao ser quebrado.
        arena.getPlacedBlocks().remove(
                e.getBlock().getLocation()
        );
    }
}