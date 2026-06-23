package com.rafaelauler.bedwars;


import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ArmorManager {

    public boolean upgrade(
            Player player,
            GamePlayer gp,
            ArmorTier tier) {

        if(gp.getArmorTier()
                .ordinal()
                >= tier.ordinal()) {

            player.sendMessage(
                    "§cVocê já possui uma armadura igual ou melhor."
            );

            return false;
        }

        gp.setArmorTier(tier);

        equip(player, gp, tier);

        player.sendMessage(
                "§aArmadura atualizada."
        );

        return true;
    }

    public void equip(
            Player player,
            GamePlayer gp,
            ArmorTier tier) {
    	
        ItemStack chestplate =
                TeamColorUtil.createColoredChestplate(
                        gp.getTeam()
                );

        ItemStack leggings;
        ItemStack boots;

        switch(tier) {

            default:
            case LEATHER:

                leggings =
                        TeamColorUtil.createColoredLeggings(
                                gp.getTeam()
                        );

                boots =
                        TeamColorUtil.createColoredBoots(
                                gp.getTeam()
                        );

                break;

            case CHAINMAIL:

                leggings =
                        new ItemStack(
                                Material.CHAINMAIL_LEGGINGS
                        );

                boots =
                        new ItemStack(
                                Material.CHAINMAIL_BOOTS
                        );

                break;

            case IRON:

                leggings =
                        new ItemStack(
                                Material.IRON_LEGGINGS
                        );

                boots =
                        new ItemStack(
                                Material.IRON_BOOTS
                        );

                break;

            case DIAMOND:

                leggings =
                        new ItemStack(
                                Material.DIAMOND_LEGGINGS
                        );

                boots =
                        new ItemStack(
                                Material.DIAMOND_BOOTS
                        );

                break;
        }

        TeamUpgradeApplier.applyArmor(
                gp.getTeam(),
                chestplate
        );

        TeamUpgradeApplier.applyArmor(
                gp.getTeam(),
                leggings
        );

        TeamUpgradeApplier.applyArmor(
                gp.getTeam(),
                boots
        );

        player.getInventory()
                .setChestplate(
                        chestplate
                );

        player.getInventory()
                .setLeggings(
                        leggings
                );

        player.getInventory()
                .setBoots(
                        boots
                );

        player.updateInventory();
    
    }
}
