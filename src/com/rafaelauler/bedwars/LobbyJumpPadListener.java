package com.rafaelauler.bedwars;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

public class LobbyJumpPadListener
        implements Listener {
	private final Map<UUID, Long> cooldown =
	        new HashMap<>();
    @EventHandler
    public void onMove(
            PlayerMoveEvent e) {

        Player player =
                e.getPlayer();

        GamePlayer gp =
                Bedwars.getInstance()
                        .getPlayerManager()
                        .get(player);

        /*
         * Apenas lobby
         */
        if(gp != null
                && gp.getArena() != null)
            return;

        Material material =
                player.getLocation()
                        .subtract(0, 1, 0)
                        .getBlock()
                        .getType();

        if(material
                != Material.SLIME_BLOCK)
            return;
        long now =
                System.currentTimeMillis();

        if(cooldown.containsKey(
                player.getUniqueId())
                && now -
                cooldown.get(
                        player.getUniqueId())
                < 1000)
            return;

        cooldown.put(
                player.getUniqueId(),
                now
        );
        Vector velocity =
                player.getLocation()
                        .getDirection()
                        .normalize()
                        .multiply(2.5);

        velocity.setY(1.0);
        player.playSound(
                player.getLocation(),
                org.bukkit.Sound.valueOf("FIREWORK_LAUNCH"),
                1F,
                1F
        );
        player.setVelocity(
                velocity
        );
    }
}