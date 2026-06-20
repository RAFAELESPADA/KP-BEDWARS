package com.rafaelauler.bedwars;
import org.bukkit.Material;

public enum GeneratorType { 

    IRON(
            Material.IRON_INGOT,
            20L
    ),

    GOLD(
            Material.GOLD_INGOT,
            80L
    ),

    DIAMOND(
            Material.DIAMOND,
            600L
    ),

    EMERALD(
            Material.EMERALD,
            1200L
    );


    private final Material material;
    private final long defaultDelay;

    GeneratorType(
            Material material,
            long defaultDelay) {

        this.material = material;
        this.defaultDelay = defaultDelay;
    }

    public Material getMaterial() {
        return material;
    }

    public long getDefaultDelay() {
        return defaultDelay;
    }
}