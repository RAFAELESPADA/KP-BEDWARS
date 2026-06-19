package com.rafaelauler.bedwars;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class LobbyItemListener
        implements Listener {

    @EventHandler
    public void onInteract(
            PlayerInteractEvent e) {

        if(e.getItem() == null)
            return;

        if(e.getItem()
                .getType()
                != Material.COMPASS)
            return;

        ArenaSelectorMenu.open(
                e.getPlayer()
        );
        if(e.getItem()
                .getType()
                == Material.SKULL_ITEM) {

            StatsMenu.open(
                    e.getPlayer()
            );
        }
    }
}