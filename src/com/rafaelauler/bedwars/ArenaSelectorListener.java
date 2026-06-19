package com.rafaelauler.bedwars;


import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class ArenaSelectorListener
        implements Listener {

    @EventHandler
    public void onClick(
            InventoryClickEvent e) {

        if(e.getInventory()
                == null)
            return;

        if(!e.getView()
                .getTitle()
                .equals(
                        "§8Selecionar Arena"
                ))
            return;

        e.setCancelled(
                true
        );

        if(e.getCurrentItem()
                == null)
            return;

        String arenaName =
                ChatColor.stripColor(
                        e.getCurrentItem()
                                .getItemMeta()
                                .getDisplayName()
                );

        Arena arena =
                Bedwars.getInstance()
                        .getArenaManager()
                        .getArena(
                                arenaName
                        );

        if(arena == null)
            return;

        ArenaJoinManager.join(
                (org.bukkit.entity.Player)
                        e.getWhoClicked(),

                arena
        );

        e.getWhoClicked()
                .closeInventory();
    }
}
