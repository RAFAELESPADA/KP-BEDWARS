package com.rafaelauler.bedwars;


import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.EulerAngle;

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


        double y = 0;
        boolean increase = true;
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
    }
}