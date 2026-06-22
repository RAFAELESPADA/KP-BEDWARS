package com.rafaelauler.bedwars;

package com.rafaelauler.bedwars;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.ArmorStand;
import org.bukkit.scheduler.BukkitRunnable;

public class GeneratorCleanUPTask
        extends BukkitRunnable {

    @Override
    public void run() {

        int removed = 0;

        for(World world :
                Bukkit.getWorlds()) {

            for(ArmorStand stand :
                    world.getEntitiesByClass(
                            ArmorStand.class
                    )) {

                if(!stand.hasMetadata(
                        "KPBEDWARS_COSMETIC"
                ))
                    continue;

                stand.remove();

                removed++;
            }
        }

        Bukkit.getLogger().info(
                "[KPBedWars] "
                + removed
                + " geradores cosméticos removidos."
        );
    }
}