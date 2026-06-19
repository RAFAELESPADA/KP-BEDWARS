package com.rafaelauler.bedwars;


import org.bukkit.entity.Player;

public class StatsCommand
        implements SubCommand {

    @Override
    public String getName() {
        return "stats";
    }

    @Override
    public String getPermission() {
        return "bedwars.stats";
    }

    @Override
    public void execute(
            Player player,
            String[] args) {

        PlayerStats stats =
                Bedwars.getInstance()
                        .getStatsManager()
                        .getStats(
                                player.getUniqueId()
                        );

        player.sendMessage("§6§m----------------");

        player.sendMessage(
                "§eKills: §f"
                + stats.getKills()
        );

        player.sendMessage(
                "§eFinal Kills: §f"
                + stats.getFinalKills()
        );

        player.sendMessage(
                "§eDeaths: §f"
                + stats.getDeaths()
        );

        player.sendMessage(
                "§eWins: §f"
                + stats.getWins()
        );

        player.sendMessage(
                "§eLosses: §f"
                + stats.getLosses()
        );

        player.sendMessage(
                "§eBeds Broken: §f"
                + stats.getBedsBroken()
        );

        player.sendMessage(
                "§eKDR: §f"
                + String.format(
                        "%.2f",
                        stats.getKDR()
                )
        );

        player.sendMessage("§6§m----------------");
    }
}