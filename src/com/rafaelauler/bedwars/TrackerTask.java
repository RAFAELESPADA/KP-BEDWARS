package com.rafaelauler.bedwars;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class TrackerTask extends BukkitRunnable {

    private final Player player;

    public TrackerTask(Player player) {
        this.player = player;
    }

    @Override
    public void run() {

        if (!player.isOnline()) {
            cancel();
            return;
        }

        GamePlayer gp = Bedwars.getInstance()
                .getPlayerManager()
                .get(player);

        if (gp == null || gp.getArena() == null) {
            cancel();
            return;
        }

        if (gp.isSpectator()) {
            cancel();
            return;
        }

        if (player.getItemInHand() == null
                || player.getItemInHand().getType() != Material.COMPASS)
            return;

        Player target = getNearestEnemy(gp);

        if (target == null) {

            player.setCompassTarget(player.getLocation());

            TitleAPI.sendActionBar(
                    player,
                    "§cNenhum inimigo encontrado."
            );

            return;
        }

        player.setCompassTarget(
                target.getLocation()
        );

        double distance =
                player.getLocation()
                        .distance(target.getLocation());

        TitleAPI.sendActionBar(
                player,
                "§eRastreando §6"
                        + target.getName()
                        + " §7("
                        + (int) distance
                        + "m)"
        );
    }

    private Player getNearestEnemy(GamePlayer gp) {

        Player nearest = null;

        double distance = Double.MAX_VALUE;

        for (Player target : gp.getArena().getPlayers()) {

            if (target.equals(player))
                continue;

            GamePlayer enemy =
                    Bedwars.getInstance()
                            .getPlayerManager()
                            .get(target);

            if (enemy == null)
                continue;

            if (!enemy.isAlive())
                continue;

            if (enemy.isSpectator())
                continue;

            if (enemy.getTeam() == gp.getTeam())
                continue;

            double current =
                    player.getLocation()
                            .distanceSquared(
                                    target.getLocation()
                            );

            if (current < distance) {

                distance = current;
                nearest = target;
            }
        }

        return nearest;
    }
}