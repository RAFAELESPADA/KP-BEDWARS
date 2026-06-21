package com.rafaelauler.bedwars;


import java.io.File;
import java.io.IOException;

import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class CosmeticGeneratorFile {

    private final File file;

    private final FileConfiguration config;

    public CosmeticGeneratorFile() {

        file =
                new File(
                        Bedwars.getInstance()
                                .getDataFolder(),
                        "cosmetic-generators.yml"
                );

        try {

            if(!file.exists()) {

                file.createNewFile();
            }

        } catch(IOException e) {

            e.printStackTrace();
        }

        config =
                YamlConfiguration
                        .loadConfiguration(
                                file
                        );
    }

    public FileConfiguration getConfig() {
        return config;
    }

    public void save() {

        try {

            config.save(file);

        } catch(IOException e) {

            e.printStackTrace();
        }
    }

    public void setLocation(
            String path,
            Location location) {

        config.set(path, location);
    }

    public Location getLocation(
            String path) {

        return (Location)
                config.get(path);
    }
}
