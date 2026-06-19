package com.rafaelauler.bedwars;


import org.bukkit.Location;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class BWTeam {

    private final TeamColor color;
    private int forgeLevel = 1;
    private Location spawn;
    private Location bed;

    private int protectionLevel = 0;

    private boolean sharpness;

    private boolean bedAlive = true;

    private final Set<UUID> players =
            new HashSet<>();

    public BWTeam(TeamColor color) {
        this.color = color;
    }

    public TeamColor getColor() {
        return color;
    }

    public Location getSpawn() {
        return spawn;
    }
    public int getForgeLevel() {
        return forgeLevel;
    }
    public boolean hasSharpness() {
        return sharpness;
    }

    public void setSharpness(
            boolean sharpness) {

        this.sharpness = sharpness;
    }

    public int getProtectionLevel() {
        return protectionLevel;
    }

    public void setProtectionLevel(
            int protectionLevel) {

        this.protectionLevel =
                protectionLevel;
    }
    public void setForgeLevel(int forgeLevel) {
        this.forgeLevel = forgeLevel;
    }
    public void setSpawn(Location spawn) {
        this.spawn = spawn;
    }

    public Location getBed() {
        return bed;
    }
  
   
    public void setBed(Location bed) {
        this.bed = bed;
    }

    public boolean isBedAlive() {
        return bedAlive;
    }
    public long getIronDelay() {

        switch(forgeLevel) {

            case 1:
                return 20L;

            case 2:
                return 15L;

            case 3:
                return 10L;

            case 4:
                return 5L;
        }

        return 20L;
    }
    public long getGoldDelay() {

        switch(forgeLevel) {

            case 1:
                return 50L;

            case 2:
                return 30L;

            case 3:
                return 15L;

            case 4:
                return 10L;
        }

        return 50L;
    }
    public void setBedAlive(boolean bedAlive) {
        this.bedAlive = bedAlive;
    }

    public Set<UUID> getPlayers() {
        return players;
    }
}
