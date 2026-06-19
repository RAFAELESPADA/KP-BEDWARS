package com.rafaelauler.bedwars;


import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

public class SpectatorUtils {

	public static void apply(Player player) {

	    player.getInventory().clear();

	    player.getInventory().setItem(
	            0,
	            SpectatorItems.getTeleportItem()
	    );

	    player.getInventory().setItem(
	            8,
	            SpectatorItems.getLeaveItem()
	    );

	    player.updateInventory();

	    player.setAllowFlight(true);
	    player.setFlying(true);
	}

    public static void reset(Player player) {

        player.setAllowFlight(false);
        player.setFlying(false);

        player.setGameMode(
                GameMode.SURVIVAL
        );

        player.removePotionEffect(
                PotionEffectType.INVISIBILITY
        );
        for(Player online :
            Bukkit.getOnlinePlayers()) {

        online.showPlayer(player);
    }
    }
}
