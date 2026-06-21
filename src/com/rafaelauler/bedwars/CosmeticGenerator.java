package com.rafaelauler.bedwars;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.inventory.ItemStack;

public class CosmeticGenerator {

    private final ArmorStand stand;

    public CosmeticGenerator(
            Location location,
            Material material) {

        stand = location.getWorld()
                .spawn(
                        location,
                        ArmorStand.class
                );

        stand.setVisible(false);
        stand.setGravity(false);
        stand.setSmall(true);
        stand.setArms(true);
        stand.setBasePlate(false);

        stand.setHelmet(
                new ItemStack(material)
        );
    }

    public ArmorStand getStand() {
        return stand;
    }
}
