package com.rafaelauler.bedwars;


import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class GeneratorTask
        extends BukkitRunnable {

    private final Generator generator;

    public GeneratorTask(
            Generator generator) {

        this.generator = generator;
    }

    @Override
    public void run() {

        Location location =
                generator.getLocation();

        location.getWorld()
                .dropItemNaturally(
                        location,
                        new ItemStack(
                                generator
                                        .getType()
                                        .getMaterial()
                        )
                );
    }
}
