package com.rafaelauler.bedwars;


import org.bukkit.entity.Player;

public class SetSpawnCommand implements SubCommand {

    @Override
    public String getName() {
        return "setspawn";
    }

    @Override
    public String getPermission() {
        return "bedwars.admin";
    }

    @Override
    public void execute(Player player, String[] args) {

        if(args.length < 3)
            return;

        Arena arena =
                Bedwars.getInstance()
                        .getArenaManager()
                        .getArena(args[1]);

        if(arena == null)
            return;

        TeamColor color =
                TeamColor.valueOf(
                        args[2].toUpperCase()
                );

        BWTeam team =
                arena.getTeam(color);

        if(team == null)
            return;

        team.setSpawn(
                player.getLocation()
        );

        Bedwars.getInstance()
                .getArenaManager()
                .getArenaFile()
                .setLocation(
                        arena.getName()
                                + ".Teams."
                                + color.name()
                                + ".spawn",
                        player.getLocation()
                );

        player.sendMessage(
                "§aSpawn definido."
        );
    }
}
