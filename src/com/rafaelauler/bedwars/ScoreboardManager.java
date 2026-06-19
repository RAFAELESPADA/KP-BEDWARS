package com.rafaelauler.bedwars;


import org.bukkit.entity.Player;

public class ScoreboardManager {

    public void update(
            Player player) {

        new ScoreboardAdapter(
                player
        ).send();
    }
}
