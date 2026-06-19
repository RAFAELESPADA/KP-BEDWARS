package com.rafaelauler.bedwars;


import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ShopManager {

    private final List<ShopItem> items =
            new ArrayList<>();

    public ShopManager() {

        loadDefaults();
    }
    public ShopItem getItemByDisplay(
            ItemStack itemStack) {

        if(itemStack == null)
            return null;

        for(ShopItem item : items) {

            if(item.getDisplay()
                    .isSimilar(itemStack)) {

                return item;
            }
        }

        return null;
    }
    private void loadDefaults() {

    	items.add(
    		    new ShopItem(
    		        "wool",
    		        ShopCategory.BLOCKS,

    		        new ItemBuilder(
    		                Material.WOOL,
    		                16
    		        )
    		        .name("§aWool")
    		        .lore(
    		                "",
    		                "§7Cost: §f4 Iron",
    		                "",
    		                "§eClick to purchase!"
    		        )
    		        .build(),

    		        new ItemStack(
    		                Material.WOOL,
    		                16
    		        ),

    		        ShopCurrency.IRON,
    		        4
    		    )
    		);
    	items.add(
    		    new ShopItem(
    		        "endstone",
    		        ShopCategory.BLOCKS,

    		        new ItemBuilder(
    		                Material.ENDER_STONE,
    		                12
    		        )
    		        .name("§aEnd Stone")
    		        .lore(
    		                "",
    		                "§7Cost: §f24 Iron",
    		                "",
    		                "§eClick to purchase!"
    		        )
    		        .build(),

    		        new ItemStack(
    		                Material.ENDER_STONE,
    		                12
    		        ),

    		        ShopCurrency.IRON,
    		        24
    		    )
    		);
    	items.add(
    		    new ShopItem(
    		        "wood",
    		        ShopCategory.BLOCKS,

    		        new ItemBuilder(
    		                Material.WOOD,
    		                16
    		        )
    		        .name("§aOak Wood")
    		        .lore(
    		                "",
    		                "§7Cost: §64 Gold",
    		                "",
    		                "§eClick to purchase!"
    		        )
    		        .build(),

    		        new ItemStack(
    		                Material.WOOD,
    		                16
    		        ),

    		        ShopCurrency.GOLD,
    		        4
    		    )
    		);

    	ShopItem stoneSword =
                new ShopItem(
                        "stone_sword",
                        ShopCategory.MELEE,

                        createItem(
                                Material.STONE_SWORD,
                                1,
                                "§aStone Sword",
                                "",
                                "§7Cost: §f10 Iron",
                                "",
                                "§eClick to purchase!"
                        ),

                        new ItemStack(
                                Material.STONE_SWORD
                        ),

                        ShopCurrency.IRON,
                        10
                );

        stoneSword.setSwordTier(
                SwordTier.STONE
        );

        items.add(stoneSword);
        ShopItem ironSword =
                new ShopItem(
                        "iron_sword",
                        ShopCategory.MELEE,

                        createItem(
                                Material.IRON_SWORD,
                                1,
                                "§aIron Sword",
                                "",
                                "§7Cost: §64 Gold",
                                "",
                                "§eClick to purchase!"
                        ),

                        new ItemStack(
                                Material.IRON_SWORD
                        ),

                        ShopCurrency.GOLD,
                        7
                );

        ironSword.setSwordTier(
                SwordTier.IRON
        );

        items.add(ironSword);
        ShopItem diamondSword =
                new ShopItem(
                        "diamond_sword",
                        ShopCategory.MELEE,

                        createItem(
                                Material.DIAMOND_SWORD,
                                1,
                                "§aDiamond Sword",
                                "",
                                "§7Cost: §26 Emeralds",
                                "",
                                "§eClick to purchase!"
                        ),

                        new ItemStack(
                                Material.DIAMOND_SWORD
                        ),

                        ShopCurrency.EMERALD,
                        6
                );

        diamondSword.setSwordTier(
                SwordTier.DIAMOND
        );

        items.add(diamondSword);

    	items.add(
    	        new ShopItem(
    	                "tnt",
    	                ShopCategory.UTILITIES,

    	                createItem(
    	                        Material.TNT,
    	                        1,
    	                        "§aTNT",
    	                        "",
    	                        "§7Preço: §64 Ouros",
    	                        "",
    	                        "§eClique para comprar!"
    	                ),

    	                new ItemStack(
    	                        Material.TNT
    	                ),

    	                ShopCurrency.GOLD,
    	                4
    	        )
    	);
    	items.add(
    		    new ShopItem(
    		        "fireball",
    		        ShopCategory.UTILITIES,

    		        new ItemBuilder(
    		                Material.FIREBALL
    		        )
    		        .name("§aFireball")
    		        .lore(
    		                "",
    		                "§7Cost: §640 Iron",
    		                "",
    		                "§eClick to purchase!"
    		        )
    		        .build(),

    		        new ItemStack(
    		                Material.FIREBALL
    		        ),

    		        ShopCurrency.IRON,
    		        7
    		    )
    		);
    	ShopItem chainmailArmor =
    	        new ShopItem(
    	                "chainmail_armor",
    	                ShopCategory.ARMOR,

    	                createItem(
    	                        Material.CHAINMAIL_BOOTS,
    	                        1,
    	                        "§aChainmail Armor",
    	                        "",
    	                        "§7Cost: §f40 Iron",
    	                        "",
    	                        "§eClick to purchase!"
    	                ),

    	                new ItemStack(
    	                        Material.CHAINMAIL_BOOTS
    	                ),

    	                ShopCurrency.IRON,
    	                40
    	        );

    	chainmailArmor.setArmorTier(
    	        ArmorTier.CHAINMAIL
    	);

    	items.add(chainmailArmor);
    	ShopItem ironArmor =
    	        new ShopItem(
    	                "iron_armor",
    	                ShopCategory.ARMOR,

    	                createItem(
    	                        Material.IRON_BOOTS,
    	                        1,
    	                        "§aIron Armor",
    	                        "",
    	                        "§7Cost: §612 Gold",
    	                        "",
    	                        "§eClick to purchase!"
    	                ),

    	                new ItemStack(
    	                        Material.IRON_BOOTS
    	                ),

    	                ShopCurrency.GOLD,
    	                12
    	        );

    	ironArmor.setArmorTier(
    	        ArmorTier.IRON
    	);

    	items.add(ironArmor);
    	ShopItem diamondArmor =
    	        new ShopItem(
    	                "diamond_armor",
    	                ShopCategory.ARMOR,

    	                createItem(
    	                        Material.DIAMOND_BOOTS,
    	                        1,
    	                        "§aDiamond Armor",
    	                        "",
    	                        "§7Cost: §26 Emeralds",
    	                        "",
    	                        "§eClick to purchase!"
    	                ),

    	                new ItemStack(
    	                        Material.DIAMOND_BOOTS
    	                ),

    	                ShopCurrency.EMERALD,
    	                6
    	        );

    	diamondArmor.setArmorTier(
    	        ArmorTier.DIAMOND
    	);

    	items.add(diamondArmor);
    	    
    }
    private ItemStack createItem(
            Material material,
            int amount,
            String name,
            String... lore) {

        ItemStack item =
                new ItemStack(
                        material,
                        amount
                );

        ItemMeta meta =
                item.getItemMeta();

        meta.setDisplayName(name);

        meta.setLore(
                Arrays.asList(lore)
        );

        item.setItemMeta(meta);

        return item;
    }

    public List<ShopItem> getItems() {
        return items;
    }
}