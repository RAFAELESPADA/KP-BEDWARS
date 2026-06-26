package com.rafaelauler.bedwars;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.scheduler.BukkitRunnable;

import com.onarandombox.MultiverseCore.MultiverseCore;

public class WorldReset {

    private final Bedwars plugin;

    public WorldReset(Bedwars plugin) {
        this.plugin = plugin;
    }

    public void reset(final Arena arena) {

        if (arena.getWorld() == null)
            return;

        final String worldName = arena.getWorld().getName();
        final World world = arena.getWorld();

        // Remove todos os jogadores do mundo
        world.getPlayers().forEach(player ->
                player.teleport(Bedwars.getInstance().getLobbySpawn())
        );

        // Descarrega o mundo
        Bukkit.unloadWorld(world, false);

        // Espera o unload finalizar
        new BukkitRunnable() {

            @Override
            public void run() {

                try {

                    MultiverseCore mv =
                            (MultiverseCore) Bukkit.getPluginManager()
                                    .getPlugin("Multiverse-Core");

                    // Remove do Multiverse
                    if (mv.getMVWorldManager().isMVWorld(worldName)) {
                        mv.getMVWorldManager().deleteWorld(
                                worldName,
                                true,
                                false
                        );
                    }

                    File source = new File(
                            plugin.getDataFolder(),
                            "maps/" + arena.getTemplate()
                    );

                    File target = new File(
                            Bukkit.getWorldContainer(),
                            worldName
                    );

                    // Remove a pasta antiga
                    if (target.exists()) {
                        FileUtils.deleteDirectory(target);
                    }

                    // Copia o mapa limpo
                    FileUtils.copyDirectory(source, target);

                    // Remove arquivos que causam conflito
                    File session = new File(target, "session.lock");
                    if (session.exists())
                        session.delete();

                    File uid = new File(target, "uid.dat");
                    if (uid.exists())
                        uid.delete();

                    // Carrega novamente
                    mv.getMVWorldManager().addWorld(
                            worldName,
                            World.Environment.NORMAL,
                            null,
                            org.bukkit.WorldType.NORMAL,
                            false,
                            "null"
                    );

                    World newWorld = Bukkit.getWorld(worldName);

                    if (newWorld == null) {
                        plugin.getLogger().severe("Não foi possível carregar o mundo " + worldName);
                        return;
                    }

                    arena.setWorld(newWorld);
                    arena.updateWorld(newWorld);

                    for (BWTeam team : arena.getTeams().values()) {
                        team.updateWorld(newWorld);
                    }

                    plugin.getLogger().info(worldName + " resetado com sucesso.");
                    Bukkit.getScheduler().runTaskLater(
                            Bedwars.getInstance(),
                            () -> Bukkit.dispatchCommand(
                                    Bukkit.getConsoleSender(),
                                    "citizens reload"
                            ), 120l
                    );
                    Bukkit.getScheduler().runTaskLater(
                            Bedwars.getInstance(),
                            () -> Bukkit.dispatchCommand(
                                    Bukkit.getConsoleSender(),
                                    "citizens reload"
                            ), 120l
                    );
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

        }.runTaskLater(plugin, 20L);
    }

}