package com.rafaelauler.bedwars;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class StatsMenu {

    public static void open(
            Player player) {

        PlayerStats stats =
                Bedwars.getInstance()
                        .getStatsManager()
                        .getStats(
                                player.getUniqueId()
                        );

        Inventory inventory =
                Bukkit.createInventory(
                        null,
                        27,
                        "§8Seu Perfil"
                );

        inventory.setItem(
                13,
                SkullBuilder.of(player)
                        .name(
                                "§6" +
                                player.getName()
                        )
                        .lore(
                                "§7Level: §e"
                                + stats.getLevel()
                                + "✫"
                        )
                        .lore(
                                "§7Coins: §6"
                                + stats.getCoins()
                        )
                        .build()
        );

        inventory.setItem(
                10,

                new ItemBuilder(
                        Material.DIAMOND_SWORD
                )

                .name(
                        "§cCombate"
                )

                .lore(
                        "§7Kills: §f"
                        + stats.getKills()
                )

                .lore(
                        "§7Final Kills: §f"
                        + stats.getFinalKills()
                )

                .lore(
                        "§7Deaths: §f"
                        + stats.getDeaths()
                )

                .lore(
                        "§7KDR: §a"
                        + String.format(
                                "%.2f",
                                stats.getKDR()
                        )
                )

                .lore(
                        "§7FKDR: §a"
                        + String.format(
                                "%.2f",
                                stats.getFKDR()
                        )
                )

                .build()
        );

        inventory.setItem(
                12,

                new ItemBuilder(
                        Material.BED
                )

                .name(
                        "§eBedWars"
                )

                .lore(
                        "§7Beds Broken: §f"
                        + stats.getBedsBroken()
                )

                .build()
        );

        inventory.setItem(
                14,

                new ItemBuilder(
                        Material.EMERALD
                )

                .name(
                        "§aVitórias"
                )

                .lore(
                        "§7Wins: §f"
                        + stats.getWins()
                )

                .lore(
                        "§7Losses: §f"
                        + stats.getLosses()
                )

                .lore(
                        "§7Win Rate: §a"
                        + String.format(
                                "%.2f",
                                stats.getWinRate()
                        )
                                + "%"
                )

                .build()
        );

        inventory.setItem(
                16,

                new ItemBuilder(
                        Material.BLAZE_POWDER
                )

                .name(
                        "§6Progressão"
                )

                .lore(
                        "§7Level: §e"
                        + stats.getLevel()
                )

                .lore(
                        "§7XP: §f"
                        + stats.getExperience()
                )

                .lore(
                        "§7Coins: §6"
                        + stats.getCoins()
                )

                .lore(
                        "§7Winstreak: §f"
                        + stats.getWinstreak()
                )

                .build()
        );

        player.openInventory(
                inventory
        );
    }
}