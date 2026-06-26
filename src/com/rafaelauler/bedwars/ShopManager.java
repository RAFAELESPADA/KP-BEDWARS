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
    		                "§eClique para comprar!"
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
    		                "§eClique para comprar!"
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
    		                "§eClique para comprar!"
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
                                "§eClique para comprar!"
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
                                "§eClique para comprar!"
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
                                "§eClique para comprar!"
                        ),

                        new ItemStack(
                                Material.DIAMOND_SWORD
                        ),

                        ShopCurrency.EMERALD,
                        6
                );
        items.add(new ShopItem(
                "blast_proof_glass",
                ShopCategory.BLOCKS,
                createItem(
                        Material.GLASS,
                        4,
                        "§aBlast-Proof Glass",
                        "",
                        "§7Cost: §f12 Iron",
                        "",
                        "§eClique para comprar!"
                ),
                new ItemStack(Material.GLASS, 4),
                ShopCurrency.IRON,
                12
        ));

        items.add(new ShopItem(
                "ladder",
                ShopCategory.BLOCKS,
                createItem(
                        Material.LADDER,
                        16,
                        "§aLadder",
                        "",
                        "§7Cost: §f4 Iron",
                        "",
                        "§eClique para comprar!"
                ),
                new ItemStack(Material.LADDER, 16),
                ShopCurrency.IRON,
                4
        ));

        items.add(new ShopItem(
                "obsidian",
                ShopCategory.BLOCKS,
                createItem(
                        Material.OBSIDIAN,
                        4,
                        "§aObsidian",
                        "",
                        "§7Cost: §24 Emeralds",
                        "",
                        "§eClique para comprar!"
                ),
                new ItemStack(Material.OBSIDIAN, 4),
                ShopCurrency.EMERALD,
                4
        ));
        items.add(new ShopItem(
                "shears",
                ShopCategory.TOOLS,
                createItem(
                        Material.SHEARS,
                        1,
                        "§aShears",
                        "",
                        "§7Cost: §f20 Iron",
                        "",
                        "§eClique para comprar!"
                ),
                new ItemStack(Material.SHEARS),
                ShopCurrency.IRON,
                20
        ));

        items.add(new ShopItem(
                "wood_pickaxe",
                ShopCategory.TOOLS,
                createItem(
                        Material.WOOD_PICKAXE,
                        1,
                        "§aWooden Pickaxe",
                        "",
                        "§7Cost: §f10 Iron",
                        "",
                        "§eClique para comprar!"
                ),
                new ItemStack(Material.WOOD_PICKAXE),
                ShopCurrency.IRON,
                10
        ));

        items.add(new ShopItem(
                "stone_pickaxe",
                ShopCategory.TOOLS,
                createItem(
                        Material.STONE_PICKAXE,
                        1,
                        "§aStone Pickaxe",
                        "",
                        "§7Cost: §f20 Iron",
                        "",
                        "§eClique para comprar!"
                ),
                new ItemStack(Material.STONE_PICKAXE),
                ShopCurrency.IRON,
                20
        ));

        items.add(new ShopItem(
                "iron_pickaxe",
                ShopCategory.TOOLS,
                createItem(
                        Material.IRON_PICKAXE,
                        1,
                        "§aIron Pickaxe",
                        "",
                        "§7Cost: §66 Gold",
                        "",
                        "§eClique para comprar!"
                ),
                new ItemStack(Material.IRON_PICKAXE),
                ShopCurrency.GOLD,
                6
        ));
        items.add(new ShopItem(
                "wood_axe",
                ShopCategory.TOOLS,
                createItem(
                        Material.WOOD_AXE,
                        1,
                        "§aWooden Axe",
                        "",
                        "§7Cost: §f10 Iron",
                        "",
                        "§eClique para comprar!"
                ),
                new ItemStack(Material.WOOD_AXE),
                ShopCurrency.IRON,
                10
        ));
        items.add(new ShopItem(
                "stone_axe",
                ShopCategory.TOOLS,
                createItem(
                        Material.STONE_AXE,
                        1,
                        "§aStone Axe",
                        "",
                        "§7Cost: §f20 Iron",
                        "",
                        "§eClique para comprar!"
                ),
                new ItemStack(Material.STONE_AXE),
                ShopCurrency.IRON,
                20
        ));
        items.add(new ShopItem(
                "iron_axe",
                ShopCategory.TOOLS,
                createItem(
                        Material.IRON_AXE,
                        1,
                        "§aIron Axe",
                        "",
                        "§7Cost: §66 Gold",
                        "",
                        "§eClique para comprar!"
                ),
                new ItemStack(Material.IRON_AXE),
                ShopCurrency.GOLD,
                6
        ));
        items.add(new ShopItem(
                "bow",
                ShopCategory.RANGED,
                createItem(
                        Material.BOW,
                        1,
                        "§aBow",
                        "",
                        "§7Cost: §612 Gold",
                        "",
                        "§eClique para comprar!"
                ),
                new ItemStack(Material.BOW),
                ShopCurrency.GOLD,
                12
        ));

        items.add(new ShopItem(
                "arrow",
                ShopCategory.RANGED,
                createItem(
                        Material.ARROW,
                        8,
                        "§aArrow",
                        "",
                        "§7Cost: §62 Gold",
                        "",
                        "§eClique para comprar!"
                ),
                new ItemStack(Material.ARROW, 8),
                ShopCurrency.GOLD,
                2
        ));
        items.add(new ShopItem(
                "golden_apple",
                ShopCategory.POTIONS,
                createItem(
                        Material.GOLDEN_APPLE,
                        1,
                        "§aGolden Apple",
                        "",
                        "§7Cost: §63 Gold",
                        "",
                        "§eClique para comprar!"
                ),
                new ItemStack(Material.GOLDEN_APPLE),
                ShopCurrency.GOLD,
                3
        ));
        items.add(new ShopItem(
                "ender_pearl",
                ShopCategory.UTILITIES,
                createItem(
                        Material.ENDER_PEARL,
                        1,
                        "§aEnder Pearl",
                        "",
                        "§7Cost: §24 Emeralds",
                        "",
                        "§eClique para comprar!"
                ),
                new ItemStack(Material.ENDER_PEARL),
                ShopCurrency.EMERALD,
                4
        ));

        items.add(new ShopItem(
                "water_bucket",
                ShopCategory.UTILITIES,
                createItem(
                        Material.WATER_BUCKET,
                        1,
                        "§aWater Bucket",
                        "",
                        "§7Cost: §63 Gold",
                        "",
                        "§eClique para comprar!"
                ),
                new ItemStack(Material.WATER_BUCKET),
                ShopCurrency.GOLD,
                3
        ));

        items.add(new ShopItem(
                "compass",
                ShopCategory.UTILITIES,
                createItem(
                        Material.COMPASS,
                        1,
                        "§aTracker Compass",
                        "",
                        "§7Cost: §24 Emeralds",
                        "",
                        "§eClique para comprar!"
                ),
                new ItemStack(Material.COMPASS),
                ShopCurrency.EMERALD,
                4
        ));


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