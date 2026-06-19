package com.rafaelauler.bedwars;


import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class LobbyJoinListener
        implements Listener {
	@EventHandler
	public void onDamage(
	        EntityDamageEvent e) {

	    if(!(e.getEntity()
	            instanceof Player))
	        return;

	    Player player =
	            (Player)
	                    e.getEntity();

	    if(e.getCause()
	            != EntityDamageEvent.DamageCause.VOID)
	        return;

	    player.teleport(
	            Bedwars.getInstance()
	                    .getLobbyFile()
	                    .getLobby()
	    );
	}
    @EventHandler
    public void onJoin(
            PlayerJoinEvent e) {

        Player player =
                e.getPlayer();

        player.teleport(
                Bedwars.getInstance().getLobbySpawn()
        );

        player.getInventory()
                .clear();

        player.setGameMode(
                GameMode.ADVENTURE
        );

        LobbyItems.give(
                player
        );
    }
}
