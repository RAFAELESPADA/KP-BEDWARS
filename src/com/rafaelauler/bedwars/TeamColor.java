package com.rafaelauler.bedwars;

import org.bukkit.ChatColor;
import org.bukkit.Color;

public enum TeamColor {

    RED(Color.RED, ChatColor.RED),

    BLUE(Color.BLUE, ChatColor.BLUE),

    GREEN(Color.GREEN, ChatColor.GREEN),

    YELLOW(Color.YELLOW, ChatColor.YELLOW);

    private final Color leatherColor;
    private final ChatColor chatColor;
    TeamColor(
            Color leatherColor, ChatColor chatColor) {

        this.leatherColor =
                leatherColor;
        this.chatColor =
        		chatColor;
    }

    public Color getLeatherColor() {
        return leatherColor;
    }
    public ChatColor getColor() {
        return chatColor;
    }
}