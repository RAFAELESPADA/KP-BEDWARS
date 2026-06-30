package com.rafaelauler.bedwars;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;


public class ArenaSelectorMenu implements Listener {

    public static void open(Player player) {

        Inventory inventory =
                Bukkit.createInventory(
                        null,
                        27,
                        "§8BedWars"
                );



        // Entrar rápido
        inventory.setItem(
                11,

                new ItemBuilder(Material.NETHER_STAR)

                        .name("§aEntrar Rápido")

                        .addLore("§7Entre automaticamente")
                        .addLore("§7na melhor arena disponível.")
                        .addLore("")
                        .addLore("§eClique para jogar!")

                        .build()
        );

        // Escolher arena
        inventory.setItem(
                15,

                new ItemBuilder(Material.BED)

                        .name("§6Escolher Partida")

                        .addLore("§7Veja todas as arenas")
                        .addLore("§7e escolha onde jogar.")
                        .addLore("")
                        .addLore(player.hasPermission("bedwars.vip")
                                ? "§aClique para abrir."
                                : "§cExclusivo para VIP.")

                        .build()
        );

        player.openInventory(inventory);
    }

@EventHandler
public void onDrop(
       InventoryClickEvent e) {
	String title = e.getClickedInventory().getTitle();
    e.setCancelled(true);
if (title.equals("§8BedWars") || title.equals("§8Selecionar Arena")) {
	


Player player = (Player) e.getWhoClicked();

    switch (e.getCurrentItem().getType()) {

        case Material.NETHER_STAR:

            Arena arena = Bedwars.getInstance()
                    .getArenaManager()
                    .findBestArena();

            if (arena == null) {

                player.sendMessage("§cNenhuma arena disponível.");
                return;
            }

            ArenaJoinManager.join(player, arena);
            player.playSound(player.getLocation(), Sound.CAT_MEOW, 10, 10);
            player.closeInventory();
            break;

        case Material.BED:

            if (!player.hasPermission("bedwars.vip")) {

                player.sendMessage("§cApenas jogadores VIP podem escolher a arena.");
                return;
            }

            ArenaListMenu.open(player);
            break;
       case Material.EMPTY_MAP:
        e.setCancelled(true);

        if (e.getCurrentItem() == null)
            return;

        if (!e.getCurrentItem().hasItemMeta())
            return;
        String arenaName = e.getCurrentItem()
                .getItemMeta()
                .getDisplayName()
                .replace("§e", "");

        Arena arena2 = Bedwars.getInstance()
                .getArenaManager()
                .getArena(arenaName);

        if (arena2 == null)
            return;

        if (arena2.getPlayers().size() >= arena2.getMaxPlayers()) {

            player.sendMessage("§cEsta arena está cheia.");
            return;
        }

        if (arena2.getState() == ArenaState.PLAYING
                || arena2.getState() == ArenaState.ENDING) {

            player.sendMessage("§cEsta partida já começou.");
            return;
        }

        ArenaJoinManager.join(player, arena2);
        player.closeInventory();
	default:
		 player.closeInventory();
		break;
    

}
}
}
}