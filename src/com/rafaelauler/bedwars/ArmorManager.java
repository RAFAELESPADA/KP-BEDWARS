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
    	
        switch(tier) {

            case LEATHER:

                player.getInventory()
                        .setBoots(
                                new ItemStack(
                                        Material.LEATHER_BOOTS
                                )
                        );
                ItemStack boots4 =
            	        new ItemStack(
            	                Material.LEATHER_BOOTS
            	        );

            	TeamUpgradeApplier.applyArmor(
            	        gp.getTeam(),
            	        boots4
            	);

            	player.getInventory()
            	        .setBoots(
            	                boots4
            	        );
                break;

            case CHAINMAIL:

                player.getInventory()
                        .setBoots(
                                new ItemStack(
                                        Material.CHAINMAIL_BOOTS
                                )
                        );
                ItemStack boots3 =
            	        new ItemStack(
            	                Material.CHAINMAIL_BOOTS
            	        );

            	TeamUpgradeApplier.applyArmor(
            	        gp.getTeam(),
            	        boots3
            	);

            	player.getInventory()
            	        .setBoots(
            	                boots3
            	        );
                break;

            case IRON:

                player.getInventory()
                        .setBoots(
                                new ItemStack(
                                        Material.IRON_BOOTS
                                )
                        );
                ItemStack boots =
            	        new ItemStack(
            	                Material.IRON_BOOTS
            	        );

            	TeamUpgradeApplier.applyArmor(
            	        gp.getTeam(),
            	        boots
            	);

            	player.getInventory()
            	        .setBoots(
            	                boots
            	        );
                break;

            case DIAMOND:

                player.getInventory()
                        .setBoots(
                                new ItemStack(
                                        Material.DIAMOND_BOOTS
                                )
                        );
                ItemStack boots2 =
            	        new ItemStack(
            	                Material.DIAMOND_BOOTS
            	        );

            	TeamUpgradeApplier.applyArmor(
            	        gp.getTeam(),
            	        boots2
            	);

            	player.getInventory()
            	        .setBoots(
            	                boots2
            	        );
                break;
        }
    }
}
