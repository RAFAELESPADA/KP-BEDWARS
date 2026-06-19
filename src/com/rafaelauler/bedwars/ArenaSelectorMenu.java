package com.rafaelauler.bedwars;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class ArenaSelectorMenu {

    public static void open(
            Player player) {

        Inventory inventory =
                Bukkit.createInventory(
                        null,
                        27,
                        "§8Selecionar Arena"
                );

        int slot = 0;
        String status;
        
        for(Arena arena :
        	
                Bedwars.getInstance()
                        .getArenaManager()
                        .getArenas()
                        .values()) {
        	boolean full =
        	        arena.getPlayers()
        	                .size()
        	        >= arena.getMaxPlayers();
        	switch(arena.getState()) {

            case WAITING:

                status =
                        "§aAGUARDANDO";

                break;

            case STARTING:

                status =
                        "§eINICIANDO";

                break;

            case PLAYING:

                status =
                        "§cEM JOGO";

                break;

            default:

                status =
                        "§7DESCONHECIDO";
        }
        	double percentage =
        	        (arena.getPlayers().size()
        	                * 100.0)
        	        / arena.getMaxPlayers();
        	String bar;

        	if(percentage >= 100) {

        	    bar = "§c██████████";

        	} else if(percentage >= 75) {

        	    bar = "§6███████░░░";

        	} else if(percentage >= 50) {

        	    bar = "§e█████░░░░░";

        	} else {

        	    bar = "§a███░░░░░░░";
        	}
        	Material material;
        	if(full) {

        	    material =
        	            Material.REDSTONE_BLOCK;

        	} else if(arena.getState()
        	        == ArenaState.PLAYING) {

        	    material =
        	            Material.BEDROCK;

        	} else {

        	    material =
        	            Material.BED;
        	}
            ItemStack item =
                    new ItemBuilder(
                            material
                    )
                    .name(
                            "§a" +
                            arena.getName())
                    .lore(
                            "§7Jogadores: §f"
                            + arena.getPlayers()
                                    .size()
                            + "/"
                            + arena.getMaxPlayers()
                    )
                    .lore(
                            "§7Status: "
                            + status
                    )
                    .lore("")
                    .lore(
                            "§7Vagas: §a"
                            + (
                            arena.getMaxPlayers()
                            - arena.getPlayers().size()
                            ))
                    
                    .lore(
                            "§aClique para entrar"
                    )
                    .lore(
                            "§7Lotação: " + bar
                    )
                    .build();

            inventory.setItem(
                    slot++,
                    item
            );
        }

        player.openInventory(
                inventory
        );
    }
}