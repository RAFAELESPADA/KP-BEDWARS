package com.rafaelauler.bedwars;

import org.bukkit.entity.ArmorStand;
import org.bukkit.scheduler.BukkitRunnable;

public class FloatingItemTask extends BukkitRunnable {

    private final ArmorStand stand;
    private float yaw = 0;

    public FloatingItemTask(ArmorStand stand) {
        this.stand = stand;
    }

    @Override
    public void run() {

        if (stand == null || stand.isDead()) {
            cancel();
            return;
        }

        yaw += 5F;

        if (yaw >= 360F)
            yaw = 0;

        Location loc = stand.getLocation();

        loc.setYaw(yaw);

        stand.teleport(loc);
    }
}