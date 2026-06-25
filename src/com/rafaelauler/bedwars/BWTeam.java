package com.rafaelauler.bedwars;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.World;
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
    private Location bedHead;
	private Location bedFoot;
    private TeamGenerator generator;
    private byte bedHeadData;
    private byte bedFootData;
  


    public void updateWorld(World world) {

        if (spawn != null) {
            spawn = new Location(
                    world,
                    spawn.getX(),
                    spawn.getY(),
                    spawn.getZ(),
                    spawn.getYaw(),
                    spawn.getPitch()
            );
        }

        if (bed != null) {
            bed = new Location(
                    world,
                    bed.getX(),
                    bed.getY(),
                    bed.getZ(),
                    bed.getYaw(),
                    bed.getPitch()
            );
        }

        if (bedHead != null) {
            bedHead = new Location(
                    world,
                    bedHead.getX(),
                    bedHead.getY(),
                    bedHead.getZ()
            );
        }

        if (bedFoot != null) {
            bedFoot = new Location(
                    world,
                    bedFoot.getX(),
                    bedFoot.getY(),
                    bedFoot.getZ()
            );
        }

        if (generator != null && generator.getLocation() != null) {

            generator.setLocation(new Location(
                    world,
                    generator.getLocation().getX(),
                    generator.getLocation().getY(),
                    generator.getLocation().getZ(),
                    generator.getLocation().getYaw(),
                    generator.getLocation().getPitch()
            ));
        }
    }
    
    public Location getBedHead() {
        return bedHead;
    }

    public void setBedHead(Location bedHead) {
        this.bedHead = bedHead;
    }



    public void setBedFoot(Location bedFoot) {
        this.bedFoot = bedFoot;
    }
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
    public boolean isActive() {

        return !getPlayers().isEmpty();
    }
    public void setProtectionLevel(
            int protectionLevel) {

        this.protectionLevel =
                protectionLevel;
    }
    public boolean hasAlivePlayers(Arena arena) {

        for (GamePlayer gp : arena.getGamePlayers()) {

            if (gp.getTeam() == this && gp.isAlive()) {
                return true;
            }
        }

        return false;
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
    public TeamGenerator getGenerator() {
        return generator;
    }

    public void setGenerator(TeamGenerator generator) {
        this.generator = generator;
    }
    public void setBedAlive(boolean bedAlive) {
        this.bedAlive = bedAlive;
    }

    public Set<UUID> getPlayers() {
        return players;
    }

	public void setBedHeadData(byte int1) {
		bedHeadData = int1;
		
	}
	 public byte getBedHeadData() {
	        return bedHeadData;
	    }
	 public byte getBedFootData() {
	        return bedFootData;
	    }
	 public Location getBedFoot() {
	        return bedFoot;
	    }
	public void setBedFootData(byte int1) {
		bedFootData = int1;
		
	
}
}
