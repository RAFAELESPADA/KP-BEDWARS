package com.rafaelauler.bedwars;


import eu.decentsoftware.holograms.api.DHAPI;
import eu.decentsoftware.holograms.api.holograms.Hologram;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
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

        ArmorStand armorStand =
                location.getWorld().spawn(
                        location.clone().add(0, 1.25, 0),
                        ArmorStand.class
                );
        Material helmet;
     	new BukkitRunnable() {

            double y = 0;
            boolean increase = true;
        @Override
        public void run() {
        	if (armorStand == null || armorStand.isDead()) {
        		cancel();
        	}
		armorStand.setVisible(false);		
		armorStand.setGravity(false);
		if (generator.getType() == GeneratorType.EMERALD) {
		     armorStand.getEquipment().setHelmet(new ItemStack(Material.EMERALD_BLOCK));

		}
		else if (generator.getType() == GeneratorType.DIAMOND) {
		     armorStand.getEquipment().setHelmet(new ItemStack(Material.DIAMOND_BLOCK));

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

                armorStand.setHeadPose(new EulerAngle(0, y, 0));
                armorStand.setHeadPose(new EulerAngle(0, y, 0));
        }	
		  	
	  	}.runTaskTimer(Bedwars.getInstance(), 1, 1);
       
         

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
                stand,
                hologram
        );
    }
}
