package com.rafaelauler.bedwars;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.scheduler.BukkitTask;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.BlockState;
import org.bukkit.entity.Player;

public class Arena {

    private final String name;
    private Location lobby;
	private boolean starting;
    private Location spectator;
    
    private int countdown = 30;
    private final List<TeamGenerator> teamGenerators =
            new ArrayList<>();
    private final List<BukkitTask> tasks =
            new ArrayList<>();
    private ArenaState state;
    private final Map<TeamColor, BWTeam> teams =
            new HashMap<>();
    private final List<Player> players;
    private final Set<UUID> spectators =
            new HashSet<>();
    private int minPlayers;
    private final List<Generator> generators =
            new ArrayList<>();
    private int maxPlayers;
    private final List<GamePlayer> gamePlayers =
            new ArrayList<>();
    private final Set<BlockState> brokenBlocks =
            new HashSet<>();

    private final Set<Location> placedBlocks =
            new HashSet<>();
    public Arena(String name) {

        this.name = name;
        this.state = ArenaState.WAITING;
        this.players = new ArrayList<>();

        this.minPlayers = 2;
        this.maxPlayers = 8;
    }
    public boolean createTeam(TeamColor color) {

        if (teams.containsKey(color))
            return false;

        teams.put(
                color,
                new BWTeam(color)
        );

        return true;
    }
    public List<GamePlayer> getGamePlayers() {
        return gamePlayers;
    }
    public void addPlayer(
            GamePlayer player) {

        gamePlayers.add(player);
    }
    public void setCountdown(
            int countdown) {

        this.countdown = countdown;
    }
    public List<BukkitTask> getTasks() {
        return tasks;
    }
    public void addTask(
            BukkitTask task) {

        tasks.add(task);
    }
    public void cancelTasks() {

        for(BukkitTask task : tasks) {

            if(task != null) {

                task.cancel();
            }
        }

        tasks.clear();
    }
    public int getCountdown() {
        return countdown;
    }
    public void removePlayer(
            GamePlayer player) {

        gamePlayers.remove(player);
    }
    public BWTeam getAvailableTeam() {

        BWTeam smallest = null;

        for (BWTeam team : teams.values()) {

            if (smallest == null) {
                smallest = team;
                continue;
            }

            if (team.getPlayers().size() <
                    smallest.getPlayers().size()) {

                smallest = team;
            }
        }

        return smallest;
    }
    public BWTeam getTeam(TeamColor color) {
        return teams.get(color);
    }
    public List<TeamGenerator> getTeamGenerators() {
        return teamGenerators;
    }
    
    public Set<UUID> getSpectators() {
        return spectators;
    }
    public void addPlayer(Player player) {

        if(players.contains(player))
            return;

        players.add(player);
    }
    public List<Generator> getGenerators() {
        return generators;
    }
    public World getWorld() {

        if(lobby != null)
            return lobby.getWorld();

        if(spectator != null)
            return spectator.getWorld();

        return null;
    }
    public Map<TeamColor, BWTeam> getTeams() {
        return teams;
    }
    public void removePlayer(Player player) {

        players.remove(player);
    }
    public Location getLobby() {
        return lobby;
    }

    public void setLobby(Location lobby) {
        this.lobby = lobby;
    }

    public Location getSpectator() {
        return spectator;
    }
    public Set<BlockState> getBrokenBlocks() {
        return brokenBlocks;
    }

    public Set<Location> getPlacedBlocks() {
        return placedBlocks;
    }
    public void setSpectator(Location spectator) {
        this.spectator = spectator;
    }
    public boolean isFull() {

        return players.size() >= maxPlayers;
    }

    public String getName() {
        return name;
    }
    public BWTeam getTeamByBed(
            Location location) {

        for(BWTeam team :
                teams.values()) {

            if(team.getBed() != null) {

                if(team.getBed()
                        .getBlock()
                        .equals(
                                location.getBlock()
                        )) {

                    return team;
                }
            }

            if(team.getBedHead() != null) {

                if(team.getBedHead()
                        .getBlock()
                        .equals(
                                location.getBlock()
                        )) {

                    return team;
                }
                if(team.getBedFoot() != null) {

                    if(team.getBedFoot()
                            .getBlock()
                            .equals(
                                    location.getBlock()
                            )) {

                        return team;
                    }
            }
        }
        }

        return null;
    }

    public ArenaState getState() {
        return state;
    }
    public boolean isStarting() {
        return starting;
    }
    public void setStarting(boolean starting) {
        this.starting = starting;
    }
    public void setState(ArenaState state) {
        this.state = state;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public int getPlayerCount() {
        return players.size();
    }

    public int getMinPlayers() {
        return minPlayers;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }
}
