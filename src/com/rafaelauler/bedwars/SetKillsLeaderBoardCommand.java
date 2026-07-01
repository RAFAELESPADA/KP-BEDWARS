package com.rafaelauler.bedwars;

import org.bukkit.entity.Player;

public class SetKillsLeaderBoardCommand implements SubCommand {

    @Override
    public void execute(Player player, String[] args) {

        new LeaderBoardManager().createKills(
                player.getLocation()
        );

        player.sendMessage("§aLeaderboard criada.");
    }

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "setkillslb";
	}

	@Override
	public String getPermission() {
		// TODO Auto-generated method stub
		return "bedwars.admin";
	}

	
}