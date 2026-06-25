package com.rafaelauler.bedwars;


import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class ShopCommand implements SubCommand {

    @Override
    public String getName() {
        return "shop";
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

        if (gp == null || gp.getArena() == null) {
            player.sendMessage(ChatColor.RED + "Você não está em uma partida.");
            return;
        }

        Arena arena = gp.getArena();

        if (arena.getState() != ArenaState.PLAYING) {
            player.sendMessage(ChatColor.RED + "Você só pode usar este comando durante a partida.");
            return;
        }


        ShopMenu.open(player);
    }
}