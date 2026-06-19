package com.rafaelauler.bedwars;

import org.bukkit.entity.Player;

public class SetMainLobbyCommand
implements SubCommand {

@Override
public String getName() {
return "setmainlobby";
}

@Override
public String getPermission() {
return "bedwars.admin";
}

@Override
public void execute(
    Player player,
    String[] args) {

Bedwars.getInstance().setLobbySpawn(player.getLocation()
        );

Bedwars.getInstance()
        .getLobbyFile()
        .save();

player.sendMessage(
        "§aLobby definido."
);
}
}
