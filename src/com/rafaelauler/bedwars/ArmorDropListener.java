package com.rafaelauler.bedwars;


import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class ArmorDropListener
        implements Listener {

    @EventHandler
    public void onDrop(
            PlayerDropItemEvent e) {

        Material material =
                e.getItemDrop()
                        .getItemStack()
                        .getType();

        if(material.name()
                .contains(
                        "HELMET"
                )
                || material.name()
                .contains(
                        "CHESTPLATE"
                )
                || material.name()
                .contains(
                        "LEGGINGS"
                )
                || material.name()
                .contains(
                        "BOOTS"
                )) {

            e.setCancelled(
                    true
            );
        }
    }
}
