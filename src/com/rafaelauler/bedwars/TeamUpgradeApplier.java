package com.rafaelauler.bedwars;


import org.bukkit.enchantments.Enchantment;
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

public static void applyArmor(
        BWTeam team,
        ItemStack armor) {

    if(armor == null)
        return;

    if(team.getProtectionLevel()
            <= 0)
        return;

    armor.addUnsafeEnchantment(
            Enchantment.PROTECTION_ENVIRONMENTAL,
            team.getProtectionLevel()
    );
}
}
