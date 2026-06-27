package com.rafaelauler.bedwars;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class SwordManager {

    public void upgrade(
            Player player,
            GamePlayer gamePlayer,
            SwordTier tier) {

        if(gamePlayer.getSwordTier()
                .ordinal()
                >= tier.ordinal()) {

            player.sendMessage(
                    "§cVocê já possui uma espada melhor."
            );

            return;
        }

        removeSwords(player);

        Material material =
                getMaterial(tier);
        ItemStack sword =
                new ItemStack(
                        material
                );
        TeamUpgradeApplier.applySword(
                gamePlayer.getTeam(),
                sword
        );
        gamePlayer.setSwordTier(tier);
        player.getInventory()
        .addItem(
                sword
        );
        player.sendMessage(
                "§aEspada atualizada."
        );
    }
    public void equip(
            Player player,
            GamePlayer gp) {

        removeSwords(player);

        if(gp.getSwordTier() != null) {

            player.getInventory()
                    .addItem(
                    		 ItemMarker.mark(         
                    	        new ItemStack(
                                    getMaterial(
                                            gp.getSwordTier()
                                    ))
                            )
                    );}
        }
    private void removeSwords(
            Player player) {

        player.getInventory()
                .remove(Material.WOOD_SWORD);

        player.getInventory()
                .remove(Material.STONE_SWORD);

        player.getInventory()
                .remove(Material.IRON_SWORD);

        player.getInventory()
                .remove(Material.DIAMOND_SWORD);
        player.updateInventory();
    }

    private Material getMaterial(
            SwordTier tier) {

        switch(tier) {

            case STONE:
                return Material.STONE_SWORD;

            case IRON:
                return Material.IRON_SWORD;

            case DIAMOND:
                return Material.DIAMOND_SWORD;

            default:
                return Material.WOOD_SWORD;
        }
    }
}