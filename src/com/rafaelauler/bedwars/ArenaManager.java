package com.rafaelauler.bedwars;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;

public class ArenaManager {

    private final Map<String, Arena> arenas;
    private final ArenaFile arenaFile;

    public ArenaManager() {

        this.arenas = new HashMap<>();
        this.arenaFile = new ArenaFile();

        loadArenas();
    }
    public static void leave(
            GamePlayer gp) {

        Arena arena =
                gp.getArena();

        if(arena == null)
            return;

        if(gp.getTeam() != null) {

            gp.getTeam()
                    .getPlayers()
                    .remove(gp.getUuid());
        }

        arena.getGamePlayers()
                .remove(gp);

        gp.setArena(null);
        gp.setTeam(null);
    }
    private void loadArenas() {

        if (arenaFile.getConfig().getKeys(false).isEmpty())
            return;

        for (String arenaName : arenaFile.getConfig().getKeys(false)) {

            Arena arena = new Arena(arenaName);

            loadTeams(arenaName, arena);
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
                    String teamName =
                            arenaFile.getConfig()
                                    .getString(
                                            arenaName
                                            + ".Generators."
                                            + key
                                            + ".team"
                                    );

                    if(teamName != null) {

                        BWTeam team =
                                arena.getTeams()
                                        .get(teamName);
                        
                        generator.setTeam(team);
                    }
                    
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

                int id =
                        arenaFile.getConfig()
                                .getInt(
                                        arenaName
                                        + ".NPCS.ITEM_SHOP_ID",
                                        -1
                                );

                if(id == -1
                        || CitizensAPI
                                .getNPCRegistry()
                                .getById(id) == null) {

                    NPC npc =
                            Bedwars.getInstance()
                                    .getNpcManager()
                                    .createItemShop(
                                            itemShop
                                    );

                    arenaFile.getConfig().set(
                            arenaName
                            + ".NPCS.ITEM_SHOP_ID",
                            npc.getId()
                    );

                    arenaFile.save();
                }
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

                int id =
                        arenaFile.getConfig()
                                .getInt(
                                        arenaName
                                        + ".NPCS.TEAM_UPGRADES_ID",
                                        -1
                                );

                if(id == -1
                        || CitizensAPI
                                .getNPCRegistry()
                                .getById(id) == null) {

                    NPC npc =
                            Bedwars.getInstance()
                                    .getNpcManager()
                                    .createUpgradeShop(
                                            teamUpgrade
                                    );

                    arenaFile.getConfig().set(
                            arenaName
                            + ".NPCS.TEAM_UPGRADES_ID",
                            npc.getId()
                    );

                    arenaFile.save();
                }
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
    private void loadTeams(
            String arenaName,
            Arena arena) {

        ConfigurationSection section =
                arenaFile.getConfig()
                        .getConfigurationSection(
                                arenaName + ".Teams"
                        );

        if(section == null)
            return;

        for(String key :
                section.getKeys(false)) {

            TeamColor color =
                    TeamColor.valueOf(
                            key.toUpperCase()
                    );

            BWTeam team =
                    new BWTeam(
                            color
                    );

            team.setSpawn(
                    arenaFile.getLocation(
                            arenaName
                            + ".Teams."
                            + key
                            + ".spawn"
                    )
            );

            String teamPath =
                    arenaName
                    + ".Teams."
                    + key;

            team.setBedHead(
                    arenaFile.getLocation(
                            teamPath + ".bedHead"
                    )
            );

            team.setBedFoot(
                    arenaFile.getLocation(
                            teamPath + ".bedFoot"
                    )
            );

            team.setBedHeadData(
                    (byte) arenaFile.getConfig()
                            .getInt(
                                    teamPath + ".bedHeadData"
                            )
            );

            team.setBedFootData(
                    (byte) arenaFile.getConfig()
                            .getInt(
                                    teamPath + ".bedFootData"
                            )
            );

            arena.getTeams()
                    .put(
                            color,
                            team
                    );
       }
    
        Bukkit.getConsoleSender().sendMessage("TIMES DA ARENA " + arenaName + " CARREGADOS");
        
    }
    public Arena PlayerisOnArena(
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
    public Arena ArenaExists() {

        for(Arena arena :
                arenas.values()) {

            if(arena.getLobby() != null){

                return arena;
            }
        }

        return null;
    }
    public Arena getArenabyRealPlayer(
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
                    teamPath + ".bedHead",
                    team.getBedHead()
            );

            arenaFile.setLocation(
                    teamPath + ".bedFoot",
                    team.getBedFoot()
            );

            arenaFile.getConfig().set(
                    teamPath + ".bedHeadData",
                    team.getBedHeadData()
            );

            arenaFile.getConfig().set(
                    teamPath + ".bedFootData",
                    team.getBedFootData()
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
            if(generator.getTeam() != null) {

                arenaFile.getConfig().set(
                        path + ".team",
                        generator.getTeam()
                                .getColor()
                                .name()
                );
            }
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