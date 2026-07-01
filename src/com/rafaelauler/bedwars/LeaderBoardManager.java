package com.rafaelauler.bedwars;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Location;


public class LeaderBoardManager {

	public final Map<String, Leaderboard> boards =
	        new HashMap<>();

	public void createKills(Location loc) {

	    boards.put(
	            "kills",
	            new Leaderboard(
	                    loc,
	                    "kills",
	                    "§6§lTOP KILLS",
	                    "kpbedwars_kills"
	            )
	    );
	}
	public void createDeaths(Location loc) {

	    boards.put(
	            "deaths",
	            new Leaderboard(
	                    loc,
	                    "deaths",
	                    "§6§lTOP DEATHS",
	                    "kpbedwars_deaths"
	            )
	    );
	}
	public void createXP(Location loc) {

	    boards.put(
	            "xp",
	            new Leaderboard(
	                    loc,
	                    "xp",
	                    "§6§lTOP MAIS XP",
	                    "kpbedwars_xp"
	            )
	    );
	    
	}
	public void createWins(Location loc) {

	    boards.put(
	            "deaths",
	            new Leaderboard(
	                    loc,
	                    "wins",
	                    "§6§lTOP WINS",
	                    "kpbedwars_wins"
	            )
	    );
	}
}