package com.rafaelauler.bedwars;


import org.bukkit.Location;
public class Generator {

    private final GeneratorType type;

    private final GeneratorCategory category;
    private GeneratorDisplay display;
    private final Location location;
    private BWTeam team;

    private Arena arena;

    public Generator(
            Arena arena,
            GeneratorType type,
            GeneratorCategory category,
            Location location,
            BWTeam team) {

        this.arena = arena;
        this.type = type;
        this.category = category;
        this.location = location;
        this.team = team;
    }
    public Generator(
            Arena arena,
            GeneratorType type,
            GeneratorCategory category,
            Location location) {

        this(
                arena,
                type,
                category,
                location,
                null
        );
    }
    public GeneratorDisplay getDisplay() {
        return display;
    }

    public void setDisplay(
            GeneratorDisplay display) {

        this.display = display;
    }
    public boolean isTeamGenerator() {

        return type == GeneratorType.IRON
                || type == GeneratorType.GOLD;
    }
    public Arena getArena() {
        return arena;
    }
    public BWTeam getTeam() {
        return team;
    }
    public void setTeam(
            BWTeam team) {

        this.team = team;
    }
    public GeneratorType getType() {
        return type;
    }

    public GeneratorCategory getCategory() {
        return category;
    }

    public Location getLocation() {
        return location;
    }
}
