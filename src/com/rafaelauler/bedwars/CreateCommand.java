package com.rafaelauler.bedwars;

import org.bukkit.entity.Player;


public class CreateCommand implements SubCommand {

    @Override
    public String getName() {
        return "create";
    }

    @Override
    public String getPermission() {
        return "bedwars.admin";
    }

    @Override
    public void execute(Player player, String[] args) {

        if (args.length < 2) {

            player.sendMessage(
                    "§cUse: /bw create <arena>"
            );

            return;
        }

        String name = args[1];

        boolean created =
                Bedwars.getInstance()
                        .getArenaManager()
                        .createArena(name);

        if (created) {

            player.sendMessage(
                    "§aArena criada."
            );

        } else {

            player.sendMessage(
                    "§cEssa arena já existe."
            );
        }
    }
}