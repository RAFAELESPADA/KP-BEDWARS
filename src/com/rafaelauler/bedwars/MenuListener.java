package com.rafaelauler.bedwars;


import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;

public class MenuListener
        implements Listener {

    @EventHandler
    public void onClick(
            InventoryClickEvent e) {

        if(e.getView()
                .getTitle()
                .equals(
                        "§8Seu Perfil"
                )) {

            e.setCancelled(
                    true
            );

            return;
        }
    }
    @EventHandler
    public void onDrag(
            InventoryDragEvent e) {

        if(e.getView()
                .getTitle()
                .equals(
                        "§8Seu Perfil"
                )) {

            e.setCancelled(
                    true
            );
        }
    }
}
