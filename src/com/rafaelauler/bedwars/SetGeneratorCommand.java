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

    @SuppressWarnings("unlikely-arg-type")
	@Override
    public void execute(
            Player player,
            String[] args) {

        if(args.length < 3) {

            player.sendMessage(
                    "§e/bw setgenerator <arena> <IRON|GOLD|DIAMOND|EMERALD> [TIME]"
            );

            return;
        }

        Arena arena =
                Bedwars.getInstance()
                        .getArenaManager()
                        .getArena(args[1]);

        if(arena == null) {

            player.sendMessage(
                    "§cArena não encontrada."
            );

            return;
        }

        GeneratorType type;

        try {

            type =
                    GeneratorType.valueOf(
                            args[2]
                                    .toUpperCase()
                    );

        } catch(Exception e) {

            player.sendMessage(
                    "§cTipos: IRON, GOLD, DIAMOND, EMERALD"
            );

            return;
        }

        GeneratorCategory category;

        switch(type) {

            case IRON:

                category =
                        GeneratorCategory.IRON;

                break;

            case GOLD:

                category =
                        GeneratorCategory.GOLD;

                break;

            case DIAMOND:

                category =
                        GeneratorCategory.DIAMOND;

                break;

            case EMERALD:

                category =
                        GeneratorCategory.EMERALD;

                break;

            default:

                return;
        }

        Generator generator =
                new Generator(
                        arena,
                        type,
                        category,
                        player.getLocation()
                );

        /*
         * IRON e GOLD precisam de time
         */
        if(type == GeneratorType.IRON
                || type == GeneratorType.GOLD) {

            if(args.length < 4) {

                player.sendMessage(
                        "§cInforme o time."
                );

                return;
            }

            TeamColor color;

            try {

                color =
                        TeamColor.valueOf(
                                args[3]
                                        .toUpperCase()
                        );

            } catch(Exception e) {

                player.sendMessage(
                        "§cCor inválida."
                );

                return;
            }

            BWTeam team =
                    arena.getTeams()
                            .get(color);

            generator.setTeam(
                    team
            );
        }

        arena.getGenerators()
                .add(generator);

        Bedwars.getInstance()
                .getArenaManager()
                .saveArena(arena);

        player.sendMessage(
                "§aGerador "
                + type.name()
                + " criado."
        );
    }
}