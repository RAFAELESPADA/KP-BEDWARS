package com.rafaelauler.bedwars;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class ArenaBlockBreakListener implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onBreak(BlockBreakEvent e) {

        GamePlayer gp = Bedwars.getInstance()
                .getPlayerManager()
                .get(e.getPlayer());

        if (gp == null || gp.getArena() == null) {
            e.setCancelled(true);
            return;
        }

        Arena arena = gp.getArena();

        if (arena.getState() != ArenaState.PLAYING) {
            e.setCancelled(true);
            return;
        }

        // A cama é tratada pelo BedWarsListeners
        if (e.getBlock().getType() == Material.BED_BLOCK)
            return;

        // Bloco colocado durante a partida
        if (arena.getPlacedBlocks().contains(e.getBlock().getLocation()))
            return;

        // Impede quebrar blocos do mapa
        e.setCancelled(true);
    }
}