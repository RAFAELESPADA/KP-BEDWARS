package com.rafaelauler.bedwars;


import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.EulerAngle;

public class GeneratorTask
        extends BukkitRunnable {

    private final Generator generator;

    public GeneratorTask(
            Generator generator) {

        this.generator = generator;
    }

    double y = 0;
    boolean increase = true;
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
