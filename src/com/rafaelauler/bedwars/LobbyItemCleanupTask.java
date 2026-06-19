package com.rafaelauler.bedwars;

import org.bukkit.World;
import org.bukkit.entity.Item;
import org.bukkit.scheduler.BukkitRunnable;

public class LobbyItemCleanupTask
        extends BukkitRunnable {

    @Override
    public void run() {

       
World world = Bedwars.getInstance().getLobbySpawn().getWorld();
            for(Item item :
                    world.getEntitiesByClass(
                            Item.class
                    )) {

                item.remove();
            }
        }
    }
