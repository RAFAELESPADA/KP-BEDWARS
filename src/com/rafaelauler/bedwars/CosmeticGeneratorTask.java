package com.rafaelauler.bedwars;


import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.scheduler.BukkitRunnable;

public class CosmeticGeneratorTask
        extends BukkitRunnable {

    private final ArmorStand stand;

    private double angle;
    public CosmeticGeneratorTask(
            ArmorStand stand) {

        this.stand = stand;
    }

    @Override
    public void run() {

        if(stand == null
                || stand.isDead()) {

            cancel();
            return;
        }

        angle += 0.1;

        Location location =
                stand.getLocation();

        location.add(
                0,
                Math.sin(angle) * 0.03,
                0
        );

        location.setYaw(
                location.getYaw() + 5
        );


        stand.teleport(location);
    }
}