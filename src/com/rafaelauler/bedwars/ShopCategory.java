package com.rafaelauler.bedwars;

public enum ShopCategory {

    QUICK_BUY("§eQuick Buy"),

    BLOCKS("§6Blocks"),

    MELEE("§cMelee"),

    ARMOR("§bArmor"),

    TOOLS("§eTools"),

    RANGED("§aRanged"),

    POTIONS("§dPotions"),

    UTILITIES("§9Utilities");

    private final String display;

    ShopCategory(
            String display) {

        this.display = display;
    }

    public String getDisplay() {
        return display;
    }
}