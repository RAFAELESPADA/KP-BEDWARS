package com.rafaelauler.bedwars;


import org.bukkit.entity.Player;

public class SetPlayNPCCommand
        implements SubCommand {

    @Override
    public String getName() {
        return "setplaynpc";
    }

    @Override
    public String getPermission() {
        return "bedwars.admin";
    }

    @Override
    public void execute(
            Player player,
            String[] args) {

        Bedwars.getInstance()
                .getNpcManager()
                .createPlayNPC(
                        player.getLocation()
                );

        player.sendMessage(
                "§aNPC criado."
        );
    }
}
