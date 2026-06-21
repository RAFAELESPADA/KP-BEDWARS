package com.rafaelauler.bedwars;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;

public class CosmeticGeneratorManager {

    public void load() {

        CosmeticGeneratorFile file =
                Bedwars.getInstance()
                        .getCosmeticGeneratorFile();

        ConfigurationSection section =
                file.getConfig()
                        .getConfigurationSection(
                                "Generators"
                        );

        if(section == null)
            return;

        for(String key :
                section.getKeys(false)) {

            CosmeticGenerator generator =
                    new CosmeticGenerator(
                            file.getLocation(
                                    "Generators."
                                    + key
                                    + ".location"
                            ),

                            Material.valueOf(
                                    file.getConfig()
                                            .getString(
                                                    "Generators."
                                                    + key
                                                    + ".material"
                                            )
                            )
                    );

            new CosmeticGeneratorTask(
                    generator.getStand()
            ).runTaskTimer(
                    Bedwars.getInstance(),
                    1L,
                    1L
            );

            new CosmeticParticleTask(
                    generator.getStand()
            ).runTaskTimer(
                    Bedwars.getInstance(),
                    1L,
                    2L
            );
        }
    }
}