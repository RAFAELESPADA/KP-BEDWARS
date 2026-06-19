package com.rafaelauler.bedwars;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class VoidListener
        implements Listener {

    @EventHandler
    public void onVoid(
            EntityDamageEvent e) {

        if(!(e.getEntity()
                instanceof Player))
            return;

        if(e.getCause()
                != EntityDamageEvent
                .DamageCause
                .VOID)
            return;

        e.setCancelled(true);

        Player player =
                (Player)
                        e.getEntity();

        GamePlayer gp =
                Bedwars.getInstance()
                        .getPlayerManager()
                        .get(player);

        if(gp == null)
            return;

        Arena arena =
                gp.getArena();

        if(arena == null)
            return;

        BWTeam team =
                gp.getTeam();

        if(team == null)
            return;

        player.setHealth(
                20
        );

        if(team.isBedAlive()) {
        	Bedwars.getInstance()
            .getKillManager()
            .handleDeath(
                    player,
                    gp
            );
        	PlayerStats stats2 =
                    Bedwars.getInstance()
                            .getStatsManager()
                            .getStats(
                                    player.getUniqueId()
                            );
        	stats2.setDeaths(stats2.getDeaths() + 1);
            respawn(
                    player
            );

            return;
        }

        eliminate(
                player,
                gp,
                team
        );
    }

    private void respawn(
            Player player) {

        Bukkit.getScheduler()
                .runTaskLater(
                        Bedwars.getInstance(),

                        () -> Bedwars
                                .getInstance()
                                .getRespawnManager()
                                .respawn(
                                        player
                                ),

                        1L
                );
    }

    private void eliminate(
            Player player,
            GamePlayer gp,
            BWTeam team) {

        gp.setAlive(false);

        team.getPlayers()
                .remove(
                        player.getUniqueId()
                );

        player.getInventory()
                .clear();

        player.setGameMode(
                GameMode.CREATIVE
        );

        Bedwars.getInstance()
                .getSpectatorManager()
                .addSpectator(gp.getArena(),
                        player
                       
                );

        Bukkit.broadcastMessage(
                "§c"
                + player.getName()
                + " foi eliminado!"
        );

        Bedwars.getInstance()
                .getGameEndManager()
                .checkWinner(
                        gp.getArena()
                );
    }
}