package com.rafaelauler.bedwars;

import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.EulerAngle;

public class CosmeticGeneratorTask extends BukkitRunnable {

    private final ArmorStand stand;
    double y = 0;

    boolean increase = true;
    private final Location baseLocation;
    private double time = 0;

    public CosmeticGeneratorTask(ArmorStand stand) {

        this.stand = stand;
        this.baseLocation = stand.getLocation().clone();
        stand.setMetadata(
                "KPBEDWARS_COSMETIC",
                new FixedMetadataValue(
                        Bedwars.getInstance(),
                        true
                )
        );
    }

    @Override
    public void run() {

        if (stand == null || stand.isDead()) {
            cancel();
            return;
        }

                time += 0.12;

                double offsetY = Math.sin(time) * 0.15;
                stand.teleport(
                        baseLocation.clone().add(0, offsetY, 0)
                );
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
                stand.setHeadPose(
                        new EulerAngle(
                                0,
                                y,
                                0
                        )
                );
    }
}