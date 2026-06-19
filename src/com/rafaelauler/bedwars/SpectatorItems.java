package com.rafaelauler.bedwars;


import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class SpectatorItems {

    public static ItemStack getTeleportItem() {

        ItemStack item =
                new ItemStack(Material.COMPASS);

        ItemMeta meta =
                item.getItemMeta();

        meta.setDisplayName(
                "§aTeletransportar"
        );

        item.setItemMeta(meta);

        return item;
    }

    public static ItemStack getLeaveItem() {

        ItemStack item =
                new ItemStack(Material.BED);

        ItemMeta meta =
                item.getItemMeta();

        meta.setDisplayName(
                "§cSair da Arena"
        );

        item.setItemMeta(meta);

        return item;
    }
}
