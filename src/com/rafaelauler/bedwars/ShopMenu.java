package com.rafaelauler.bedwars;


import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class ShopMenu {

    public static void open(
            Player player) {

        Inventory inv =
                Bukkit.createInventory(
                        null,
                        54,
                        "Item Shop"
                );

        inv.setItem(
                10,
                new ItemStack(
                        Material.NETHER_STAR
                )
        );

        inv.setItem(
                19,
                new ItemStack(
                        Material.SANDSTONE
                )
        );

        inv.setItem(
                20,
                new ItemStack(
                        Material.IRON_SWORD
                )
        );

        inv.setItem(
                21,
                new ItemStack(
                        Material.CHAINMAIL_CHESTPLATE
                )
        );

        inv.setItem(
                22,
                new ItemStack(
                        Material.IRON_PICKAXE
                )
        );

        inv.setItem(
                23,
                new ItemStack(
                        Material.BOW
                )
        );

        inv.setItem(
                24,
                new ItemStack(
                        Material.BREWING_STAND_ITEM
                )
        );

        inv.setItem(
                25,
                new ItemStack(
                        Material.TNT
                )
        );

        player.openInventory(inv);
    }
}
