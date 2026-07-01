package com.rafaelauler.bedwars;


import org.bukkit.entity.Player;

public class SetDeathsLeaderBoard implements SubCommand {

    @Override
    public void execute(Player player, String[] args) {

        new LeaderBoardManager().createDeaths(
                player.getLocation()
        );

        player.sendMessage("§aLeaderboard criada.");
    }

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "setdeathslb";
	}

	@Override
	public String getPermission() {
		// TODO Auto-generated method stub
		return "bedwars.admin";
	}

	
}