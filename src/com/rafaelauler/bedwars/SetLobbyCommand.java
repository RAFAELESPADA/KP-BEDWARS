package com.rafaelauler.bedwars;


import org.bukkit.entity.Player;

public class SetLobbyCommand implements SubCommand {

    @Override
    public String getName() {
        return "setlobby";
    }

    @Override
    public String getPermission() {
        return "bedwars.admin";
    }

    @Override
    public void execute(Player player, String[] args) {

        if (args.length < 2)
            return;

        Arena arena =
                Bedwars.getInstance()
                        .getArenaManager()
                        .getArena(args[1]);

        if (arena == null) {

            player.sendMessage(
                    "§cArena não encontrada."
            );

            return;
        }

        arena.setLobby(player.getLocation());

        Bedwars.getInstance()
                .getArenaManager()
                .getArenaFile()
                .setLocation(
                        arena.getName() + ".lobby",
                        player.getLocation()
                );

        player.sendMessage(
                "§aLobby definido."
        );
    }
}
