package com.rafaelauler.bedwars;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class UpgradeMenu {

    public static void open(
            Player player) {

        Inventory inv =
                Bukkit.createInventory(
                        null,
                        27,
                        "Team Upgrades"
                );

        inv.setItem(
                13,

                new ItemBuilder(
                        Material.IRON_SWORD
                )
                .name(
                        "§aSharpness"
                )
                .lore(
                        "",
                        "§7Cost: §b4 Diamonds",
                        "",
                        "§eClick to purchase!"
                )
                .build()
        );

        inv.setItem(
                15,

                new ItemBuilder(
                        Material.IRON_CHESTPLATE
                )
                .name(
                        "§aProtection"
                )
                .lore(
                        "",
                        "§7Cost: §b2 Diamonds",
                        "",
                        "§eClick to purchase!"
                )
                .build()
        );

        player.openInventory(inv);
    }
}
