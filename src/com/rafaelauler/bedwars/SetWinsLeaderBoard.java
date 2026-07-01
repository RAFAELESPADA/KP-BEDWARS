package com.rafaelauler.bedwars;


import org.bukkit.entity.Player;

public class SetWinsLeaderBoard implements SubCommand {

    @Override
    public void execute(Player player, String[] args) {

        new LeaderBoardManager().createWins(
                player.getLocation()
        );

        player.sendMessage("§aLeaderboard criada.");
    }

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "setwinslb";
	}

	@Override
	public String getPermission() {
		// TODO Auto-generated method stub
		return "bedwars.admin";
	}

	
}