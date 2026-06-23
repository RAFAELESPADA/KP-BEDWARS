package com.rafaelauler.bedwars;


import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class TeamUpgradeApplier {

    public static void applySword(
            BWTeam team,
            ItemStack sword) {

        if(sword == null)
            return;

        if(!team.hasSharpness())
            return;

        sword.addUnsafeEnchantment(
                Enchantment.DAMAGE_ALL,
                1
        );
    }
    public static void applyProtection(
            BWTeam team) {

        int level =
                team.getProtectionLevel();

        for(UUID uuid :
                team.getPlayers()) {

            Player player =
                    Bukkit.getPlayer(uuid);

            if(player == null)
                continue;

            enchant(
                    player.getInventory()
                            .getHelmet(),
                    level
            );

            enchant(
                    player.getInventory()
                            .getChestplate(),
                    level
            );

            enchant(
                    player.getInventory()
                            .getLeggings(),
                    level
            );

            enchant(
                    player.getInventory()
                            .getBoots(),
                    level
            );

            player.updateInventory();
        }
    }

    private static void enchant(
            ItemStack item,
            int level) {

        if(item == null)
            return;

        item.removeEnchantment(
                Enchantment.PROTECTION_ENVIRONMENTAL
        );

        item.addUnsafeEnchantment(
                Enchantment.PROTECTION_ENVIRONMENTAL,
                level
        );
    }

    public static void applyArmor(
            BWTeam team,
            ItemStack item) {

        if(item == null)
            return;

        if(team.getProtectionLevel() > 0) {

            item.addUnsafeEnchantment(
                    Enchantment.PROTECTION_ENVIRONMENTAL,
                    team.getProtectionLevel()
            );
        }
    }
}
