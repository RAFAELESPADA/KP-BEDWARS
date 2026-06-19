package com.rafaelauler.bedwars;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class LobbyFile {

    private final File file;

    private final FileConfiguration config;

    public LobbyFile() {

        file = new File(
                Bedwars.getInstance()
                        .getDataFolder(),
                "lobby.yml"
        );

        if(!file.exists()) {

            try {

                file.createNewFile();

            } catch(IOException e) {

                e.printStackTrace();
            }
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

    public Location getLobby() {

        if(!config.contains("Lobby.World"))
            return null;

        return new Location(
                Bukkit.getWorld(
                        config.getString(
                                "Lobby.World"
                        )
                ),

                config.getDouble(
                        "Lobby.X"
                ),

                config.getDouble(
                        "Lobby.Y"
                ),

                config.getDouble(
                        "Lobby.Z"
                ),

                (float) config.getDouble(
                        "Lobby.Yaw"
                ),

                (float) config.getDouble(
                        "Lobby.Pitch"
                )
        );
    }

    public void setLobby(
            Location location) {

        config.set(
                "Lobby.World",
                location.getWorld().getName()
        );

        config.set(
                "Lobby.X",
                location.getX()
        );

        config.set(
                "Lobby.Y",
                location.getY()
        );

        config.set(
                "Lobby.Z",
                location.getZ()
        );

        config.set(
                "Lobby.Yaw",
                location.getYaw()
        );

        config.set(
                "Lobby.Pitch",
                location.getPitch()
        );

        save();
    }
}