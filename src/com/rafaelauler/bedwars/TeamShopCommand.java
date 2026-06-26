package com.rafaelauler.bedwars;


import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class TeamShopCommand implements SubCommand {

    @Override
    public String getName() {
        return "teamshop";
    }

    @Override
    public String getPermission() {
        return "bedwars.join";
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


        UpgradeMenu.open(player);
    }
}
