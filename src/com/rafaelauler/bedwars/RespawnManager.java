package com.rafaelauler.bedwars;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;

public class RespawnManager {

    public void respawn(
            Player player) {

        GamePlayer gp =
                Bedwars.getInstance()
                        .getPlayerManager()
                        .get(player);

        if(gp == null)
            return;

        Arena arena =
                gp.getArena();

        if(arena == null)
            return;

        BWTeam team =
                gp.getTeam();

        if(team == null)
            return;

        player.teleport(
                team.getSpawn()
        );

        player.setHealth(
                20.0
        );

        player.setFoodLevel(
                20
        );

        player.setFireTicks(
                0
        );

        player.setGameMode(
                GameMode.SURVIVAL
        );

        player.getInventory()
                .clear();

        giveDefaultItems(
                player
        );

        restoreUpgrades(
                player,
                gp
        );
    }

private void giveDefaultItems(
        Player player) {

    player.getInventory()
            .addItem(
                    new org.bukkit.inventory.ItemStack(
                            org.bukkit.Material.WOOD_SWORD
                    )
            );
}

private void restoreUpgrades(
        Player player,
        GamePlayer gp) {

    Bedwars.getInstance()
            .getArmorManager()
            .equip(
                    player, gp,
                    gp.getArmorTier()
            );

    Bedwars.getInstance()
            .getToolManager()
            .equip(
                    player,
                    gp
            );
}
}