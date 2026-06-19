package com.rafaelauler.bedwars;


import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;

import java.util.HashMap;
import java.util.Map;

public class NPCManager {

    private final Map<Integer, NPCType> npcTypes =
            new HashMap<>();
    private final Map<NPC, NPCType> npcs =
            new HashMap<>();
    public NPC createItemShop(
            Location location) {

        NPC npc =
                CitizensAPI.getNPCRegistry()
                        .createNPC(
                                EntityType.PLAYER,
                                "§aITEM SHOP"
                        );

        npc.spawn(location);

        npcTypes.put(
                npc.getId(),
                NPCType.ITEM_SHOP
        );
        npc.data().set(
                NPC.Metadata.NAMEPLATE_VISIBLE,
                true
        );
        npc.data().setPersistent(
                "cached-skin-uuid-name",
                "Villager"
        );
        npcs.put(
                npc,
                NPCType.ITEM_SHOP
        );
        return npc;
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

        npcTypes.put(
                npc.getId(),
                NPCType.TEAM_UPGRADES
        );
        npc.data().set(
                NPC.Metadata.NAMEPLATE_VISIBLE,
                true
        );
        npc.data().setPersistent(
                "cached-skin-uuid-name",
                "Gladiator"
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

        npc.data().set(
                "bw_npc",
                NPCType.PLAY.name()
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