package com.rafaelauler.bedwars;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ToolManager {

    public boolean upgrade(
            Player player,
            GamePlayer gp,
            ToolType type,
            ToolTier tier) {

        ToolTier current =
                getCurrentTier(
                        gp,
                        type
                );

        if(current != null &&
                current.ordinal()
                        >= tier.ordinal()) {

            player.sendMessage(
                    "§cVocê já possui uma ferramenta melhor."
            );

            return false;
        }

        setTier(
                gp,
                type,
                tier
        );

        equip(
                player,
                gp
        );

        return true;
    }

    private ToolTier getCurrentTier(
            GamePlayer gp,
            ToolType type) {

        switch(type) {

            case PICKAXE:
                return gp.getPickaxeTier();

            case AXE:
                return gp.getAxeTier();
        }

        return null;
    }

    private void setTier(
            GamePlayer gp,
            ToolType type,
            ToolTier tier) {

        switch(type) {

            case PICKAXE:

                gp.setPickaxeTier(
                        tier
                );

                break;

            case AXE:

                gp.setAxeTier(
                        tier
                );

                break;
        }
    }

    public void equip(
            Player player,
            GamePlayer gp) {

        removeTools(player);

        if(gp.getPickaxeTier() != null) {

            player.getInventory()
                    .addItem(
                            new ItemStack(
                                    getPickaxeMaterial(
                                            gp.getPickaxeTier()
                                    )
                            )
                    );
        }

        if(gp.getAxeTier() != null) {

            player.getInventory()
                    .addItem(
                            new ItemStack(
                                    getAxeMaterial(
                                            gp.getAxeTier()
                                    )
                            )
                    );
        }
    }

    private void removeTools(
            Player player) {

        player.getInventory()
                .remove(Material.WOOD_PICKAXE);

        player.getInventory()
                .remove(Material.STONE_PICKAXE);

        player.getInventory()
                .remove(Material.IRON_PICKAXE);

        player.getInventory()
                .remove(Material.DIAMOND_PICKAXE);

        player.getInventory()
                .remove(Material.WOOD_AXE);

        player.getInventory()
                .remove(Material.STONE_AXE);

        player.getInventory()
                .remove(Material.IRON_AXE);

        player.getInventory()
                .remove(Material.DIAMOND_AXE);
    }

    private Material getPickaxeMaterial(
            ToolTier tier) {

        switch(tier) {

            case WOOD:
                return Material.WOOD_PICKAXE;

            case STONE:
                return Material.STONE_PICKAXE;

            case IRON:
                return Material.IRON_PICKAXE;

            case DIAMOND:
                return Material.DIAMOND_PICKAXE;
        }

        return Material.WOOD_PICKAXE;
    }

    private Material getAxeMaterial(
            ToolTier tier) {

        switch(tier) {

            case WOOD:
                return Material.WOOD_AXE;

            case STONE:
                return Material.STONE_AXE;

            case IRON:
                return Material.IRON_AXE;

            case DIAMOND:
                return Material.DIAMOND_AXE;
        }

        return Material.WOOD_AXE;
    }
}
