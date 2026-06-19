package com.rafaelauler.bedwars;


import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class SetBedCommand implements SubCommand {

    @Override
    public String getName() {
        return "setbed";
    }

    @Override
    public String getPermission() {
        return "bedwars.admin";
    }

    @Override
    public void execute(Player player, String[] args) {

        if(args.length < 3)
            return;

        Block block =
                player.getTargetBlock(
                        null,
                        10
                );

        if(block == null ||
                block.getType() != Material.BED_BLOCK) {

            player.sendMessage(
                    "§cOlhe para uma cama."
            );
            return;
        }

        Arena arena =
                Bedwars.getInstance()
                        .getArenaManager()
                        .getArena(args[1]);

        TeamColor color =
                TeamColor.valueOf(
                        args[2].toUpperCase()
                );

        BWTeam team =
                arena.getTeam(color);

        team.setBed(
                block.getLocation()
        );

        Bedwars.getInstance()
                .getArenaManager()
                .getArenaFile()
                .setLocation(
                        arena.getName()
                                + ".Teams."
                                + color.name()
                                + ".bed",
                        block.getLocation()
                );

        player.sendMessage(
                "§aCama definida."
        );
    }
}
