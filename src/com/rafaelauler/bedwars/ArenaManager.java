package com.rafaelauler.bedwars;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

public class ArenaManager {

    private final Map<String, Arena> arenas;
    private final ArenaFile arenaFile;

    public ArenaManager() {

        this.arenas = new HashMap<>();
        this.arenaFile = new ArenaFile();

        loadArenas();
    }

    private void loadArenas() {

        if (arenaFile.getConfig().getKeys(false).isEmpty())
            return;

        for (String arenaName : arenaFile.getConfig().getKeys(false)) {

            Arena arena = new Arena(arenaName);

            arena.setLobby(
                    arenaFile.getLocation(
                            arenaName + ".lobby"
                    )
            );

            arena.setSpectator(
                    arenaFile.getLocation(
                            arenaName + ".spectator"
                    )
            );
            ConfigurationSection section =
                    arenaFile.getConfig()
                            .getConfigurationSection(
                                    arenaName
                                    + ".Generators"
                            );
            if(section != null) {

                for(String key :
                        section.getKeys(false)) {

                    GeneratorType type =
                            GeneratorType.valueOf(
                                    arenaFile.getConfig()
                                            .getString(
                                                    arenaName
                                                    + ".Generators."
                                                    + key
                                                    + ".type"
                                            )
                            );

                    GeneratorCategory category =
                            type ==
                            GeneratorType.DIAMOND

                            ?

                            GeneratorCategory.DIAMOND

                            :

                            GeneratorCategory.EMERALD;

                    Generator generator =
                            new Generator(
                                    arena,
                                    type,
                                    category,
                                    arenaFile.getLocation(
                                            arenaName
                                            + ".Generators."
                                            + key
                                            + ".location"
                                    )
                            );

                    arena.getGenerators()
                            .add(generator);
                }
            }
            org.bukkit.Location itemShop =
                    arenaFile.getLocation(
                            arenaName +
                            ".NPCS.ITEM_SHOP"
                    );

            if(itemShop != null) {

                Bedwars.getInstance()
                        .getNpcManager()
                        .createItemShop(
                                itemShop
                        );
            }
            else {
            	  Bukkit.getLogger().warning(
            	            "[KPBedWars] Arena "
            	            + arenaName
            	            + " não possui NPC ITEM_SHOP."
            	    );
            	  continue;
            }
            org.bukkit.Location teamUpgrade =
                    arenaFile.getLocation(
                            arenaName +
                            ".NPCS.TEAM_UPGRADES"
                    );

            if(teamUpgrade != null) {

                Bedwars.getInstance()
                        .getNpcManager()
                        .createUpgradeShop(
                                teamUpgrade
                        );
            }
                else {
              	  Bukkit.getLogger().warning(
              	            "[KPBedWars] Arena "
              	            + arenaName
              	            + " não possui NPC TEAM_UPGRADES."
              	    );
              	  continue;
              
            }
            arenas.put(arenaName, arena);
        }
    }
    public Arena getArena(
            GamePlayer player) {

        for(Arena arena :
                arenas.values()) {

            if(arena.getGamePlayers()
                    .contains(player)) {

                return arena;
            }
        }

        return null;
    }
    public Arena getArena(
            Player player) {

        for(Arena arena :
                arenas.values()) {

            for(GamePlayer gp :
                    arena.getGamePlayers()) {

                if(gp.getArena()
                        .equals(arena)) {

                    return arena;
                }
            }
        }

        return null;
    }
    public void saveArena(
            Arena arena) {

        String path =
                arena.getName();

        arenaFile.setLocation(
                path + ".lobby",
                arena.getLobby()
        );

        arenaFile.setLocation(
                path + ".spectator",
                arena.getSpectator()
        );

        saveTeams(arena);

        saveGenerators(arena);

        arenaFile.save();
    }
    private void saveTeams(
            Arena arena) {

        String path =
                arena.getName()
                + ".Teams";

        for(BWTeam team :
                arena.getTeams()
                        .values()) {

            String teamPath =
                    path
                    + "."
                    + team.getColor()
                            .name();

            arenaFile.setLocation(
                    teamPath + ".spawn",
                    team.getSpawn()
            );

            arenaFile.setLocation(
                    teamPath + ".bed",
                    team.getBed()
            );
        }
    }
    private void saveGenerators(
            Arena arena) {

        int index = 0;

        for(Generator generator :
                arena.getGenerators()) {

            String path =
                    arena.getName()
                    + ".Generators."
                    + index;

            arenaFile.getConfig().set(
                    path + ".type",
                    generator
                            .getType()
                            .name()
            );

            arenaFile.setLocation(
                    path + ".location",
                    generator
                            .getLocation()
            );

            index++;
        }
    }
    public Arena getArena(String name) {
        return arenas.get(name);
    }

    public boolean createArena(String name) {

        if (arenas.containsKey(name))
            return false;

        Arena arena = new Arena(name);

        arenas.put(name, arena);

        arenaFile.getConfig().set(
                name + ".displayName",
                name
        );

        arenaFile.save();

        return true;
    }

    public boolean deleteArena(String name) {

        if (!arenas.containsKey(name))
            return false;

        arenas.remove(name);

        arenaFile.getConfig().set(name, null);
        arenaFile.save();

        return true;
    }

    public ArenaFile getArenaFile() {
        return arenaFile;
    }

    public Map<String, Arena> getArenas() {
        return arenas;
    }
}