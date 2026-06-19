package com.rafaelauler.bedwars;


import org.bukkit.Location;
public class Generator {

    private final GeneratorType type;

    private final GeneratorCategory category;
    private GeneratorDisplay display;
    private final Location location;

    private Arena arena;

    public Generator(
            Arena arena,
            GeneratorType type,
            GeneratorCategory category,
            Location location) {

        this.arena = arena;
        this.type = type;
        this.category = category;
        this.location = location;
    }
    public GeneratorDisplay getDisplay() {
        return display;
    }

    public void setDisplay(
            GeneratorDisplay display) {

        this.display = display;
    }
    public Arena getArena() {
        return arena;
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
