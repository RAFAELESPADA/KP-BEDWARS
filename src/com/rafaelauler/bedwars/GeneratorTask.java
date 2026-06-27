package com.rafaelauler.bedwars;


import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.EulerAngle;

public class GeneratorTask
        extends BukkitRunnable {

    private final Generator generator;

    public GeneratorTask(
            Generator generator) {

        this.generator = generator;
    }

    double y = 0;
    boolean increase = true;
    @Override
    public void run() {

        Location location =
                generator.getLocation();
    	ArmorStand armorStand = null;
    	if (armorStand == null) {
		armorStand = (ArmorStand) generator.getLocation().getWorld().spawnEntity(generator.getLocation().add(0, 1, 0), EntityType.ARMOR_STAND);
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
        
        location.getWorld()
                .dropItemNaturally(
                        location,
                        new ItemStack(
                                generator
                                        .getType()
                                        .getMaterial()
                        )
                );
    }
}
