package com.rafaelauler.bedwars;

import org.bukkit.entity.Player;

public class LeaveCommand implements SubCommand {

    @Override
    public String getName() {
        return "leave";
    }

    @Override
    public String getPermission() {
        return "bedwars.join";
    }

    @Override
    public void execute(Player player, String[] args) {

        GamePlayer gp =
                Bedwars.getInstance()
                        .getPlayerManager()
                        .get(player);

        if(gp.getArena() == null)
            return;

        gp.getArena()
                .getPlayers()
                .remove(player);

        gp.getTeam()
                .getPlayers()
                .remove(player.getUniqueId());

	    Bedwars.getInstance().getGameEndManager().checkWinner(gp.getArena());
   
        player.teleport(Bedwars.getInstance().getLobbySpawn());
        player.getInventory().clear();
        player.getInventory().setArmorContents(null);
        LobbyItems.give(player);
        gp.setArena(null);
        gp.setTeam(null);
        player.sendMessage(
                "§cVocê saiu da arena."
        );
    }
}