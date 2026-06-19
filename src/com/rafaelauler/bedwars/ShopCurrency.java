package com.rafaelauler.bedwars;


import org.bukkit.Material;

public enum ShopCurrency {

    IRON(Material.IRON_INGOT),

    GOLD(Material.GOLD_INGOT),

    DIAMOND(Material.DIAMOND),

    EMERALD(Material.EMERALD);

    private final Material material;

    ShopCurrency(
            Material material) {

        this.material = material;
    }

    public Material getMaterial() {
        return material;
    }
}
