package com.rafaelauler.bedwars;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class IronTaskGenerator
        extends BukkitRunnable {

    private final Generator generator;

    private int ticks;

    public IronTaskGenerator(
            Generator generator) {

        this.generator = generator;
    }

    @Override
    public void run() {

        ticks++;

        if(ticks < getDelay())
            return;

        ticks = 0;

        spawnIron();
    }

    private int getDelay() {

        BWTeam team =
                generator.getTeam();

        if(team == null)
            return 20;

        switch(team.getForgeLevel()) {

            case 1:
                return 15;

            case 2:
                return 10;

            case 3:
                return 7;

            case 4:
                return 4;

            default:
                return 20;
        }
    }

    private void spawnIron() {

        if(getNearbyAmount() >= 48)
            return;

        generator.getLocation()
                .getWorld()
                .dropItemNaturally(
                        generator.getLocation(),
                        new ItemStack(
                                Material.IRON_INGOT
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
                    != Material.IRON_INGOT)
                continue;

            amount += item.getItemStack()
                    .getAmount();
        }

        return amount;
    }
}