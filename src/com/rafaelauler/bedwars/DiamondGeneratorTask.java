package com.rafaelauler.bedwars;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import eu.decentsoftware.holograms.api.DHAPI;

public class DiamondGeneratorTask
        extends BukkitRunnable {

    private final Generator generator;

    private final int maxSeconds;

    private int countdownTicks;

    public DiamondGeneratorTask(
            Generator generator,
            int maxSeconds) {

        this.generator =
                generator;

        this.maxSeconds =
                maxSeconds;

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

        spawnDiamond();

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

                "§fPróximo diamante em §b"
                        + seconds
                        + "s"
        );
    }

    private void spawnDiamond() {

        long amount =
                generator.getLocation()
                        .getWorld()
                        .getNearbyEntities(
                                generator.getLocation(),
                                2,
                                2,
                                2
                        )
                        .stream()
                        .filter(entity ->
                                entity instanceof org.bukkit.entity.Item
                        )
                        .count();

        if(amount >= 4)
            return;

        generator.getLocation()
                .getWorld()
                .dropItemNaturally(
                        generator.getLocation(),

                        new ItemStack(
                                Material.DIAMOND
                        )
                );
    }
}