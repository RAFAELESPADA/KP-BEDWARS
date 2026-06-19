package com.rafaelauler.bedwars;


import org.bukkit.entity.Player;

public class SetGeneratorCommand
        implements SubCommand {

    @Override
    public String getName() {
        return "setgenerator";
    }

    @Override
    public String getPermission() {
        return "bedwars.admin";
    }

    @Override
    public void execute(
            Player player,
            String[] args) {

        if(args.length < 3)
            return;

        Arena arena =
                Bedwars.getInstance()
                        .getArenaManager()
                        .getArena(args[1]);

        if(arena == null)
            return;

        GeneratorType type =
                GeneratorType.valueOf(
                        args[2]
                                .toUpperCase()
                );


        GeneratorCategory category =
                type == GeneratorType.DIAMOND
                        ? GeneratorCategory.DIAMOND
                        : GeneratorCategory.EMERALD;

        Generator generator =
                new Generator(
                        arena,
                        type,
                        category,
                        player.getLocation()
                );
        arena.getGenerators()
                .add(generator);

        player.sendMessage(
                "§aGerador criado."
        );
    }
}
