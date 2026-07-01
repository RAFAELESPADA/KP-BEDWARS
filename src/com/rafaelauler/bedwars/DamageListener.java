package com.rafaelauler.bedwars;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class DamageListener
        implements Listener {
	
    @EventHandler
    public void onDamage(
            EntityDamageByEntityEvent e) {

        if(!(e.getEntity()
                instanceof Player))
            return;

        Player victim =
                (Player) e.getEntity();

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

        GamePlayer gp =
                Bedwars.getInstance()
                        .getPlayerManager()
                        .get(victim);

        if(gp == null)
            return;

        gp.setLastDamager(
                attacker
        );

        gp.setLastDamageTime(
                System.currentTimeMillis()
        );
    }
}
