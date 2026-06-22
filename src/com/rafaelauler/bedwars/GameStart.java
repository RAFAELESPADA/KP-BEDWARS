package com.rafaelauler.bedwars;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class GameStart {

	public static void start(
	        Arena arena) {

	    arena.setStarting(false);

	    arena.setState(
	            ArenaState.PLAYING
	    );
	    for(BWTeam team :
	        arena.getTeams().values()) {

	    if(!team.isActive()) {

	        team.setBedAlive(false);

	        continue;
	    }

	    team.setBedAlive(true);
	}
	    assignTeams(
	            arena
	    );

	    teleportPlayers(
	            arena
	    );

	    Bedwars.getInstance()
	            .getGeneratorManager()
	            .start(
	                    arena
	            );
	}
    private static void assignTeams(
            Arena arena) {
    	for(BWTeam team :
            arena.getTeams()
                    .values()) {

        team.getPlayers()
                .clear();
    }
        List<Player> players =
                new ArrayList<>(
                        arena.getPlayers()
                );

        Collections.shuffle(
                players
        );

        List<BWTeam> teams =
                new ArrayList<>(
                        arena.getTeams()
                                .values()
                );

        int index = 0;

        for(Player player :
                players) {

            BWTeam team =
                    teams.get(
                            index
                            % teams.size()
                    );

            team.getPlayers()
                    .add(
                            player.getUniqueId()
                    );

            GamePlayer gp =
                    Bedwars.getInstance()
                            .getPlayerManager()
                            .get(player);

            gp.setTeam(team);

            index++;
            player.sendMessage(
                    "§aVocê entrou no time "
                    + team.getColor()
                            .name()
            );
        }
    }
    private static void teleportPlayers(
            Arena arena) {

        for(Player player :
                arena.getPlayers()) {

            GamePlayer gp =
                    Bedwars.getInstance()
                            .getPlayerManager()
                            .get(player);

            BWTeam team =
                    gp.getTeam();

            player.teleport(
                    team.getSpawn()
            );

            player.getInventory()
                    .clear();

            player.setHealth(20);

            player.setFoodLevel(20);

            player.setGameMode(
                    GameMode.SURVIVAL);
            LeatherArmorUtil.equip(
                    player,
                    team.getColor()
            );
            giveStartingItems(
                    player
            );
        }
    }
    private static void giveStartingItems(
            Player player) {

        player.getInventory()
                .addItem(
                        new ItemStack(
                                Material.WOOD_SWORD
                        )
                );
        
    }
}
