package com.rafaelauler.bedwars;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ShopMenu {

    public static void open(Player player) {

        Inventory inv =
                Bukkit.createInventory(
                        null,
                        54,
                        "Item Shop"
                );

        inv.setItem(
                10,
                createItem(
                        Material.NETHER_STAR,
                        "§eCategorias",
                        "§7Clique para navegar",
                        "§7pelas categorias da loja."
                )
        );

        inv.setItem(
                19,
                createItem(
                        Material.SANDSTONE,
                        "§6Blocos",
                        "§7Compre blocos para",
                        "§7proteger sua cama."
                )
        );

        inv.setItem(
                20,
                createItem(
                        Material.IRON_SWORD,
                        "§cArmas",
                        "§7Espadas e itens",
                        "§7para combate."
                )
        );

        inv.setItem(
                21,
                createItem(
                        Material.CHAINMAIL_CHESTPLATE,
                        "§bArmaduras",
                        "§7Melhore sua defesa",
                        "§7contra inimigos."
                )
        );

        inv.setItem(
                22,
                createItem(
                        Material.IRON_PICKAXE,
                        "§aFerramentas",
                        "§7Picaretas e machados",
                        "§7para quebrar blocos."
                )
        );

        inv.setItem(
                23,
                createItem(
                        Material.BOW,
                        "§dArcos",
                        "§7Ataque inimigos",
                        "§7à distância."
                )
        );

        inv.setItem(
                24,
                createItem(
                        Material.BREWING_STAND_ITEM,
                        "§5Poções",
                        "§7Poções de velocidade,",
                        "§7invisibilidade e mais."
                )
        );

        inv.setItem(
                25,
                createItem(
                        Material.TNT,
                        "§4Utilidades",
                        "§7TNT, fireballs e",
                        "§7outros itens especiais."
                )
        );

        player.openInventory(inv);
    }

    private static ItemStack createItem(
            Material material,
            String name,
            String... lore) {

        ItemStack item =
                new ItemStack(material);

        ItemMeta meta =
                item.getItemMeta();

        meta.setDisplayName(name);

        meta.setLore(
                Arrays.asList(lore)
        );

        item.setItemMeta(meta);

        return item;
    }
}