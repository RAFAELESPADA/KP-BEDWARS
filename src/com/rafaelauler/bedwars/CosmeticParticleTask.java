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

            double current =
                    angle +
                    ((Math.PI * 2) / 8)
                    * i;

            double x =
                    Math.cos(current)
                    * 0.8;

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
            double z =
                    Math.sin(current)
                    * 0.8;
            double y =
                    (Math.sin(angle * 2) + 1)
                    * 0.5;
            Location particle =
                    center.clone().add(
                            x,
                            y,
                            z
                    );
            Effect effect =
                    angle % 2 < 1

                    ? Effect.MAGIC_CRIT

                    : Effect.CRIT;

            center.getWorld()
                    .spigot()
                    .playEffect(
                            particle,
                            effect
                    );
        }
    }
}