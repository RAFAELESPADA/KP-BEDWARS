package com.rafaelauler.bedwars;

import org.bukkit.ChatColor;
import org.bukkit.Color;

public enum TeamColor {

    VERMELHO(Color.RED, ChatColor.RED),
    AZUL(Color.BLUE, ChatColor.BLUE),
    VERDE(Color.GREEN, ChatColor.GREEN),
    BRANCO(Color.WHITE, ChatColor.WHITE),
    CINZA(Color.GRAY, ChatColor.GRAY),
    ROXO(Color.PURPLE, ChatColor.DARK_PURPLE),
    ROSA(Color.OLIVE, ChatColor.LIGHT_PURPLE),
    AMARELO(Color.YELLOW, ChatColor.YELLOW);

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