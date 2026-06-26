package com.rafaelauler.bedwars;


import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

public class TeamColorUtil {

    public static ItemStack createColoredChestplate(
            BWTeam team) {

        ItemStack item =
                new ItemStack(
                        Material.LEATHER_CHESTPLATE
                );

        LeatherArmorMeta meta =
                (LeatherArmorMeta)
                        item.getItemMeta();

        meta.setColor(
                getColor(team)
        );

        item.setItemMeta(meta);

        return item;
    }
    public static ItemStack createColoredHelmet(
            BWTeam team) {

        ItemStack item =
                new ItemStack(
                        Material.LEATHER_HELMET
                );

        LeatherArmorMeta meta =
                (LeatherArmorMeta)
                        item.getItemMeta();

        meta.setColor(
                getColor(team)
        );

        item.setItemMeta(meta);

        return item;
    }

    public static ItemStack createColoredLeggings(
            BWTeam team) {

        ItemStack item =
                new ItemStack(
                        Material.LEATHER_LEGGINGS
                );

        LeatherArmorMeta meta =
                (LeatherArmorMeta)
                        item.getItemMeta();

        meta.setColor(
                getColor(team)
        );

        item.setItemMeta(meta);

        return item;
    }

    public static ItemStack createColoredBoots(
            BWTeam team) {

        ItemStack item =
                new ItemStack(
                        Material.LEATHER_BOOTS
                );

        LeatherArmorMeta meta =
                (LeatherArmorMeta)
                        item.getItemMeta();

        meta.setColor(
                getColor(team)
        );

        item.setItemMeta(meta);

        return item;
    }

    private static Color getColor(
            BWTeam team) {

        switch(team.getColor()) {

            case VERMELHO:
                return Color.RED;

            case AZUL:
                return Color.BLUE;

            case VERDE:
                return Color.GREEN;

            case AMARELO:
                return Color.YELLOW;

            case ROXO:
                return Color.PURPLE;

            case BRANCO:
                return Color.WHITE;

            case ROSA:
                return Color.FUCHSIA;

            case CINZA:
                return Color.GRAY;
        }

        return Color.WHITE;
    }
}
