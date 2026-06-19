package com.rafaelauler.bedwars;


import org.bukkit.entity.Player;


public class DeleteCommand implements SubCommand {

    @Override
    public String getName() {
        return "delete";
    }

    @Override
    public String getPermission() {
        return "bedwars.admin";
    }

    @Override
    public void execute(Player player, String[] args) {

        if (args.length < 2) {

            player.sendMessage(
                    "§cUse: /bw delete <arena>"
            );

            return;
        }

        if (Bedwars.getInstance()
                .getArenaManager()
                .deleteArena(args[1])) {

            player.sendMessage(
                    "§aArena removida."
            );

        } else {

            player.sendMessage(
                    "§cArena não encontrada."
            );
        }
    }
}