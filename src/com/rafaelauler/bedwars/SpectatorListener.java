package com.rafaelauler.bedwars;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class SpectatorListener implements Listener {

    private boolean isSpectator(Player player) {

        GamePlayer gp =
                Bedwars.getInstance()
                        .getPlayerManager()
                        .get(player);

        if (gp.getArena() == null)
            return false;

        return gp.getArena()
                .getSpectators()
                .contains(player.getUniqueId());
    }

    @EventHandler
    public void onBreak(BlockBreakEvent e) {

        if (!isSpectator(e.getPlayer()))
            return;

        e.setCancelled(true);
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent e) {

        if (!isSpectator(e.getPlayer()))
            return;

        e.setCancelled(true);
    }

    @EventHandler
    public void onDamage(EntityDamageEvent e) {

        if (!(e.getEntity() instanceof Player))
            return;

        Player player = (Player) e.getEntity();

        if (!isSpectator(player))
            return;

        e.setCancelled(true);
    }

    @EventHandler
    public void onFood(FoodLevelChangeEvent e) {

        if (!(e.getEntity() instanceof Player))
            return;

        Player player = (Player) e.getEntity();

        if (!isSpectator(player))
            return;

        e.setCancelled(true);
    }

    @EventHandler
    public void onPickup(PlayerPickupItemEvent e) {

        if (!isSpectator(e.getPlayer()))
            return;

        e.setCancelled(true);
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent e) {

        if (!isSpectator(e.getPlayer()))
            return;

        e.setCancelled(true);
    }
    @EventHandler
    public void onTarget(EntityTargetEvent e) {
    	if (!(e.getTarget() instanceof Player))
    	    return;

    	Player player = (Player) e.getTarget();

    	if (!isSpectator(player))
    	    return;

    	e.setCancelled(true);
    }
    @EventHandler
    public void onCommand(
            PlayerCommandPreprocessEvent e) {

        if(!isSpectator(
                        e.getPlayer()
                ))
            return;

        if(e.getMessage()
                .startsWith("/bw"))
            return;

        e.setCancelled(true);

        e.getPlayer()
                .sendMessage(
                        "§cEspectadores não podem usar comandos."
                );
    }
    @EventHandler
    public void onInteract(
            PlayerInteractEvent e) {

        Player player =
                e.getPlayer();

        if(!isSpectator(player))
            return;

        if(e.getItem() == null)
            return;

        Material type =
                e.getItem().getType();

        GamePlayer gp =
                Bedwars.getInstance()
                        .getPlayerManager()
                        .get(player);

        if(type == Material.COMPASS) {

            SpectatorMenu.open(
                    player,
                    gp.getArena()
            );
        }

        if(type == Material.BED) {

            player.performCommand(
                    "bw leave"
            );
        }
    }
    @EventHandler
    public void onInventoryClick(
            InventoryClickEvent e) {

        if(!(e.getWhoClicked()
                instanceof Player))
            return;

        Player player =
                (Player) e.getWhoClicked();

        if(!isSpectator(player))
            return;

        if(!e.getView().getTitle()
                .equals("Jogadores"))
            return;

        e.setCancelled(true);

        if(e.getCurrentItem() == null)
            return;

        if(e.getCurrentItem()
                .getType()
                != Material.SKULL_ITEM)
            return;

        String name =
                e.getCurrentItem()
                        .getItemMeta()
                        .getDisplayName()
                        .replace("§a", "");

        Player target =
                Bukkit.getPlayerExact(
                        name
                );

        if(target == null)
            return;

        player.teleport(
                target.getLocation()
        );

        player.closeInventory();

        player.sendMessage(
                "§aTeleportado para "
                + target.getName()
        );
    }
}