package com.rafaelauler.bedwars;
import org.bukkit.Location;

public class TeamGenerator {

    private final BWTeam team;
    private Location location;

    public TeamGenerator(
            BWTeam team,
            Location location) {

        this.team = team;
        this.location = location;
    }

    public BWTeam getTeam() {
        return team;
    }

    public Location getLocation() {
        return location;
    }

	public void setLocation(Location location2) {
		this.location = location2;
		
	}
}