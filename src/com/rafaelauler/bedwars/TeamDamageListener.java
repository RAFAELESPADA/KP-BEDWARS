package com.rafaelauler.bedwars;


import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class TeamDamageListener
        implements Listener {

    @EventHandler
    public void onDamage(
            EntityDamageByEntityEvent e) {

        Entity victim =
                e.getEntity();

        Player attacker = null;

        if(e.getDamager()
                instanceof Player) {

            attacker =
                    (Player)
                            e.getDamager();
        }
        if(e.getDamager()
                instanceof Arrow) {

            Arrow arrow =
                    (Arrow)
                            e.getDamager();

            if(arrow.getShooter()
                    instanceof Player) {

                attacker =
                        (Player)
                                arrow.getShooter();
            }
        }
        if(attacker == null)
            return;

        if(!(victim instanceof Player))
            return;


        Player playerVictim =
                (Player) victim;

 

        GamePlayer gpVictim =
                Bedwars.getInstance()
                        .getPlayerManager()
                        .get(playerVictim);

        GamePlayer gpDamager =
                Bedwars.getInstance()
                        .getPlayerManager()
                        .get(attacker);

        if(gpVictim == null
                || gpDamager == null)
            return;

        if(gpVictim.getTeam() == null
                || gpDamager.getTeam() == null)
            return;

        if(gpVictim.getTeam()
                .equals(
                        gpDamager.getTeam()
                )) {

            e.setCancelled(
                    true
            );
        }
    }
}
