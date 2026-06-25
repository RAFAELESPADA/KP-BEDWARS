package com.rafaelauler.bedwars;


import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockState;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

public class ArenaReset {

	public void reset(Arena arena) {

	    clearDrops(arena);

	    resetGenerators(arena);

	    for(Player player : new ArrayList<>(arena.getPlayers())){

	        Bedwars.getInstance()
	                .getSpectatorManager()
	                .removeSpectator(arena, player);
	    }

	    resetPlayers(arena);

	    arena.getPlayers().clear();

	    arena.getGamePlayers().clear();

	    arena.getTasks().forEach(BukkitTask::cancel);

	    arena.cancelTasks();

	    arena.getTasks().clear();

	    for(BWTeam team : arena.getTeams().values()){

	        team.getPlayers().clear();

	        team.setBedAlive(true);

	        team.setSharpness(false);

	        team.setForgeLevel(0);

	        team.setProtectionLevel(0);
	    }

	    Bedwars.getInstance()
	            .getWorldReset()
	            .reset(arena);

	    arena.setState(ArenaState.WAITING);
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

        if(team.getBedHead() == null
                || team.getBedFoot() == null)
            continue;
    

        Block head =
                team.getBedHead()
                        .getBlock();

        head.setType(
                Material.BED_BLOCK
        );

        head.setData(
                team.getBedHeadData()
        );

        Block foot =
                team.getBedFoot()
                        .getBlock();

        foot.setType(
                Material.BED_BLOCK
        );

        foot.setData(
                team.getBedFootData()
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
