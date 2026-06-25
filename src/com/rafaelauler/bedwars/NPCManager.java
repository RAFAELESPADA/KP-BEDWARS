package com.rafaelauler.bedwars;


import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.trait.CommandTrait;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;

import java.util.HashMap;
import java.util.Map;

public class NPCManager {

    private final Map<Integer, NPCType> npcTypes =
            new HashMap<>();
    private final Map<NPC, NPCType> npcs =
            new HashMap<>();

    public void register(Arena arena, NPC npc, NPCType type) {

        npcTypes.put(npc.getId(), type);
        npcs.put(npc, type);

        ArenaFile file =
                Bedwars.getInstance()
                        .getArenaManager()
                        .getArenaFile();

        file.getConfig().set(
                arena.getName() + ".NPCS." + type.name() + ".ID",
                npc.getId()
        );

        file.save();
    }
    public void loadNPCs() {

        Bukkit.getScheduler().runTaskLater(
                Bedwars.getInstance(),
                () -> {

                    npcTypes.clear();
                    npcs.clear();

                    for (Arena arena : Bedwars.getInstance()
                            .getArenaManager()
                            .getArenas()
                            .values()) {

                        for (NPCType type : NPCType.values()) {

                            if (type == NPCType.PLAY)
                                continue;

                            int id = Bedwars.getInstance()
                                    .getArenaManager()
                                    .getArenaFile()
                                    .getConfig()
                                    .getInt(
                                            arena.getName()
                                                    + ".NPCS."
                                                    + type.name()
                                                    + ".ID",
                                            -1
                                    );

                            if (id == -1)
                                continue;

                            NPC npc = CitizensAPI
                                    .getNPCRegistry()
                                    .getById(id);

                            if (npc == null)
                                continue;

                            npcTypes.put(id, type);
                            npcs.put(npc, type);

                            CommandTrait trait =
                                    npc.getOrAddTrait(CommandTrait.class);

                            trait.clear();

                            switch (type) {

                                case ITEM_SHOP:

                                    trait.addCommand(
                                            new CommandTrait.NPCCommandBuilder(
                                                    "bw shop",
                                                    CommandTrait.Hand.BOTH
                                            ).player(true)
                                    );
                                    break;

                                case TEAM_UPGRADES:

                                    trait.addCommand(
                                            new CommandTrait.NPCCommandBuilder(
                                                    "bw teamshop",
                                                    CommandTrait.Hand.BOTH
                                            ).player(true)
                                    );
                                    break;

                                default:
                                    break;
                            }
                        }
                    }

                    Bukkit.getLogger().info(
                            "[KPBedWars] "
                                    + npcs.size()
                                    + " NPCs carregados."
                    );

                },
                40L
        );
    
    }
    private void registerCommand(NPC npc, String command) {

        CommandTrait trait = npc.getOrAddTrait(CommandTrait.class);

        trait.clear();

        trait.addCommand(new CommandTrait.NPCCommandBuilder(
                command,
                CommandTrait.Hand.BOTH
        ).player(true));
    }

    public NPC createItemShop(Arena arena, Location location) {

        NPC npc =
                CitizensAPI.getNPCRegistry()
                        .createNPC(
                                EntityType.PLAYER,
                                "§aITEM SHOP"
                        );

        npc.spawn(location);


        npc.data().set(
                NPC.Metadata.NAMEPLATE_VISIBLE,
                true
        );
        npc.data().setPersistent(
                "cached-skin-uuid-name",
                "Villager"
        );
        register(arena, npc, NPCType.ITEM_SHOP);
        npcs.put(
                npc,
                NPCType.ITEM_SHOP
        );
        CommandTrait trait = npc.getOrAddTrait(CommandTrait.class);

        registerCommand(npc, "bw shop");
        return npc;
    }
    public void cleanupArenaNPCs() {

        for(NPC npc :
                new java.util.ArrayList<>(
                        npcs.keySet()
                )) {

            npc.destroy();
        }

        npcs.clear();
        npcTypes.clear();
    }
    public NPC createUpgradeShop(Arena arena, Location location) {

        NPC npc =
                CitizensAPI.getNPCRegistry()
                        .createNPC(
                                EntityType.PLAYER,
                                "§bTEAM UPGRADES"
                        );

        npc.spawn(location);

        npc.data().set(
                NPC.Metadata.NAMEPLATE_VISIBLE,
                true
        );
        npc.data().setPersistent(
                "cached-skin-uuid-name",
                "Gladiator"
        );
        register(arena, npc, NPCType.TEAM_UPGRADES);
        npcs.put(
                npc,
                NPCType.TEAM_UPGRADES
        );

        registerCommand(npc, "bw teamshop");
        return npc;
    }
    public NPC getClosestNPC(
            Location location,
            NPCType type) {

        NPC closest = null;

        double distance = Double.MAX_VALUE;

        for(Map.Entry<NPC, NPCType> entry :
                npcs.entrySet()) {

            if(entry.getValue() != type)
                continue;

            if(!entry.getKey().isSpawned())
                continue;

            double current =
                    entry.getKey()
                            .getEntity()
                            .getLocation()
                            .distance(location);

            if(current < distance) {

                distance = current;
                closest = entry.getKey();
            }
        }

        return closest;
    }
    public NPC createPlayNPC(
            Location location) {

        NPC npc =
                CitizensAPI
                        .getNPCRegistry()
                        .createNPC(
                                EntityType.PLAYER,
                                "§aJogar BedWars"
                        );

        npc.spawn(location);
        npcs.put(
                npc,
                NPCType.PLAY
        );
        npc.setProtected(
                true
        );
        npc.setFlyable(
                false
        );
        npc.data().setPersistent(
                "cached-skin-uuid-name",
                "Hypixel"
        );
        return npc;
    }
    public void removeNPC(
            NPC npc) {

        if(npc == null)
            return;

        npc.destroy();

        npcs.remove(npc);
    }
    public NPCType getType(
            int npcId) {

        return npcTypes.get(npcId);
    }
}