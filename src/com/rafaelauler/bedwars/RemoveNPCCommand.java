package com.rafaelauler.bedwars;


import net.citizensnpcs.api.npc.NPC;
import org.bukkit.entity.Player;

public class RemoveNPCCommand
        implements SubCommand {

    @Override
    public String getName() {
        return "removenpc";
    }

    @Override
    public String getPermission() {
        return "bedwars.admin";
    }

    @Override
    public void execute(
            Player player,
            String[] args) {

        if(args.length < 3)
            return;

        Arena arena =
                Bedwars.getInstance()
                        .getArenaManager()
                        .getArena(args[1]);

        if(arena == null)
            return;

        NPCType type =
                args[2]
                        .equalsIgnoreCase(
                                "itemshop"
                        )

                        ?

                        NPCType.ITEM_SHOP

                        :

                        NPCType.TEAM_UPGRADES;

        NPC npc =
                Bedwars.getInstance()
                        .getNpcManager()
                        .getClosestNPC(
                                player.getLocation(),
                                type
                        );

        if(npc == null) {

            player.sendMessage(
                    "§cNPC não encontrado."
            );

            return;
        }

        Bedwars.getInstance()
                .getNpcManager()
                .removeNPC(npc);

        Bedwars.getInstance()
                .getArenaManager()
                .getArenaFile()
                .setLocation(
                        arena.getName()
                        + ".NPCS."
                        + type.name(),
                        null
                );

        player.sendMessage(
                "§aNPC removido."
        );
    }
}
