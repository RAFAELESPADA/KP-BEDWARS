package com.rafaelauler.bedwars;


import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class ArenaBlockBreakListener
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

        if(gp == null)
            return;

        Arena arena =
                gp.getArena();

        if(arena == null)
            return;

        if(e.getBlock()
                .getType()
                == Material.BED_BLOCK)
            return;

        if(!arena.getPlacedBlocks()
                .contains(
                        e.getBlock()
                                .getLocation().getBlock()
                                .getLocation()
                )) {

            e.setCancelled(
                    true
            );

            return;
        }

        arena.getPlacedBlocks()
                .remove(
                        e.getBlock()
                                .getLocation().getBlock()
                                .getLocation()
                );
    }
}
