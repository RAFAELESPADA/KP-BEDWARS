package com.rafaelauler.bedwars;


import eu.decentsoftware.holograms.api.DHAPI;
import eu.decentsoftware.holograms.api.holograms.Hologram;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.EulerAngle;

import java.util.Arrays;

public class GeneratorDisplayFactory {
    public static GeneratorDisplay create(
            Generator generator,
            String id) {
        Location location =
                generator.getLocation();
        ArmorStand armorStand = location.getWorld().spawn(
                location.clone().add(0, 1.25, 0),
                ArmorStand.class
        );
        armorStand.setVisible(false);
        armorStand.setGravity(false);
        armorStand.setBasePlate(false);
        armorStand.setArms(false);

        if (generator.getType() == GeneratorType.EMERALD) {
        	armorStand.getEquipment().setHelmet(new ItemStack(Material.EMERALD_BLOCK));
        } else if (generator.getType() == GeneratorType.DIAMOND) {
        	armorStand.getEquipment().setHelmet(new ItemStack(Material.DIAMOND_BLOCK));
        }

        final Location base = armorStand.getLocation().clone();

        new BukkitRunnable() {

            double rotation = 0;
            double time = 0;

            @Override
            public void run() {

                if (armorStand.isDead()) {
                    cancel();
                    return;
                }

                // Rotação
                rotation += Math.toRadians(5);

                if (rotation >= Math.PI * 2) {
                    rotation = 0;
                }

                armorStand.setHeadPose(
                        new EulerAngle(
                                0,
                                rotation,
                                0
                        )
                );

                // Flutuação
                time += 0.12;

                double offset = Math.sin(time) * 0.15;

                armorStand.teleport(
                        base.clone().add(0, offset, 0)
                );
            }

        }.runTaskTimer(
                Bedwars.getInstance(),
                1L,
                1L
        );
         

        Hologram hologram =
                DHAPI.createHologram(
                        id,
                        location.clone()
                                .add(0, 3.0, 0)
                );
if (generator.getType() == GeneratorType.DIAMOND || generator.getType() == GeneratorType.EMERALD) {
        DHAPI.setHologramLines(
                hologram,
                Arrays.asList(
                        generator.getType()
                                == GeneratorType.DIAMOND
                                ?
                                "§b♦ Gerador de Diamante"
                                :
                                "§a✦ Gerador de Esmeralda",
                        "§eNível I",
                        "§fGera items a cada §a0 §fsegundos"
                )
        );
}

        return new GeneratorDisplay(
                armorStand,
                hologram
        );
    }
}
