package com.rafaelauler.bedwars;


import org.bukkit.entity.Player;

public class SpectatorManager {

    public void addSpectator(
            Arena arena,
            Player player) {

        arena.getSpectators()
                .add(
                        player.getUniqueId()
                );

        SpectatorUtils.apply(player);
    }

    public void removeSpectator(
            Arena arena,
            Player player) {

        arena.getSpectators()
                .remove(
                        player.getUniqueId()
                );

        SpectatorUtils.reset(player);
    }
}
