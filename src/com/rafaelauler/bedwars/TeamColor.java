package com.rafaelauler.bedwars;

import org.bukkit.ChatColor;
import org.bukkit.Color;

public enum TeamColor {

    VERMELHO( ChatColor.RED, Color.RED),
    AZUL( ChatColor.BLUE, Color.BLUE),
    VERDE(ChatColor.GREEN, Color.GREEN),
    BRANCO(ChatColor.WHITE, Color.WHITE),
    CINZA( ChatColor.GRAY, Color.GRAY),
    ROXO( ChatColor.DARK_PURPLE, Color.PURPLE),
    ROSA(ChatColor.LIGHT_PURPLE, Color.OLIVE),
    AMARELO(ChatColor.YELLOW,Color.YELLOW);

    private final Color leatherColor;
    private final ChatColor chatColor;
    TeamColor(
    		ChatColor chatColor, Color leatherColor) {

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