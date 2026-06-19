package com.rafaelauler.bedwars;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import eu.decentsoftware.holograms.api.DHAPI;

public class DiamondGeneratorTask
extends BukkitRunnable {

private final Generator generator;

private final int maxSeconds;

private int countdown;

public DiamondGeneratorTask(
    Generator generator,
    int maxSeconds) {

this.generator = generator;
this.maxSeconds = maxSeconds;
this.countdown = maxSeconds;
}

@Override
public void run() {

countdown--;

updateHologram();

if(countdown > 0)
    return;

spawnDiamond();

countdown = maxSeconds;
}

private void updateHologram() {

DHAPI.setHologramLine(
        generator.getDisplay()
                .getHologram(),
        2,"§fGera items a cada §a" + countdown + " §fsegundos"
);
}

private void spawnDiamond() {

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
