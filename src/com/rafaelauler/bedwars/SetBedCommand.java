package com.rafaelauler.bedwars;

import java.util.HashSet;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
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
    public void execute(
            Player player,
            String[] args) {

        if(args.length < 3) {

            player.sendMessage(
                    "§cUse: /bw setbed <arena> <time>"
            );

            return;
        }
        @SuppressWarnings("deprecation")
		Block bed =
                player.getTargetBlock(
                        (java.util.HashSet<Byte>) null,
                        10
                );

        if(bed == null
                || bed.getType()
                != Material.BED_BLOCK) {

            player.sendMessage(
                    "§cOlhe para uma cama."
            );

            return;
        }

        Arena arena =
                Bedwars.getInstance()
                        .getArenaManager()
                        .getArena(
                                args[1]
                        );

        if(arena == null) {

            player.sendMessage(
                    "§cArena não encontrada."
            );

            return;
        }

        TeamColor color =
                TeamColor.valueOf(
                        args[2]
                                .toUpperCase()
                );

        BWTeam team =
                arena.getTeam(
                        color
                );

        if(team == null) {

            player.sendMessage(
                    "§cTime não encontrado."
            );

            return;
        }

        boolean head =
                (bed.getData() & 0x8)
                != 0;

        int direction =
                bed.getData() & 0x3;

        BlockFace face;

        switch(direction) {

            case 0:
                face =
                        BlockFace.SOUTH;
                break;

            case 1:
                face =
                        BlockFace.WEST;
                break;

            case 2:
                face =
                        BlockFace.NORTH;
                break;

            default:
                face =
                        BlockFace.EAST;
        }

        Block headBlock;
        Block footBlock;

        if(head) {

            headBlock = bed;

            footBlock =
                    bed.getRelative(
                            face.getOppositeFace()
                    );

        } else {

            footBlock = bed;

            headBlock =
                    bed.getRelative(
                            face
                    );
        }

        team.setBedHead(
                headBlock.getLocation()
        );

        team.setBedFoot(
                footBlock.getLocation()
        );

        team.setBedHeadData(
                headBlock.getData()
        );

        team.setBedFootData(
                footBlock.getData()
        );

        String path =
                arena.getName()
                + ".Teams."
                + color.name();

        Bedwars.getInstance()
                .getArenaManager()
                .getArenaFile()
                .setLocation(
                        path + ".bedHead",
                        headBlock.getLocation()
                );

        Bedwars.getInstance()
                .getArenaManager()
                .getArenaFile()
                .setLocation(
                        path + ".bedFoot",
                        footBlock.getLocation()
                );

        Bedwars.getInstance()
                .getArenaManager()
                .getArenaFile()
                .getConfig()
                .set(
                        path + ".bedHeadData",
                        headBlock.getData()
                );

        Bedwars.getInstance()
                .getArenaManager()
                .getArenaFile()
                .getConfig()
                .set(
                        path + ".bedFootData",
                        footBlock.getData()
                );

        Bedwars.getInstance()
                .getArenaManager()
                .getArenaFile()
                .save();

        player.sendMessage(
                "§aCama completa salva."
        );
    }
}