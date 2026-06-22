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