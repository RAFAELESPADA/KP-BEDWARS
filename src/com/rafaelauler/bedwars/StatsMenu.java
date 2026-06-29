package com.rafaelauler.bedwars;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.user.User;
public class StatsMenu {

    public static void open(
            Player player) {
    	LuckPerms lp = LuckPermsProvider.get();

    	User user = lp.getPlayerAdapter(Player.class)
    	        .getUser(player);

    	String prefix = "";

    	if (user.getCachedData().getMetaData().getPrefix() != null) {
    	    prefix = user.getCachedData().getMetaData().getPrefix();
    	}
        PlayerStats stats =
                Bedwars.getInstance()
                        .getStatsManager()
                        .getStats(
                                player.getUniqueId()
                        );

        Inventory inventory =
                Bukkit.createInventory(
                        null,
                        54,
                        "§8Seu Perfil"
                );

        inventory.setItem(4,
        	    SkullBuilder.of(player)
        	            .name("§6§l" + player.getName())
        	            .lore(
        	                    "").lore("§7Cargo: " + prefix).lore(
        	                    "§7Nível §8» §e" + stats.getLevel() + "✫").lore(
        	                    "§7XP §8» §b" + stats.getExperience()).lore(
        	                    "").lore(
        	                    "§7Coins §8» §6" + stats.getCoins()).lore(
        	                    "§7Winstreak §8» §a" + stats.getWinstreak()).lore("").lore("§eBedWars Profile"
        	            )
        	            .build()
        	);
        ItemStack glass = new ItemBuilder(Material.STAINED_GLASS_PANE, 1)
        		
                .name(" ")
                .build();
        int[] border = {
        0,1,2,3,5,6,7,8,
        9,17,
        18,26,
        27,35,
        36,44,
        45,46,47,48,50,51,52,53
        };

        for(int slot : border)
            inventory.setItem(slot, glass);

        inventory.setItem(
                19,

                new ItemBuilder(Material.DIAMOND_SWORD)

                        .name("§c§lCombate")

                        .addLore(
                                ""
                        )

                        .addLore(
                                "§7Kills: §f" + stats.getKills()
                        )

                        .addLore(
                                "§7Final Kills: §f" + stats.getFinalKills()
                        )

                        .addLore(
                                "§7Deaths: §f" + stats.getDeaths()
                        )

                        .addLore(
                                "§7KDR: §a" +
                                        String.format("%.2f", stats.getKDR())
                        )

                        .addLore(
                                "§7FKDR: §a" +
                                        String.format("%.2f", stats.getFKDR())
                        )

                        .addLore(
                                ""
                        )
                        .addLore(
                        	    "§7Maior Combo: §6"
                        	    + stats.getHighestCombo()
                        	)
                        .addLore(
                                "§eEstatísticas de combate."
                        )

                        .build()
        );
        inventory.setItem(
                20,

                new ItemBuilder(Material.BED)

                        .addLore("§e§lBedWars")

                        .addLore(
                                ""
                        )

                        .addLore(
                                "§7Camas quebradas: §f"
                                        + stats.getBedsBroken()
                        )

                        .addLore(
                                "§7Final Kills: §f"
                                        + stats.getFinalKills()
                        )

                        .addLore(
                                "§7Kills: §f"
                                        + stats.getKills()
                        )

                        .addLore(
                                ""
                        )

                        .addLore(
                                "§eDesempenho como atacante."
                        )

                        .build()
        );
        inventory.setItem(
                22,

                new ItemBuilder(Material.EMERALD)

                        .name("§a§lVitórias")

                        .addLore(
                                ""
                        )

                        .addLore(
                                "§7Vitórias: §f"
                                        + stats.getWins()
                        )

                        .addLore(
                                "§7Derrotas: §f"
                                        + stats.getLosses()
                        )

                        .addLore(
                                "§7Partidas: §f"
                                        + (stats.getWins() + stats.getLosses())
                        )

                        .addLore(
                                "§7Win Rate: §a"
                                        + String.format("%.2f",
                                        stats.getWinRate())
                                        + "%"
                        )

                        .addLore(
                                ""
                        )

                        .addLore(
                                "§eSeu desempenho geral."
                        )

                        .build()
        );
        int xp = stats.getExperience();

        int required = stats.getLevel() * 100;

        double percent = required <= 0 ? 100 :
                Math.min(100D, (xp * 100D) / required);

        StringBuilder bar = new StringBuilder();

        for (int i = 0; i < 20; i++) {

            if (i < percent / 5)
                bar.append("§a■");
            else
                bar.append("§7■");
        }

        inventory.setItem(
                24,

                new ItemBuilder(Material.BLAZE_POWDER)

                        .name("§6§lProgressão")

                        .addLore(
                                ""
                        )

                        .addLore(
                                "§7Nível: §e"
                                        + stats.getLevel()
                                        + "✫"
                        )

                        .addLore(
                                "§7XP: §b"
                                        + xp
                                        + " / "
                                        + required
                        )

                        .addLore(
                                ""
                        )

                        .addLore(
                                bar.toString()
                        )

                        .addLore(
                                "§7Progresso: §a"
                                        + String.format("%.0f", percent)
                                        + "%"
                        )

                        .addLore(
                                ""
                        )

                        .addLore(
                                "§7Coins: §6"
                                        + stats.getCoins()
                        )

                        .addLore(
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