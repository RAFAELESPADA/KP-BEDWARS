package com.rafaelauler.bedwars;


import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class PlayCommand implements SubCommand {

    @Override
    public String getName() {
        return "play";
    }

    @Override
    public String getPermission() {
        return "";
    }

    @Override
    public void execute(Player player, String[] args) {

        GamePlayer gp = Bedwars.getInstance()
                .getPlayerManager()
                .get(player);

        if (gp == null || gp.getArena() 
        		!= null) {
            player.sendMessage(ChatColor.RED + "Você está em uma partida.");
            return;
        }



        ArenaSelectorMenu.open(player);
    }
}