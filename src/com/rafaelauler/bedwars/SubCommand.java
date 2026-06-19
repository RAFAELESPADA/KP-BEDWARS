package com.rafaelauler.bedwars;

import org.bukkit.entity.Player;

public interface SubCommand {

    String getName();

    String getPermission();

    void execute(Player player, String[] args);

}