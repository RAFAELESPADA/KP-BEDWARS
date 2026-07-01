package com.rafaelauler.bedwars;


import org.bukkit.entity.Player;

public class SetXPLeaderBoard implements SubCommand {

    @Override
    public void execute(Player player, String[] args) {

        new LeaderBoardManager().createXP(
                player.getLocation()
        );

        player.sendMessage("§aLeaderboard criada.");
    }

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "setxplb";
	}

	@Override
	public String getPermission() {
		// TODO Auto-generated method stub
		return "bedwars.admin";
	}

	
}