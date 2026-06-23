package com.rafaelauler.bedwars;


import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockFormEvent;
import org.bukkit.event.block.BlockIgniteEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.LeavesDecayEvent;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class LobbyProtectionListener
        implements Listener {

    /*
     * Anti Hunger
     */
	@EventHandler
	public void onDamage(
	        EntityDamageByEntityEvent e) {

	    if(!(e.getEntity()
	            instanceof Player))
	        return;

	    Player player =
	            (Player) e.getEntity();

	    GamePlayer gp =
	            Bedwars.getInstance()
	                    .getPlayerManager()
	                    .get(player);

	    if(gp == null
	            || gp.getArena() == null) {

	        e.setCancelled(true);
	        return;
	    }

	    ArenaState state =
	            gp.getArena()
	                    .getState();

	    if(state == ArenaState.WAITING
	            || state == ArenaState.STARTING) {

	        e.setCancelled(true);
	    }
	}
	@EventHandler
	public void onFood2(
	        FoodLevelChangeEvent e) {

	    if(!(e.getEntity()
	            instanceof Player))
	        return;
	        e.setCancelled(true);
	    }
	
	@EventHandler
	public void onBREAK(
	        BlockBreakEvent e) {

	    Player player =
	            e.getPlayer();

	    GamePlayer gp =
	            Bedwars.getInstance()
	                    .getPlayerManager()
	                    .get(player);

	    if(gp == null
	            || gp.getArena() == null) {

	        e.setCancelled(true);
	        return;
	    }

	    ArenaState state =
	            gp.getArena()
	                    .getState();

	    if(state == ArenaState.WAITING
	            || state == ArenaState.STARTING) {

	        e.setCancelled(true);
	    }
	}
	@EventHandler
	public void onPlace2(
	        BlockPlaceEvent e) {

	    Player player =
	            e.getPlayer();

	    GamePlayer gp =
	            Bedwars.getInstance()
	                    .getPlayerManager()
	                    .get(player);

	    if(gp == null
	            || gp.getArena() == null) {

	        e.setCancelled(true);
	        return;
	    }

	    ArenaState state =
	            gp.getArena()
	                    .getState();

	    if(state == ArenaState.WAITING
	            || state == ArenaState.STARTING) {

	        e.setCancelled(true);
	    }
	}
    @EventHandler
    public void onFood(
            FoodLevelChangeEvent e) {

        if(!(e.getEntity()
                instanceof Player))
            return;

        Player player =
                (Player)
                        e.getEntity();

        if(!isLobby(player))
            return;

        e.setCancelled(true);

        player.setFoodLevel(20);
    }
    public void onForm(
            BlockFormEvent e) {

        e.setCancelled(true);
    }
    /*
     * Anti Damage
     */
    @EventHandler
    public void onDamage(
            EntityDamageEvent e) {

        if(!(e.getEntity()
                instanceof Player))
            return;

        Player player =
                (Player)
                        e.getEntity();

        if(!isLobby(player))
            return;

        e.setCancelled(true);
    }

    /*
     * Anti Build
     */
    @EventHandler
    public void onPlace(
            BlockPlaceEvent e) {

        if(!isLobby(
                e.getPlayer()
        ))
            return;
        if (e.getPlayer().getGameMode() == GameMode.CREATIVE) {
        	return;
        }
        e.setCancelled(true);
    }

    /*
     * Anti Break
     */
    @EventHandler
    public void onBreak(
            BlockBreakEvent e) {

        if(!isLobby(
                e.getPlayer()
        ))
            return;
        if (e.getPlayer().getGameMode() == GameMode.CREATIVE) {
        	return;
        }
        e.setCancelled(true);
    }

    /*
     * Anti Mob Spawn
     */
    @EventHandler
    public void onSpawn(
            CreatureSpawnEvent e) {

        e.setCancelled(true);
    }

    /*
     * Anti Weather
     */
    @EventHandler
    public void onWeather(
            WeatherChangeEvent e) {

        if(e.toWeatherState()) {

            e.setCancelled(true);
        }
    }
    @EventHandler
    public void onIgnite(
            BlockIgniteEvent e) {
    	if (Bedwars.getInstance().getLobbySpawn().getWorld() != null) {
if (e.getBlock().getWorld().equals(Bedwars.getInstance().getLobbySpawn().getWorld())) {
        e.setCancelled(true);
    }
    	}
    }
    @EventHandler
    public void onDecay(
            LeavesDecayEvent e) {

        e.setCancelled(true);
    }
    private boolean isLobby(
            Player player) {

        GamePlayer gp =
                Bedwars.getInstance()
                        .getPlayerManager()
                        .get(player);

        return gp == null
                || gp.getArena() == null;
    }
}