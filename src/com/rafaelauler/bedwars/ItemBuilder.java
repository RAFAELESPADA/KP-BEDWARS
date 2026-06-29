package com.rafaelauler.bedwars;


import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;

public class ItemBuilder {

    private final ItemStack item;
    private final ItemMeta meta;

    public ItemBuilder(Material material) {

        item = new ItemStack(material);
        meta = item.getItemMeta();
    }

    public ItemBuilder(Material material,
                       int amount) {

        item = new ItemStack(
                material,
                amount
        );

        meta = item.getItemMeta();
    }

    public ItemBuilder name(
            String name) {

        meta.setDisplayName(name);

        return this;
    }
    public ItemBuilder addLore(String... lore) {

        List<String> currentLore = meta.hasLore()
                ? meta.getLore()
                : new java.util.ArrayList<String>();

        currentLore.addAll(Arrays.asList(lore));

        meta.setLore(currentLore);

        return this;
    }
    public ItemBuilder lore(
            String... lore) {

        meta.setLore(
                Arrays.asList(lore)
        );

        return this;
    }

    public ItemBuilder setlore(
            List<String> lore2) {

        meta.setLore(lore2);
        

        return this;
    }

    public ItemStack build() {

        item.setItemMeta(meta);

        return item;
    }
}
