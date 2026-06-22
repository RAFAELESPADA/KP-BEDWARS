package com.rafaelauler.bedwars;


import org.bukkit.Material;
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

        resetPlayers(arena);

        arena.setState(
                ArenaState.WAITING
        );
        for(Player player :
            arena.getPlayers()) {

        Bedwars.getInstance()
                .getSpectatorManager()
                .removeSpectator(arena, player);
        }
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
            arena.getTeams()
                    .values()) {

        if(team.getBed() == null)
            continue;

        team.getBed()
                .getBlock()
                .setType(
                        org.bukkit.Material.BED_BLOCK
                );

        team.setBedAlive(
                true
        );
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
