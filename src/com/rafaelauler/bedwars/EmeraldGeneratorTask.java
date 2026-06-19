package com.rafaelauler.bedwars;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import eu.decentsoftware.holograms.api.DHAPI;

public class EmeraldGeneratorTask
extends BukkitRunnable {

private final Generator generator;

private final int maxSeconds;

private int countdown;

public EmeraldGeneratorTask(
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

spawnEmerald();

countdown = maxSeconds;
}

private void updateHologram() {

DHAPI.setHologramLine(
        generator.getDisplay()
                .getHologram(),
        2,"§fGera items a cada §a" + countdown + " §fsegundos"
);
}

private void spawnEmerald() {

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
