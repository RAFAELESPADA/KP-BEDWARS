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

        if (gp == null || gp.getArena() == null)
            return;

        Arena arena = gp.getArena();

        // Só permite quebrar blocos durante a partida
        if (arena.getState() != ArenaState.PLAYING) {
            return;
        }

        // A cama é tratada pelo BedWarsListeners
        if (e.getBlock().getType() == Material.BED_BLOCK)
            return;

        // Se foi um bloco colocado durante a partida, pode quebrar
        if (arena.getPlacedBlocks().contains(e.getBlock().getLocation())) {
            return;
        }

        // Caso contrário é um bloco do mapa
        e.setCancelled(true);
    }
}