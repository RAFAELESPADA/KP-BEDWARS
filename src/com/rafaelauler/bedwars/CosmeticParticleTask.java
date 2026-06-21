package com.rafaelauler.bedwars;


import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.scheduler.BukkitRunnable;

public class CosmeticParticleTask
        extends BukkitRunnable {


    private final ArmorStand stand;
    private double angle;

    public CosmeticParticleTask(
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

        angle += 0.15;

        for(int i = 0; i < 8; i++) {



            Location center =
                    stand.getLocation();
            center.getWorld()
            .spigot()
            .playEffect(
                    center.clone().add(
                            0,
                            1.2,
                            0
                    ),
                    Effect.HAPPY_VILLAGER
            );
        }
    }
}