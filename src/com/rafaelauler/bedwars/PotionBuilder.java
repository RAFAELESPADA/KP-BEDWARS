package com.rafaelauler.bedwars;

import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionType;

public class PotionBuilder {

    public static ItemStack speed(int seconds) {

        Potion potion = new Potion(PotionType.SPEED);
        potion.setSplash(false);

        return potion.toItemStack(1);
    }

    public static ItemStack jump(int seconds) {

        Potion potion = new Potion(PotionType.JUMP);
        potion.setSplash(false);

        return potion.toItemStack(1);
    }

    public static ItemStack invisibility(int seconds) {

        Potion potion = new Potion(PotionType.INVISIBILITY);
        potion.setSplash(false);

        return potion.toItemStack(1);
    }
}