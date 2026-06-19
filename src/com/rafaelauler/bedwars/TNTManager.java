package com.rafaelauler.bedwars;


import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TNTManager {

    private final Map<UUID, BWTeam> tnts =
            new HashMap<>();

    public void register(
            UUID uuid,
            BWTeam team) {

        tnts.put(
                uuid,
                team
        );
    }

    public BWTeam getTeam(
            UUID uuid) {

        return tnts.get(uuid);
    }

    public void remove(
            UUID uuid) {

        tnts.remove(uuid);
    }
}
