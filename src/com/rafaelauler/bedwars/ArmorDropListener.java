package com.rafaelauler.bedwars;


import org.bukkit.Material;
import org.bukkit.entity.Player;
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
        Player player =
	            e.getPlayer();

	    GamePlayer gp =
	            Bedwars.getInstance()
	                    .getPlayerManager()
	                    .get(player);

	    if(gp == null
	            || gp.getArena() == null)
	        return;
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
