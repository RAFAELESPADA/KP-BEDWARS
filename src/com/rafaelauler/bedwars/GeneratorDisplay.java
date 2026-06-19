package com.rafaelauler.bedwars;

import org.bukkit.entity.ArmorStand;

import eu.decentsoftware.holograms.api.holograms.Hologram;

public class GeneratorDisplay {

    private final ArmorStand stand;
    private final Hologram hologram;

    public GeneratorDisplay(
            ArmorStand stand,
            Hologram hologram) {

        this.stand = stand;
        this.hologram = hologram;
    }

    public ArmorStand getStand() {
        return stand;
    }

    public Hologram getHologram() {
        return hologram;
    }
}