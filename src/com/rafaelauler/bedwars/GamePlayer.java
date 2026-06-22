package com.rafaelauler.bedwars;


import java.util.UUID;

import org.bukkit.entity.Player;

public class GamePlayer {

    private final UUID uuid;
    private boolean spectator;
    private Arena arena;
    private BWTeam team;
    private SwordTier swordTier =
            SwordTier.WOOD;
    private ArmorTier armorTier =
            ArmorTier.LEATHER;
    private ToolTier pickaxeTier = null;
    private ToolTier axeTier = null;
    private int kills;
    private Player lastDamager;
    private boolean countedLoss;
    private boolean winner;

    public boolean hasCountedLoss() {
        return countedLoss;
    }

    public void setCountedLoss(
            boolean countedLoss) {

        this.countedLoss = countedLoss;
    }
    public boolean isWinner() {
        return winner;
    }

    public void setWinner(
            boolean winner) {

        this.winner = winner;
    }
    private long lastDamageTime;
    private int finalKills;

    private boolean alive = true;
    public GamePlayer(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getUuid() {
        return uuid;
    }

    public Arena getArena() {
        return arena;
    }
    public ArmorTier getArmorTier() {
        return armorTier;
    }
    public void setArmorTier(
            ArmorTier armorTier) {

        this.armorTier = armorTier;
    }
    public boolean isSpectator() {
        return spectator;
    }
    public ToolTier getPickaxeTier() {
        return pickaxeTier;
    }
    public void setKills(
            int kills) {

        this.kills = kills;
    }
    public Player getLastDamager() {
        return lastDamager;
    }

    public void setLastDamager(
            Player lastDamager) {

        this.lastDamager = lastDamager;
    }

    public long getLastDamageTime() {
        return lastDamageTime;
    }

    public void setLastDamageTime(
            long lastDamageTime) {

        this.lastDamageTime = lastDamageTime;
    }
    public void setFinalKills(
            int finalKills) {

        this.finalKills = finalKills;
    }
    public int getKills() {
        return kills;
    }

    public int getFinalKills() {
        return finalKills;
    }

    public boolean isAlive() {
        return alive;
    }
    public void setAlive(
            boolean alive) {

        this.alive = alive;
    }
    public void setPickaxeTier(
            ToolTier pickaxeTier) {

        this.pickaxeTier = pickaxeTier;
    }

    public ToolTier getAxeTier() {
        return axeTier;
    }

    public void setAxeTier(
            ToolTier axeTier) {

        this.axeTier = axeTier;
    }
    public SwordTier getSwordTier() {
        return swordTier;
    }
    public void setSwordTier(
            SwordTier swordTier) {

        this.swordTier = swordTier;
    }
    public void setSpectator(boolean spectator) {
        this.spectator = spectator;
    }
    public void setArena(Arena arena) {
        this.arena = arena;
    }

    public BWTeam getTeam() {
        return team;
    }

    public void setTeam(BWTeam team) {
        this.team = team;
    }
}