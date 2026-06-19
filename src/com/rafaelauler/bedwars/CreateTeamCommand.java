package com.rafaelauler.bedwars;

import org.bukkit.entity.Player;

public class CreateTeamCommand implements SubCommand {

    @Override
    public String getName() {
        return "createteam";
    }

    @Override
    public String getPermission() {
        return "bedwars.admin";
    }

    @Override
    public void execute(Player player, String[] args) {

        if(args.length < 3) {
            player.sendMessage("§c/bw createteam <arena> <cor>");
            return;
        }

        Arena arena =
                Bedwars.getInstance()
                        .getArenaManager()
                        .getArena(args[1]);

        if(arena == null) {
            player.sendMessage("§cArena não encontrada.");
            return;
        }

        TeamColor color;

        try {
            color = TeamColor.valueOf(
                    args[2].toUpperCase()
            );
        } catch(Exception ex) {

            player.sendMessage("§cCor inválida.");
            return;
        }

        if(!arena.createTeam(color)) {

            player.sendMessage("§cEsse time já existe.");
            return;
        }

        Bedwars.getInstance()
                .getArenaManager()
                .getArenaFile()
                .getConfig()
                .set(
                        arena.getName()
                        + ".Teams."
                        + color.name(),
                        true
                );

        Bedwars.getInstance()
                .getArenaManager()
                .getArenaFile()
                .save();

        player.sendMessage(
                "§aTime criado."
        );
    }
}