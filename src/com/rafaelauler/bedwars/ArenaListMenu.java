package com.rafaelauler.bedwars;


import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class ArenaListMenu {
	private static final int[] ARENA_SLOTS = {

	        11,12,13,14,15,

	        20,21,22,23,24,

	        29,30,31,32,33,

	        38,39,40,41,42
	};
    public static void open(Player player) {

        Inventory inventory =
                Bukkit.createInventory(
                        null,
                        54,
                        "§8Escolher Partida"
                );

        int index = 0;

        for (Arena arena : Bedwars.getInstance()
                .getArenaManager()
                .getArenas()
                .values()) {

        	if (index >= ARENA_SLOTS.length)
        	    break;

        	

            boolean full =
                    arena.getPlayers().size() >= arena.getMaxPlayers();

            String status;

            switch (arena.getState()) {

                case WAITING:
                    status = "§aAguardando";
                    break;

                case STARTING:
                    status = "§eIniciando";
                    break;

                case PLAYING:
                    status = "§cEm jogo";
                    break;

                case ENDING:
                    status = "§4Finalizando";
                    break;

                default:
                    status = "§7Desconhecido";
            }

            Material icon;

            if (full) {
                icon = Material.EMPTY_MAP;
            } else if (arena.getState() == ArenaState.PLAYING) {
                icon = Material.EMPTY_MAP;
            } else if (arena.getState() == ArenaState.STARTING) {
                icon = Material.EMPTY_MAP;
            } else {
                icon = Material.EMPTY_MAP;
            }

            List<String> lore = new ArrayList<>();

            lore.add("§7Estado: " + status);
            lore.add("");

            lore.add("§7Jogadores:");
            lore.add(" §f" + arena.getPlayers().size()
                    + "/" + arena.getMaxPlayers());

            lore.add("");

            lore.add(full
                    ? "§cArena cheia."
                    : "§aClique para entrar.");

            inventory.setItem(           		
                	        ARENA_SLOTS[index++],
                	             	
                    new ItemBuilder(icon)
                            .name("§e" + arena.getName())
                            .setlore(lore)
                            .build()
            );
        }

        player.openInventory(inventory);
    }
}
