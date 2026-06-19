package com.rafaelauler.bedwars;

import java.util.Iterator;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;

public class TNTExplodeListener
        implements Listener {

    @EventHandler
    public void onExplode(
            EntityExplodeEvent e) {

        Entity entity =
                e.getEntity();

        if(!(entity instanceof TNTPrimed))
            return;

        BWTeam owner =
                Bedwars.getInstance()
                        .getTntManager()
                        .getTeam(
                                entity.getUniqueId()
                        );

        if(owner == null)
            return;

        Arena arena =
                getArena(owner);

        if(arena == null)
            return;

        Iterator<Block> iterator =
                e.blockList()
                        .iterator();

        while(iterator.hasNext()) {

            Block block =
                    iterator.next();

            /*
             * Só deixa quebrar blocos
             * colocados durante a partida.
             */
            if(block.getType() ==
                    Material.BED_BLOCK) {

                continue;
            }
            if(!arena.getPlacedBlocks()
                    .contains(
                            block.getLocation().getBlock()
                            .getLocation()
                    )) {

                iterator.remove();

                continue;
            }

            arena.getPlacedBlocks()
                    .remove(
                            block.getLocation().getBlock()
                            .getLocation()
                    );
        }

        Bedwars.getInstance()
                .getTntManager()
                .remove(
                        entity.getUniqueId()
                );
    }

    private Arena getArena(
            BWTeam team) {

        for(Arena arena :
                Bedwars.getInstance()
                        .getArenaManager()
                        .getArenas()
                        .values()) {

            if(arena.getTeams()
                    .containsValue(
                            team
                    )) {

                return arena;
            }
        }

        return null;
    }
}