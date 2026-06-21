package com.rafaelauler.bedwars;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BWCommand implements CommandExecutor {

    private final Map<String, SubCommand> subCommands =
            new HashMap<>();

    public BWCommand() {

        register(new CreateCommand());
        register(new DeleteCommand());
        register(new SetLobbyCommand());
        register(new SetSpecCommand());
        register(new ListCommand());
        register(
                new SetNPCCommand()
        );
        register(
                new SetGeneratorCommand()
        );
        register(
                new StatsCommand()
        );
        register(
                new SaveArenaCommand()
        );
        register(
                new SetCosmeticGeneratorCommand()
        );
        register(new CreateTeamCommand());
        register(new SetSpawnCommand());
        register(new SetBedCommand());
        register(new JoinCommand());
        register(new LeaveCommand());

        register(new SetPlayNPCCommand());
        register(new SetMainLobbyCommand());
        register(
                new RemoveNPCCommand()
        );

        register(
                new RemoveGeneratorCommand()
        );
    }

    private void register(SubCommand command) {

        subCommands.put(
                command.getName().toLowerCase(),
                command
        );
    }

    @Override
    public boolean onCommand(
            CommandSender sender,
            Command command,
            String label,
            String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage("Somente jogadores.");
            return true;
        }

        Player player = (Player) sender;

        if (args.length == 0) {

        	player.sendMessage("§6§lBedWars Setup");
        	player.sendMessage("§7------------------");
        	player.sendMessage("§e/bw create <arena>");
        	player.sendMessage("§e/bw delete <arena>");
        	player.sendMessage("§e/bw stats");
        	player.sendMessage("§e/bw setlobby <arena>");
        	player.sendMessage("§e/bw setplaynpc");
        	player.sendMessage("§e/bw setmainlobby");
        	player.sendMessage("§e/bw setspec <arena>");
        	player.sendMessage("§e/bw createteam <arena> <cor>");
        	player.sendMessage("§e/bw setspawn <arena> <cor>");
        	player.sendMessage("§e/bw setbed <arena> <cor>");
        	player.sendMessage("§e/bw setnpc <arena> <itemshop|upgrades>");
        	player.sendMessage("§e/bw setgenerator <arena> <diamond|emerald>");

        	player.sendMessage("§e/bw setcosmeticgenerator diamond|emerald|gold_block>");
        	player.sendMessage("§e/bw list");
        	player.sendMessage(
        	        "§e/bw removenpc <arena> <itemshop|upgrades>"
        	);
        	player.sendMessage(
        	        "§e/bw savearena <arena>"
        	);
        	player.sendMessage(
        	        "§e/bw removegenerator <arena>"
        	);
            return true;
        }

        SubCommand sub =
                subCommands.get(
                        args[0].toLowerCase()
                );

        if (sub == null) {

            player.sendMessage(
                    "§cSubcomando não encontrado."
            );

            return true;
        }

        if (!player.hasPermission(
                sub.getPermission())) {

            player.sendMessage(
                    "§cSem permissão."
            );

            return true;
        }

        sub.execute(player, args);

        return true;
    }
}