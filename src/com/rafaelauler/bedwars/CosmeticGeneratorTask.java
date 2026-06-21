package com.rafaelauler.bedwars;

import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.EulerAngle;

public class CosmeticGeneratorTask
        extends BukkitRunnable {

    private final ArmorStand stand;

    private final Location baseLocation;

    private double rotation;

    private double height;

    private boolean up = true;

    public CosmeticGeneratorTask(
            ArmorStand stand) {

        this.stand = stand;

        this.baseLocation =
                stand.getLocation()
                        .clone();
    }

    @Override
    public void run() {

        if(stand == null
                || stand.isDead()) {

            cancel();
            return;
        }

        rotation += 0.15;

        stand.setHeadPose(
                new EulerAngle(
                        0,
                        rotation,
                        0
                )
        );

        if(up) {

            height += 0.01;

            if(height >= 0.25)
                up = false;

        } else {

            height -= 0.01;

            if(height <= 0)
                up = true;
        }

        Location location =
                baseLocation.clone();

        location.add(
                0,
                height,
                0
        );

        stand.teleport(
                location
        );
    }
}