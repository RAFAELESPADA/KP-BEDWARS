package com.rafaelauler.bedwars;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerManager {

    private final Map<UUID, GamePlayer> players =
            new HashMap<>();

    public GamePlayer get(Player player) {

        return players.computeIfAbsent(
                player.getUniqueId(),
                uuid -> new GamePlayer(uuid)
        );
    }

    public void remove(Player player) {

        players.remove(
                player.getUniqueId()
        );
    }
}
