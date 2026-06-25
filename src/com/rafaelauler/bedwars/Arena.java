package com.rafaelauler.bedwars;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

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
    private World world;
    private String template;
    private final Set<Location> placedBlocks =
            new HashSet<>();
    public Arena(String name) {
    
    	    this.template = name;
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
    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
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
    public void updateWorld(World world) {

        this.world = world;

        if (lobby != null) {
            lobby = new Location(
                    world,
                    lobby.getX(),
                    lobby.getY(),
                    lobby.getZ(),
                    lobby.getYaw(),
                    lobby.getPitch()
            );
        }

        if (spectator != null) {
            spectator = new Location(
                    world,
                    spectator.getX(),
                    spectator.getY(),
                    spectator.getZ(),
                    spectator.getYaw(),
                    spectator.getPitch()
            );
        }

        for (BWTeam team : teams.values()) {

            if (team.getSpawn() != null) {
                team.setSpawn(new Location(
                        world,
                        team.getSpawn().getX(),
                        team.getSpawn().getY(),
                        team.getSpawn().getZ(),
                        team.getSpawn().getYaw(),
                        team.getSpawn().getPitch()
                ));
            }

            if (team.getBedHead() != null) {
                team.setBedHead(new Location(
                        world,
                        team.getBedHead().getX(),
                        team.getBedHead().getY(),
                        team.getBedHead().getZ()
                ));
            }

            if (team.getBedFoot() != null) {
                team.setBedFoot(new Location(
                        world,
                        team.getBedFoot().getX(),
                        team.getBedFoot().getY(),
                        team.getBedFoot().getZ()
                ));
            }

            if (team.getGenerator() != null &&
            	    team.getGenerator().getLocation() != null) {
            	
                team.getGenerator().setLocation(new Location(
                        world,
                        team.getGenerator().getLocation().getX(),
                        team.getGenerator().getLocation().getY(),
                        team.getGenerator().getLocation().getZ()
                ));
            }
        }

        for (Generator generator : generators) {
        	 if (generator == null || generator.getLocation() == null)
        	        continue;
            generator.setLocation(new Location(
                    world,
                    generator.getLocation().getX(),
                    generator.getLocation().getY(),
                    generator.getLocation().getZ()
            ));
        }

        for (TeamGenerator generator : teamGenerators) {
        	
        	 if (generator == null || generator.getLocation() == null )
        	        continue;
        	 
            generator.setLocation(new Location(
                    world,
                    generator.getLocation().getX(),
                    generator.getLocation().getY(),
                    generator.getLocation().getZ()
            ));
        }
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
