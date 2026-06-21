package com.rafaelauler.bedwars;

import org.bukkit.Material;
import org.bukkit.entity.Player;

public class SetCosmeticGeneratorCommand
        implements SubCommand {

    @Override
    public String getName() {
        return "setcosmeticgenerator";
    }

    @Override
    public String getPermission() {
        return "bedwars.admin";
    }

    @Override
    public void execute(
            Player player,
            String[] args) {

        if(args.length < 2) {

            player.sendMessage(
                    "§cUse: /bw setcosmeticgenerator <DIAMOND|EMERALD|GOLD|IRON>"
            );

            return;
        }

        Material material;

        try {

            material =
                    Material.valueOf(
                            args[1]
                                    .toUpperCase()
                    );

        } catch(Exception e) {

            player.sendMessage(
                    "§cMaterial inválido."
            );

            return;
        }

        CosmeticGeneratorFile file =
                Bedwars.getInstance()
                        .getCosmeticGeneratorFile();

        int id =
                file.getConfig()
                        .getKeys(false)
                        .size();

        String path =
                "Generators."
                + id;

        file.setLocation(
                path + ".location",
                player.getLocation()
        );

        file.getConfig().set(
                path + ".material",
                material.name()
        );

        file.save();

        player.sendMessage(
                "§aGerador cosmético criado."
        );
    }
}