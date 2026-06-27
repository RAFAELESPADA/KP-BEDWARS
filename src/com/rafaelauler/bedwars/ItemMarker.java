package com.rafaelauler.bedwars;


import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemMarker {

    private static final String MARK =
            ChatColor.COLOR_CHAR + "0"
            + ChatColor.COLOR_CHAR + "1"
            + ChatColor.COLOR_CHAR + "2"
            + ChatColor.COLOR_CHAR + "3"
            + "PERMANENT";

    public static ItemStack mark(ItemStack item) {

        ItemMeta meta = item.getItemMeta();

        List<String> lore =
                meta.hasLore()
                        ? new ArrayList<>(meta.getLore())
                        : new ArrayList<>();

        lore.add(MARK);

        meta.setLore(lore);

        item.setItemMeta(meta);

        return item;
    }

    public static boolean isMarked(ItemStack item) {

        if(item == null)
            return false;

        if(!item.hasItemMeta())
            return false;

        if(!item.getItemMeta().hasLore())
            return false;

        return item.getItemMeta()
                .getLore()
                .contains(MARK);
    }
}
