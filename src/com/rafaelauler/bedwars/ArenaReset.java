package com.rafaelauler.bedwars;


import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockState;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;

public class ArenaReset {

    public void reset(
            Arena arena) {
        restoreBlocks(arena);

        removePlacedBlocks(arena);

        clearDrops(arena);

        resetTeams(arena);
        restoreBeds(arena);
        resetGenerators(arena);
        for(Player player :
            new java.util.ArrayList<>(
                    arena.getPlayers()
            )) {

        Bedwars.getInstance()
                .getSpectatorManager()
                .removeSpectator(
                        arena,
                        player
                );
    }
        resetPlayers(arena);
        arena.getPlayers().clear();
        arena.getGamePlayers().clear();
        arena.setState(
                ArenaState.WAITING
        );
     
    }

private void restoreBlocks(
        Arena arena) {

    for(BlockState state :
            arena.getBrokenBlocks()) {

        state.update(
                true,
                false
        );
    }

    arena.getBrokenBlocks()
            .clear();
}
private void restoreBeds(
        Arena arena) {

    for(BWTeam team :
            arena.getTeams().values()) {

        Block bed =
                team.getBed()
                        .getBlock();

        bed.setType(
                Material.BED_BLOCK
        );

        byte data =
                bed.getData();

        BlockFace face;

        switch(data & 0x3) {

        case 0:
            face = BlockFace.SOUTH;
            break;

        case 1:
            face = BlockFace.WEST;
            break;

        case 2:
            face = BlockFace.NORTH;
            break;

        default:
            face = BlockFace.EAST;
            break;
        }

        Block other =
                bed.getRelative(face);

        other.setType(
                Material.BED_BLOCK
        );
        team.setBedAlive(true);
    }
}
private void clearDrops(
        Arena arena) {

    if(arena.getWorld() == null)
        return;

    for(Entity entity :
            arena.getWorld()
                    .getEntities()) {

        if(entity instanceof Item) {

            entity.remove();
        }
    }
}
private void resetTeams(
        Arena arena) {

    for(BWTeam team :
            arena.getTeams()
                    .values()) {

        team.setBedAlive(true);

        team.setForgeLevel(0);

        team.setProtectionLevel(0);

        team.setSharpness(false);

        team.getPlayers()
                .clear();
    }
}
private void resetGenerators(
        Arena arena) {

    Bedwars.getInstance()
            .getGeneratorManager()
            .stop(arena);

    Bedwars.getInstance()
            .getGeneratorManager()
            .start(arena);
}
private void resetPlayers(
        Arena arena) {

    for(GamePlayer gp :
            arena.getGamePlayers()) {

        gp.setTeam(null);

        gp.setKills(0);

        gp.setFinalKills(0);

        gp.setAlive(true);
    }
}
private void removePlacedBlocks(
        Arena arena) {

    for(org.bukkit.Location location :
            arena.getPlacedBlocks()) {

        location.getBlock()
                .setType(
                        Material.AIR
                );
    }

    arena.getPlacedBlocks()
            .clear();
}
}
