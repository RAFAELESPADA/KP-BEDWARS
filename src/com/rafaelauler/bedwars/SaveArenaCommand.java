package com.rafaelauler.bedwars;


import org.bukkit.entity.Player;

public class SaveArenaCommand
        implements SubCommand {

    @Override
    public String getName() {
        return "savearena";
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
                    "§cUse: /bw savearena <arena>"
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

        Bedwars.getInstance()
                .getArenaManager()
                .saveArena(arena);

        player.sendMessage(
                "§aArena salva."
        );
    }
}
