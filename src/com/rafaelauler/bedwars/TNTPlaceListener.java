package com.rafaelauler.bedwars;

import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class TNTPlaceListener
        implements Listener {

    @EventHandler
    public void onPlace(
            BlockPlaceEvent e) {

        if(e.getBlock()
                .getType()
                != org.bukkit.Material.TNT)
            return;

        Player player =
                e.getPlayer();

        GamePlayer gp =
                Bedwars.getInstance()
                        .getPlayerManager()
                        .get(player);

        if(gp == null)
            return;

        e.getBlock()
                .setType(
                        org.bukkit.Material.AIR
                );

        TNTPrimed tnt =
                e.getBlock()
                        .getWorld()
                        .spawn(
                                e.getBlock()
                                        .getLocation()
                                        .add(
                                                0.5,
                                                0,
                                                0.5
                                        ),
                                TNTPrimed.class
                        );

        Bedwars.getInstance()
                .getTntManager()
                .register(
                        tnt.getUniqueId(),
                        gp.getTeam()
                );
    }
}
