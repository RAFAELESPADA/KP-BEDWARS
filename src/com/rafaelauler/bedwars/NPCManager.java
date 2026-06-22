package com.rafaelauler.bedwars;


import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;

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

    public void register(
            int id,
            NPCType type) {

        npcTypes.put(id, type);

        Bedwars.getInstance()
                .getConfig()
                .set(
                        "NPCS." + type.name(),
                        id
                );

        Bedwars.getInstance()
                .saveConfig();
    }
    public void loadNPCs() {

        Bukkit.getScheduler()
                .runTaskLater(
                        Bedwars.getInstance(),

                        () -> {

                            for(NPCType type :
                                    NPCType.values()) {

                                int id =
                                        Bedwars.getInstance()
                                                .getConfig()
                                                .getInt(
                                                        "NPCS."
                                                        + type.name(),
                                                        -1
                                                );

                                if(id == -1)
                                    continue;
                                NPC npc =
                                        CitizensAPI
                                                .getNPCRegistry()
                                                .getById(id);

                                if(npc == null)
                                    continue;

                                npcTypes.put(id, type);

                                npcs.put(
                                        npc,
                                        type
                                );


                                npcTypes.put(
                                        id,
                                        type
                                );

                                npcs.put(
                                        npc,
                                        type
                                );
                            }

                            Bukkit.getLogger()
                                    .info(
                                            "[KPBedWars] "
                                            + npcTypes.size()
                                            + " NPCs carregados."
                                    );

                        },

                        40L
                );
    }


    public NPC createItemShop(
            Location location) {

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
        register(
                npc.getId(),
                NPCType.ITEM_SHOP
        );
        npcs.put(
                npc,
                NPCType.ITEM_SHOP
        );
    
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
    public NPC createUpgradeShop(
            Location location) {

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
        register(
                npc.getId(),
                NPCType.TEAM_UPGRADES
        );
        npcs.put(
                npc,
                NPCType.TEAM_UPGRADES
        );
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
        register(npc.getId(), NPCType.PLAY);
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