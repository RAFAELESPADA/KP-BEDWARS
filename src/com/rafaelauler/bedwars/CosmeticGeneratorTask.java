package com.rafaelauler.bedwars;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.EulerAngle;

public class CosmeticGeneratorTask
        extends BukkitRunnable {

    private final ArmorStand stand;

    private final Location baseLocation;
    private final List<ArmorStand> cosmetics =
	        new ArrayList<>();
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
double y = 0;

boolean increase = true;
        if(stand == null
                || stand.isDead()) {

            cancel();
            return;
        }

        if (y >= Math.PI * 6) {
            increase = false;
        } else if (y <= 0) {
            increase = true;
        }
        if (increase) {
            y += 0.2;
        } else {
            y -= 0.2;
        }

        stand.setHeadPose(new EulerAngle(0, y, 0));
        stand.setHeadPose(new EulerAngle(0, y, 0));
        stand.setMetadata(
                "KPBEDWARS_COSMETIC",
                new FixedMetadataValue(
                        Bedwars.getInstance(),
                        true
                )
        );
        cosmetics.add(stand);
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