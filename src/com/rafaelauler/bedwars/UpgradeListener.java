package com.rafaelauler.bedwars;


import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class UpgradeListener
        implements Listener {

    @EventHandler
    public void onClick(
            InventoryClickEvent e) {

        if(!e.getView()
                .getTitle()
                .equals(
                        "Team Upgrades"
                ))
            return;

        e.setCancelled(true);

        Player player =
                (Player)
                        e.getWhoClicked();

        GamePlayer gp =
                Bedwars.getInstance()
                        .getPlayerManager()
                        .get(player);

        if(gp.getTeam() == null)
            return;

        if(e.getCurrentItem() == null)
            return;

        Material type =
                e.getCurrentItem().getType();

        BWTeam team =
                gp.getTeam();

        int cost =
                getForgeCost(
                        team.getForgeLevel()
                );

        if(!hasDiamonds(
                player,
                cost
        )) {

            player.sendMessage(
                    "§cDiamantes insuficientes."
            );

            return;
        }

        removeDiamonds(
                player,
                cost
        );
  
        		switch(type) {
        	    case IRON_SWORD:

        	        if(!hasDiamonds(player, 4)) {
        	            player.sendMessage("§cDiamantes insuficientes.");
        	            return;
        	        }

        	        if(!Bedwars.getInstance()
        	                .getUpgradeManager()
        	                .buySharpness(team)) {

        	            player.sendMessage(
        	                    "§cSeu time já possui Sharpness."
        	            );

        	            return;
        	        }

        	        removeDiamonds(player, 4);

        	        applySharpnessToTeam(team);

        	        player.sendMessage(
        	                "§aSharpness comprada."
        	        );
        	        break;

        	    case IRON_CHESTPLATE:

        	        int cost2 =
        	                team.getProtectionLevel() + 2;

        	        if(!hasDiamonds(player, cost2)) {
        	            player.sendMessage("§cDiamantes insuficientes.");
        	            return;
        	        }

        	        if(!Bedwars.getInstance()
        	                .getUpgradeManager()
        	                .buyProtection(team)) {
        	        	 
        	            player.sendMessage(
        	                    "§cProtection máxima."
        	            );

        	            return;
        	        }

        	        removeDiamonds(player, cost2);
        	        Bedwars.getInstance()
                    .getArmorManager()
                    .upgrade(
                            player,
                            gp,
                            gp.getArmorTier()
                    );
        	        player.sendMessage(
        	                "§aProtection melhorada."
        	        );
        	        break;

        	    case GOLD_INGOT:

        	        int forgeCost =
        	                getForgeCost(
        	                        team.getForgeLevel()
        	                );

        	        if(!hasDiamonds(
        	                player,
        	                forgeCost
        	        )) {

        	            player.sendMessage(
        	                    "§cDiamantes insuficientes."
        	            );

        	            return;
        	        }

        	        if(!Bedwars.getInstance()
        	                .getUpgradeManager()
        	                .buyForge(team)) {

        	            player.sendMessage(
        	                    "§cForge máxima."
        	            );

        	            return;
        	        }

        	        removeDiamonds(
        	                player,
        	                forgeCost
        	        );

        	        player.sendMessage(
        	                "§aForge melhorada."
        	        );

        	        if(!Bedwars.getInstance()
        	                .getUpgradeManager()
        	                .buyForge(team)) {

        	            player.sendMessage(
        	                    "§cForge máxima."
        	            );

        	            return;
        	        }

        	        player.sendMessage(
        	                "§aForge melhorada."
        	        );
        	    
        	       break;
				default:
					break;
        		}
        		
				
        	}

    public int getForgeCost(
            int level) {

        switch(level) {

            case 0:
                return 2;

            case 1:
                return 4;

            case 2:
                return 6;

            case 3:
                return 8;
        }

        return 999;
    }

    private boolean hasDiamonds(
            Player player,
            int amount) {

        int found = 0;

        for(org.bukkit.inventory.ItemStack item :
                player.getInventory()
                        .getContents()) {

            if(item == null)
                continue;

            if(item.getType()
                    != Material.DIAMOND)
                continue;

            found += item.getAmount();
        }

        return found >= amount;
    }
    private void applySharpnessToTeam(
            BWTeam team) {

        for(UUID uuid :
                team.getPlayers()) {

            Player player =
                    Bukkit.getPlayer(
                            uuid
                    );

            if(player == null)
                continue;

            for(ItemStack item :
                    player.getInventory()
                            .getContents()) {

                if(item == null)
                    continue;

                if(item.getType()
                        .name()
                        .contains(
                                "_SWORD"
                        )) {

                    item.addUnsafeEnchantment(
                            Enchantment.DAMAGE_ALL,
                            1
                    );
                }
            }
        }
    }
    private void removeDiamonds(
            Player player,
            int amount) {

        for(org.bukkit.inventory.ItemStack item :
                player.getInventory()
                        .getContents()) {

            if(item == null)
                continue;

            if(item.getType()
                    != Material.DIAMOND)
                continue;

            if(amount <= 0)
                break;

            int stack =
                    item.getAmount();

            if(stack <= amount) {

                amount -= stack;

                item.setAmount(0);

            } else {

                item.setAmount(
                        stack - amount
                );

                amount = 0;
            }
        }
    }
}