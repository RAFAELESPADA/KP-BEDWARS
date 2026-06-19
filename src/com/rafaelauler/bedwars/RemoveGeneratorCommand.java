package com.rafaelauler.bedwars;


import org.bukkit.entity.Player;

public class RemoveGeneratorCommand
        implements SubCommand {

    @Override
    public String getName() {
        return "removegenerator";
    }

    @Override
    public String getPermission() {
        return "bedwars.admin";
    }

    @Override
    public void execute(
            Player player,
            String[] args) {

        if(args.length < 2)
            return;

        Arena arena =
                Bedwars.getInstance()
                        .getArenaManager()
                        .getArena(args[1]);

        if(arena == null)
            return;

        Generator generator =
                Bedwars.getInstance()
                        .getGeneratorManager()
                        .getClosestGenerator(
                                arena,
                                player.getLocation()
                        );

        if(generator == null) {

            player.sendMessage(
                    "§cGerador não encontrado."
            );

            return;
        }

        arena.getGenerators()
                .remove(generator);

        player.sendMessage(
                "§aGerador removido."
        );
    }
}