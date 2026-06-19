package com.rafaelauler.bedwars;

import org.bukkit.inventory.ItemStack;

public class ShopItem {

    private final ShopCategory category;

    private final ItemStack display;
    private ArmorTier armorTier;
    private final ItemStack reward;
    private SwordTier swordTier;
    private final ShopCurrency currency;
    private final String id;
    private final int cost;
    private ToolType toolType;

    private ToolTier toolTier;

    public ShopItem(
            String id,
            ShopCategory category,
            ItemStack display,
            ItemStack reward,
            ShopCurrency currency,
            int cost) {

        this.id = id;
        this.category = category;
        this.display = display;
        this.reward = reward;
        this.currency = currency;
        this.cost = cost;
        
    }

    public ShopCategory getCategory() {
        return category;
    }
    public SwordTier getSwordTier() {
        return swordTier;
    }
    public ArmorTier getArmorTier() {
        return armorTier;
    }
    public void setArmorTier(
            ArmorTier armorTier) {

        this.armorTier = armorTier;
    }
    public ToolType getToolType() {
        return toolType;
    }

    public void setToolType(
            ToolType toolType) {

        this.toolType = toolType;
    }

    public ToolTier getToolTier() {
        return toolTier;
    }

    public void setToolTier(
            ToolTier toolTier) {

        this.toolTier = toolTier;
    }
    public ItemStack getDisplay() {
        return display;
    }
    public void setSwordTier(
            SwordTier swordTier) {

        this.swordTier = swordTier;
    }
    public String getId() {
        return id;
    }
    public ItemStack getReward() {
        return reward;
    }

    public ShopCurrency getCurrency() {
        return currency;
    }

    public int getCost() {
        return cost;
    }
}
