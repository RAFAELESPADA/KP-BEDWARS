package com.rafaelauler.bedwars;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class SpectatorMenu {

    public static void open(
            Player spectator,
            Arena arena) {

        Inventory inventory =
                Bukkit.createInventory(
                        null,
                        54,
                        "Jogadores"
                );

        for(Player target :
                arena.getPlayers()) {

            if(target == null)
                continue;

            ItemStack skull =
                    new ItemStack(
                            Material.SKULL_ITEM,
                            1,
                            (short) 3
                    );

            SkullMeta meta =
                    (SkullMeta)
                            skull.getItemMeta();

            meta.setOwner(
                    target.getName()
            );

            meta.setDisplayName(
                    "§a" +
                    target.getName()
            );

            skull.setItemMeta(meta);

            inventory.addItem(skull);
        }

        spectator.openInventory(
                inventory
        );
    }
}
