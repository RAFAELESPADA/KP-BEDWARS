package com.rafaelauler.bedwars;

import org.bukkit.entity.Player;

public class ListCommand implements SubCommand {

    @Override
    public String getName() {
        return "list";
    }

    @Override
    public String getPermission() {
        return "bedwars.admin";
    }

    @Override
    public void execute(Player player, String[] args) {

        player.sendMessage("§6Arenas:");

        for (Arena arena :
                Bedwars.getInstance()
                        .getArenaManager()
                        .getArenas()
                        .values()) {

            player.sendMessage(
                    " §e- " + arena.getName()
            );
        }
    }
}