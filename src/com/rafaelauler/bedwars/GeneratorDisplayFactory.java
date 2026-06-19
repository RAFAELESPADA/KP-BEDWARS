package com.rafaelauler.bedwars;


import eu.decentsoftware.holograms.api.DHAPI;
import eu.decentsoftware.holograms.api.holograms.Hologram;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class GeneratorDisplayFactory {

    public static GeneratorDisplay create(
            Generator generator,
            String id) {

        Location location =
                generator.getLocation();

        ArmorStand stand =
                location.getWorld().spawn(
                        location.clone().add(0, 1.25, 0),
                        ArmorStand.class
                );

        stand.setVisible(false);
        stand.setGravity(false);
        stand.setSmall(true);

        Material helmet;

        switch(generator.getType()) {

            case DIAMOND:
                helmet = Material.DIAMOND;
                break;

            case EMERALD:
                helmet = Material.EMERALD;
                break;

            default:
                helmet = Material.BEDROCK;
        }

        stand.setHelmet(
                new ItemStack(helmet)
        );

        Hologram hologram =
                DHAPI.createHologram(
                        id,
                        location.clone()
                                .add(0, 3.0, 0)
                );

        DHAPI.setHologramLines(
                hologram,
                Arrays.asList(
                        generator.getType()
                                == GeneratorType.DIAMOND
                                ?
                                "§b♦ Gerador de Diamante"
                                :
                                "§a✦ Gerador de Esmeralda",
                        "§eNível I",
                        "§fGera items a cada §a0 §fsegundos"
                )
        );

        return new GeneratorDisplay(
                stand,
                hologram
        );
    }
}
