package com.rafaelauler.bedwars;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class GoldGeneratorTask
        extends BukkitRunnable {

    private final Generator generator;

    private int ticks;

    public GoldGeneratorTask(
            Generator generator) {

        this.generator = generator;
    }

    @Override
    public void run() {

        ticks++;

        if(ticks < getDelay())
            return;

        ticks = 0;

        spawnGold();
    }

    private int getDelay() {

        BWTeam team =
                generator.getTeam();

        if(team == null)
            return 80;

        switch(team.getForgeLevel()) {

            case 1:
                return 70;

            case 2:
                return 60;

            case 3:
                return 50;

            case 4:
                return 40;

            default:
                return 80;
        }
    }

    private void spawnGold() {

        if(getNearbyAmount() >= 12)
            return;

        generator.getLocation()
                .getWorld()
                .dropItemNaturally(
                        generator.getLocation(),
                        new ItemStack(
                                Material.GOLD_INGOT
                        )
                );
    }

    private int getNearbyAmount() {

        int amount = 0;

        for(Entity entity :
                generator.getLocation()
                        .getWorld()
                        .getNearbyEntities(
                                generator.getLocation(),
                                2,
                                2,
                                2
                        )) {

            if(!(entity instanceof Item))
                continue;

            Item item =
                    (Item) entity;

            if(item.getItemStack()
                    .getType()
                    != Material.GOLD_INGOT)
                continue;

            amount += item.getItemStack()
                    .getAmount();
        }

        return amount;
    }
}