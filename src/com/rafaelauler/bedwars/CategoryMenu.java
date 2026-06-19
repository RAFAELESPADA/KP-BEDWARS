package com.rafaelauler.bedwars;


import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class CategoryMenu {

    public static void open(
            Player player,
            ShopCategory category) {

        Inventory inventory =
                Bukkit.createInventory(
                        null,
                        54,
                        category.name()
                );

        for(ShopItem item :
                Bedwars.getInstance()
                        .getShopManager()
                        .getItems()) {

            if(item.getCategory()
                    != category)
                continue;

            inventory.addItem(
                    item.getDisplay()
            );
        }

        player.openInventory(
                inventory
        );
    }
}
