package com.rafaelauler.bedwars;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import eu.decentsoftware.holograms.api.DHAPI;

public class EmeraldGeneratorTask
        extends BukkitRunnable {

    private final Generator generator;

    private final int maxSeconds;

    private int countdownTicks;

    public EmeraldGeneratorTask(
            Generator generator,
            int maxSeconds) {

        this.generator = generator;
        this.maxSeconds = maxSeconds;

        this.countdownTicks =
                maxSeconds * 20;
    }

    @Override
    public void run() {

        countdownTicks--;

        if(countdownTicks % 20 == 0) {

            updateHologram();
        }

        if(countdownTicks > 0)
            return;

        spawnEmerald();

        countdownTicks =
                maxSeconds * 20;
    }

    private void updateHologram() {

        int seconds =
                countdownTicks / 20;

        DHAPI.setHologramLine(
                generator.getDisplay()
                        .getHologram(),

                2,

                "§fPróxima esmeralda em §a"
                        + seconds
                        + "s"
        );
    }

    private void spawnEmerald() {

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
                    != Material.EMERALD)
                continue;

            amount += item.getItemStack()
                    .getAmount();
        }

        if(amount >= 4)
            return;

        generator.getLocation()
                .getWorld()
                .dropItemNaturally(
                        generator.getLocation(),

                        new ItemStack(
                                Material.EMERALD
                        )
                );
    }
}