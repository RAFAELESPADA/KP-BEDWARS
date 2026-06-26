package com.rafaelauler.bedwars;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class UpgradeMenu {

    public static void open(
            Player player) {

        Inventory inv =
                Bukkit.createInventory(
                        null,
                        27,
                        "Team Upgrades"
                );
        GamePlayer gp =
	            Bedwars.getInstance()
	                    .getPlayerManager()
	                    .get(player);
        inv.setItem(
                11,

                new ItemBuilder(
                        Material.IRON_SWORD
                )
                .name(
                        "§aSharpness"
                )
                .lore(
                        "",
                        "§7Custo: §b4 Diamantes",
                        "",
                        "§eClique para comprar!"
                )
                .build()
        );

		UpgradeListener u = new UpgradeListener();
        inv.setItem(
                15,

                new ItemBuilder(
                        Material.GOLD_INGOT
                )
                
                .name(
                        "§aVelocidade da forja"
                )
                .lore(
                        "",
                        "§7Custo: §b" + gp.getTeam().getForgeLevel() + " Diamantes"
                        ,
                        "§eClique para comprar!"
                )
                .build()
        );
        inv.setItem(
                13,

                new ItemBuilder(
                        Material.IRON_CHESTPLATE
                )
                .name(
                        "§aProtection"
                )
                .lore(
                        "",
                        "§7Custo: §b2 Diamantes",
                        "",
                        "§eClique para comprar!"
                )
                .build()
        );

        player.openInventory(inv);
    }
}
