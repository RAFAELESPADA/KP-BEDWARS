package com.rafaelauler.bedwars;


import java.util.UUID;

public class PlayerStats {
	 public PlayerStats(
	            UUID uuid) {

	        this.uuid = uuid;
	    }
    private final UUID uuid;
    private int level = 1;

    private int coins;

    private int winstreak;
    private String name;

    private int kills;

    private int experience;

    private int finalKills;

    private int deaths;

    private int wins;

    private int losses;

    private int bedsBroken;

	public int getKills() {
		return kills;
	}

	public void setKills(int kills) {
		this.kills = kills;
	}
	public void setExperience(int exp) {
		this.experience = exp;
	}
	public int getBedsBroken() {
		return bedsBroken;
	}

	public void setBedsBroken(int bedsBroken) {
		this.bedsBroken = bedsBroken;
	}

	public int getLosses() {
		return losses;
	}
	public int getExperience() {
		return experience;
	}
	public UUID getUuid() {
		return uuid;
	}
	public double getKDR() {

	    if(deaths == 0)
	        return kills;

	    return (double) kills / deaths;
	}
	public int getLevel() {
	    return level;
	}

	public void setLevel(
	        int level) {

	    this.level = level;
	}

	public int getCoins() {
	    return coins;
	}
	private int highestCombo;

	public int getHighestCombo() {
	    return highestCombo;
	}

	public void setHighestCombo(int highestCombo) {
	    this.highestCombo = highestCombo;
	}
	public void setCoins(
	        int coins) {

	    this.coins = coins;
	}

	public int getWinstreak() {
	    return winstreak;
	}

	public void setWinstreak(
	        int winstreak) {

	    this.winstreak = winstreak;
	}
	public double getFKDR() {

	    if(deaths == 0)
	        return finalKills;

	    return (double) finalKills / deaths;
	}
	public double getWinRate() {

	    int total =
	            wins + losses;

	    if(total == 0)
	        return 0;

	    return (wins * 100.0)
	            / total;
	}
	public void setLosses(int losses) {
		this.losses = losses;
	}

	public int getWins() {
		return wins;
	}

	public void setWins(int wins) {
		this.wins = wins;
	}

	public int getDeaths() {
		return deaths;
	}

	public void setDeaths(int deaths) {
		this.deaths = deaths;
	}

	public int getFinalKills() {
		return finalKills;
	}

	public void setFinalKills(int finalKills) {
		this.finalKills = finalKills;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}

