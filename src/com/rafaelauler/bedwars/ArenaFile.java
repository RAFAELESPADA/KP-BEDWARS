package com.rafaelauler.bedwars;


import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;


public class ArenaFile {

    private final File file;
    private final FileConfiguration config;

    public ArenaFile() {

        file = new File(
                Bedwars.getInstance().getDataFolder(),
                "arenas.yml"
        );

        if(!file.exists()) {

            try {
                file.createNewFile();
            } catch(Exception e) {
                e.printStackTrace();
            }
        }

        config = YamlConfiguration.loadConfiguration(file);
    }

    public FileConfiguration getConfig() {
        return config;
    }
    public void setLocation(String path, Location location) {

        if(location == null) {
            config.set(path, null);
            return;
        }

        config.set(path + ".world", location.getWorld().getName());
        config.set(path + ".x", location.getX());
        config.set(path + ".y", location.getY());
        config.set(path + ".z", location.getZ());
        config.set(path + ".yaw", location.getYaw());
        config.set(path + ".pitch", location.getPitch());

        save();
    }
    public Location getLocation(String path) {

        if(!config.contains(path + ".world"))
            return null;

        World world = Bukkit.getWorld(
                config.getString(path + ".world")
        );

        if(world == null)
            return null;

        double x = config.getDouble(path + ".x");
        double y = config.getDouble(path + ".y");
        double z = config.getDouble(path + ".z");

        float yaw = (float) config.getDouble(path + ".yaw");
        float pitch = (float) config.getDouble(path + ".pitch");

        return new Location(
                world,
                x,
                y,
                z,
                yaw,
                pitch
        );
    }
    public void save() {

        try {
            config.save(file);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}