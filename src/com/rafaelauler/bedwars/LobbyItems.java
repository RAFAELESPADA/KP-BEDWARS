package com.rafaelauler.bedwars;

import org.bukkit.Material;
import org.bukkit.entity.Player;

public class LobbyItems {

    public static void give(
            Player player) {

        player.getInventory()
                .setItem(
                        3,
                       new ItemBuilder(
                                Material.COMPASS
                        )
                        .name(
                                "§aSelecionar Arena"
                        )
                        .build()
                );

        player.getInventory()
                .setItem(
                        5,
                       new ItemBuilder(
                                Material.SKULL_ITEM
                        )
                        .name(
                                "§aPerfil"
                        )
                        .build()
                );
    }
}
