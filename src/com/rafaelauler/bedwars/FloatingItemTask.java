package com.rafaelauler.bedwars;

import org.bukkit.entity.ArmorStand;
import org.bukkit.scheduler.BukkitRunnable;

public class FloatingItemTask
        extends BukkitRunnable {

    private final ArmorStand stand;

    private float yaw;

    public FloatingItemTask(
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

        yaw += 5;

        stand.teleport(
                stand.getLocation()
                        .setDirection(
                                stand.getLocation()
                                        .getDirection()
                        )
        );

        stand.setHeadPose(
                stand.getHeadPose()
        );

      
    }
}
