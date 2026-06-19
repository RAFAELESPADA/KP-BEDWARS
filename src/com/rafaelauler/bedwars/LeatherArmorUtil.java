package com.rafaelauler.bedwars;


import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

public class LeatherArmorUtil {

    public static void equip(
            Player player,
            TeamColor color) {

        player.getInventory()
                .setHelmet(
                        create(
                                Material.LEATHER_HELMET,
                                color.getLeatherColor()
                        )
                );

        player.getInventory()
                .setChestplate(
                        create(
                                Material.LEATHER_CHESTPLATE,
                                color.getLeatherColor()
                        )
                );

        player.getInventory()
                .setLeggings(
                        create(
                                Material.LEATHER_LEGGINGS,
                                color.getLeatherColor()
                        )
                );

        player.getInventory()
                .setBoots(
                        create(
                                Material.LEATHER_BOOTS,
                                color.getLeatherColor()
                        )
                );
    }

    private static ItemStack create(
            Material material,
            Color color) {

        ItemStack item =
                new ItemStack(
                        material
                );

        LeatherArmorMeta meta =
                (LeatherArmorMeta)
                        item.getItemMeta();

        meta.setColor(
                color
        );

        item.setItemMeta(
                meta
        );

        return item;
    }
}