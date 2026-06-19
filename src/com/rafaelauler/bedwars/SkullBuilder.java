package com.rafaelauler.bedwars;


import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class SkullBuilder {

    private final ItemStack item;

    private final SkullMeta meta;

    public SkullBuilder(
            Player player) {

        item =
                new ItemStack(
                        Material.SKULL_ITEM,
                        1,
                        (short) 3
                );

        meta =
                (SkullMeta)
                        item.getItemMeta();

        meta.setOwner(
                player.getName()
        );
    }

    public static SkullBuilder of(
            Player player) {

        return new SkullBuilder(
                player
        );
    }

    public SkullBuilder name(
            String name) {

        meta.setDisplayName(
                name
        );

        return this;
    }

    public SkullBuilder lore(
            String line) {

        List<String> lore =
                meta.hasLore()

                        ? meta.getLore()

                        : new ArrayList<>();

        lore.add(line);

        meta.setLore(
                lore
        );

        return this;
    }

    public ItemStack build() {

        item.setItemMeta(
                meta
        );

        return item;
    }
}
