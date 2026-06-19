package com.rafaelauler.bedwars;

import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

public class LobbyDropListener
        implements Listener {

    @EventHandler
    public void onDrop(
            PlayerDropItemEvent e) {

        GamePlayer gp =
                Bedwars.getInstance()
                        .getPlayerManager()
                        .get(
                                e.getPlayer()
                        );

        /*
         * Está em partida
         */
        if(gp != null
                && gp.getArena() != null)
            return;
if (e.getPlayer().getGameMode() == GameMode.CREATIVE) {
	return;
}
        e.setCancelled(true);
    }
    @EventHandler
    public void onPickup(
            PlayerPickupItemEvent e) {

        GamePlayer gp =
                Bedwars.getInstance()
                        .getPlayerManager()
                        .get(
                                e.getPlayer()
                        );

        if(gp != null
                && gp.getArena() != null)
            return;
        if (e.getPlayer().getGameMode() == GameMode.CREATIVE) {
        	return;
        }
        e.setCancelled(true);
    }
    

@EventHandler
public void onClick(
        InventoryClickEvent e) {

    if(!(e.getWhoClicked()
            instanceof org.bukkit.entity.Player))
        return;

    org.bukkit.entity.Player player =
            (org.bukkit.entity.Player)
                    e.getWhoClicked();

    GamePlayer gp =
            Bedwars.getInstance()
                    .getPlayerManager()
                    .get(player);

    if(gp != null
            && gp.getArena() != null)
        return;
    if (player.getGameMode() == GameMode.CREATIVE) {
    	return;
    }
    e.setCancelled(true);
}

@EventHandler
public void onDrag(
        InventoryDragEvent e) {

    if(!(e.getWhoClicked()
            instanceof org.bukkit.entity.Player))
        return;

    org.bukkit.entity.Player player =
            (org.bukkit.entity.Player)
                    e.getWhoClicked();

    GamePlayer gp =
            Bedwars.getInstance()
                    .getPlayerManager()
                    .get(player);

    if(gp != null
            && gp.getArena() != null)
        return;
    if (player.getGameMode() == GameMode.CREATIVE) {
    	return;
    }
    e.setCancelled(true);
}
}