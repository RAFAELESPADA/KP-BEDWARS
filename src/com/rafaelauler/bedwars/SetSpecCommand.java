package com.rafaelauler.bedwars;

import org.bukkit.entity.Player;


public class SetSpecCommand implements SubCommand {

    @Override
    public String getName() {
        return "setspec";
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

        if (arena == null)
            return;

        arena.setSpectator(
                player.getLocation()
        );

        Bedwars.getInstance()
                .getArenaManager()
                .getArenaFile()
                .setLocation(
                        arena.getName() + ".spectator",
                        player.getLocation()
                );

        player.sendMessage(
                "§aSpawn espectador definido."
        );
    }
}
