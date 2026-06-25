package com.rafaelauler.bedwars;


import org.bukkit.entity.Player;

public class SetNPCCommand implements SubCommand {

    @Override
    public String getName() {
        return "setnpc";
    }

    @Override
    public String getPermission() {
        return "bedwars.admin";
    }

    @Override
    public void execute(
            Player player,
            String[] args) {

        if(args.length < 3) {

            player.sendMessage(
                    "§cUse: /bw setnpc <arena> <itemshop|upgrades>"
            );

            return;
        }

        Arena arena =
                Bedwars.getInstance()
                        .getArenaManager()
                        .getArena(args[1]);

        if(arena == null) {

            player.sendMessage(
                    "§cArena não encontrada."
            );

            return;
        }

        String type =
                args[2].toLowerCase();

        if(type.equals("itemshop")) {

            Bedwars.getInstance()
                    .getNpcManager()                  
                    .createItemShop(
                            arena,
                            player.getLocation()
                    );

            Bedwars.getInstance()
                    .getArenaManager()
                    .getArenaFile()
                    .setLocation(
                            arena.getName()
                            + ".NPCS.ITEM_SHOP",
                            player.getLocation()
                    );

            player.sendMessage(
                    "§aNPC Item Shop criado."
            );

            return;
        }

        if(type.equals("upgrades")) {

            Bedwars.getInstance()
                    .getNpcManager().createUpgradeShop(
                            arena,
                            player.getLocation()
                    );

            Bedwars.getInstance()
                    .getArenaManager()
                    .getArenaFile()
                    .setLocation(
                            arena.getName()
                            + ".NPCS.TEAM_UPGRADES",
                            player.getLocation()
                    );

            player.sendMessage(
                    "§aNPC Team Upgrades criado."
            );

            return;
        }

        player.sendMessage(
                "§cTipos disponíveis:"
        );

        player.sendMessage(
                "§7- itemshop"
        );

        player.sendMessage(
                "§7- upgrades"
        );
    }
}
