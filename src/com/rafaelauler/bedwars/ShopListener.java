package com.rafaelauler.bedwars;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class ShopListener implements Listener {

    @EventHandler
    public void onClick(
            InventoryClickEvent e) {

        if(!(e.getWhoClicked()
                instanceof Player))
            return;

        Player player =
                (Player) e.getWhoClicked();

        if(e.getCurrentItem() == null)
            return;

        String title =
                e.getView().getTitle();

        /*
         * MENU PRINCIPAL
         */
        if(title.equals("Item Shop")) {

            e.setCancelled(true);

            Material material =
                    e.getCurrentItem()
                            .getType();

            switch(material) {

                case NETHER_STAR:

                    CategoryMenu.open(
                            player,
                            ShopCategory.QUICK_BUY
                    );

                    break;

                case SANDSTONE:

                    CategoryMenu.open(
                            player,
                            ShopCategory.BLOCKS
                    );

                    break;

                case IRON_SWORD:

                    CategoryMenu.open(
                            player,
                            ShopCategory.MELEE
                    );

                    break;

                case CHAINMAIL_CHESTPLATE:

                    CategoryMenu.open(
                            player,
                            ShopCategory.ARMOR
                    );

                    break;

                case IRON_PICKAXE:

                    CategoryMenu.open(
                            player,
                            ShopCategory.TOOLS
                    );

                    break;

                case BOW:

                    CategoryMenu.open(
                            player,
                            ShopCategory.RANGED
                    );

                    break;

                case BREWING_STAND_ITEM:

                    CategoryMenu.open(
                            player,
                            ShopCategory.POTIONS
                    );

                    break;

                case TNT:

                    CategoryMenu.open(
                            player,
                            ShopCategory.UTILITIES
                    );

                    break;
            }

            return;
        }

        /*
         * MENUS DE CATEGORIA
         */
        for(ShopCategory category :
                ShopCategory.values()) {

            if(!title.equals(
                    category.getDisplay()
            ))
                continue;

            e.setCancelled(true);
            if(e.getCurrentItem() == null)
                return;

            if(e.getCurrentItem().getType() == Material.AIR)
                return;

            if(!e.getCurrentItem().hasItemMeta())
                return;

            if(e.getCurrentItem()
                    .getItemMeta()
                    .hasDisplayName()) {
            ShopItem shopItem =
                    Bedwars.getInstance()
                            .getShopManager()
                            .getItemByDisplay(
                                    e.getCurrentItem()
                            );
            if(e.getCurrentItem() == null)
                return;

            if(e.getCurrentItem().getType()
                    == Material.AIR)
                return;
            if(shopItem == null) {

                player.sendMessage(
                        "§cItem não encontrado."
                );

                return;
            }
            
            buy(
                    player,
                    shopItem
            );

            return;
        }
        }
    }

    private void buy(
            Player player,
            ShopItem item) {

        Material currency =
                item.getCurrency()
                        .getMaterial();

        int amount =
                item.getCost();

        if(!hasEnough(
                player,
                currency,
                amount
        )) {

            player.sendMessage(
                    "§cVocê não possui recursos suficientes."
            );

            return;
        }
        if(item.getSwordTier() != null) {

            GamePlayer gp =
                    Bedwars.getInstance()
                            .getPlayerManager()
                            .get(player);

            if(gp.getSwordTier()
                    .ordinal()
                    >= item.getSwordTier()
                            .ordinal()) {

                player.sendMessage(
                        "§cVocê já possui uma espada igual ou melhor."
                );

                return;
            }
        }
        if(item.getArmorTier() != null) {

            GamePlayer gp =
                    Bedwars.getInstance()
                            .getPlayerManager()
                            .get(player);

            if(gp.getArmorTier()
                    .ordinal()
                    >= item.getArmorTier()
                            .ordinal()) {

                player.sendMessage(
                        "§cVocê já possui uma armadura melhor."
                );

                return;
            }
        }
        if(item.getToolTier() != null) {

            GamePlayer gp =
                    Bedwars.getInstance()
                            .getPlayerManager()
                            .get(player);

            ToolTier current =
                    item.getToolType()
                            == ToolType.PICKAXE

                    ?

                    gp.getPickaxeTier()

                    :

                    gp.getAxeTier();

            if(current != null &&
                    current.ordinal()
                            >= item.getToolTier()
                            .ordinal()) {

                player.sendMessage(
                        "§cVocê já possui uma ferramenta melhor."
                );

                return;
            }
        }
        removeCurrency(
                player,
                currency,
                amount
        );
        if(item.getToolTier() != null) {

            GamePlayer gp =
                    Bedwars.getInstance()
                            .getPlayerManager()
                            .get(player);

            Bedwars.getInstance()
                    .getToolManager()
                    .upgrade(
                            player,
                            gp,
                            item.getToolType(),
                            item.getToolTier()
                    );
            player.updateInventory();
            return;
        }
        if(item.getArmorTier() != null) {

            GamePlayer gp =
                    Bedwars.getInstance()
                            .getPlayerManager()
                            .get(player);

            Bedwars.getInstance()
                    .getArmorManager()
                    .upgrade(
                            player,
                            gp,
                            item.getArmorTier()
                    );
            player.updateInventory();
            return;
        }
        if(item.getSwordTier()
                != null) {

            GamePlayer gp =
                    Bedwars.getInstance()
                            .getPlayerManager()
                            .get(player);

            Bedwars.getInstance()
                    .getSwordManager()
                    .upgrade(
                            player,
                            gp,
                            item.getSwordTier()
                    );
            player.updateInventory();
            return;
        }
        if(item.getDisplay().getType().name().contains("_SWORD") && !item.getDisplay().getType().name().contains("_AXE")) {
        player.getInventory()
        .addItem(
                item.getReward()
        );
        }
        player.sendMessage(
                "§aCompra realizada."
        );
        player.updateInventory();
    }

    private boolean hasEnough(
            Player player,
            Material material,
            int amount) {

        int found = 0;

        for(ItemStack item :
                player.getInventory()
                        .getContents()) {

            if(item == null)
                continue;

            if(item.getType()
                    != material)
                continue;

            found += item.getAmount();
        }

        return found >= amount;
    }

    private void removeCurrency(Player player, Material material, int amount) {

        ItemStack[] contents = player.getInventory().getContents();

        for (int i = 0; i < contents.length; i++) {

            ItemStack item = contents[i];

            if (item == null)
                continue;

            if (item.getType() != material)
                continue;

            if (amount <= 0)
                break;

            if (item.getAmount() <= amount) {

                amount -= item.getAmount();

                contents[i] = null;

            } else {

                item.setAmount(item.getAmount() - amount);

                amount = 0;
            }
        }

        player.getInventory().setContents(contents);
        player.updateInventory();
    }}