package com.rafaelauler.bedwars;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
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
Player p = player;
	Bedwars.getInstance().getConfig().set("Lobby.World", p.getLocation().getWorld().getName());
	Bedwars.getInstance().getConfig().set("Lobby.X", Double.valueOf(p.getLocation().getX()));
	Bedwars.getInstance().getConfig().set("Lobby.Y", Double.valueOf(p.getLocation().getY()));
	Bedwars.getInstance().getConfig().set("Lobby.Z", Double.valueOf(p.getLocation().getZ()));
	Bedwars.getInstance().getConfig().set("Lobby.Pitch", Float.valueOf(p.getLocation().getPitch()));
	Bedwars.getInstance().getConfig().set("Lobby.Yaw", Float.valueOf(p.getLocation().getYaw()));

try {
	Bedwars.getInstance().getConfig().options().copyDefaults(true);
	Bedwars.getInstance().getConfig().options().copyHeader(true);
	Bedwars.getInstance().getConfig().save(new File(Bukkit.getWorldContainer().getAbsolutePath() + "/plugins/KPBedWars/config.yml"));
} catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}

player.sendMessage(
        "§aLobby definido."
);
}
}
