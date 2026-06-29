package com.rafaelauler.bedwars;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;

public class BedwarsExpansion
        extends PlaceholderExpansion {

    @Override
    public String getIdentifier() {
        return "kpbedwars";
    }

    @Override
    public String getAuthor() {
        return "RafaelAuler";
    }

    @Override
    public String getVersion() {
        return "1.0";
    }

    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public String onPlaceholderRequest(
            Player player,
            String params) {

        if(player == null)
            return "";

        PlayerStats stats =
                Bedwars.getInstance()
                        .getStatsManager()
                        .getStats(
                                player.getUniqueId()
                        );

        GamePlayer gp =
                Bedwars.getInstance()
                        .getPlayerManager()
                        .get(player);

        switch(params.toLowerCase()) {
        case "level":

            return String.valueOf(
                    stats.getLevel()
            );
        case "coins":

            return String.valueOf(
                    stats.getCoins()
            );
        case "winstreak":

            return String.valueOf(
                    stats.getWinstreak()
            );
        case "state":

            if(gp == null
                    || gp.getArena() == null)
                return "NONE";

            return gp.getArena()
                    .getState()
                    .name();
        case "team_players":

            if(gp == null
                    || gp.getTeam() == null)
                return "0";

            return String.valueOf(
                    gp.getTeam()
                            .getPlayers()
                            .size()
            );
        case "team_color":

            if(gp == null
                    || gp.getTeam() == null)
                return "NONE";

            return gp.getTeam()
                    .getColor()
                    .name();
        case "team_color_raw":


            if (gp == null || gp.getTeam() == null)
                return "NONE";

            return gp.getTeam()
                    .getColor()
                    .getColor()
                     + gp.getTeam()
                            .getColor()
                            .name()
                            .substring(0, 1) + String.valueOf(" ");
            case "arena":

                return gp == null
                        || gp.getArena() == null
                        ? "None"
                        : gp.getArena().getName();

            case "team":

                return gp == null
                        || gp.getTeam() == null
                        ? "NONE"
                        : gp.getTeam()
                                .getColor()
                                .name();

            case "team_alive":

                return gp == null
                        || gp.getTeam() == null
                        ? "false"
                        : String.valueOf(
                                gp.getTeam()
                                        .isBedAlive()
                        );

            case "arena_players":

                return gp == null
                        || gp.getArena() == null
                        ? "0"
                        : String.valueOf(
                                gp.getArena()
                                        .getPlayers()
                                        .size()
                        );

            case "kills":
                return String.valueOf(
                        stats.getKills()
                );

            case "finalkills":
                return String.valueOf(
                        stats.getFinalKills()
                );

            case "deaths":
                return String.valueOf(
                        stats.getDeaths()
                );
            case "xp":
                return String.valueOf(
                        stats.getExperience()
                );

          

            case "next_level_xp":
                return String.valueOf(
                        Bedwars.getInstance()
                                .getLevelManager()
                                .getRequiredXP(
                                        stats.getLevel()
                                )
                );
            case "arena_max_players":

                if(gp == null
                        || gp.getArena() == null)
                    return "0";

                return String.valueOf(
                        gp.getArena()
                                .getMaxPlayers()
                );
            case "wins":
                return String.valueOf(
                        stats.getWins()
                );

            case "losses":
                return String.valueOf(
                        stats.getLosses()
                );

            case "bedsbroken":
                return String.valueOf(
                        stats.getBedsBroken()
                );

            case "kdr":
                return String.format(
                        "%.2f",
                        stats.getKDR()
                );

            case "fkdr":
                return String.format(
                        "%.2f",
                        stats.getFKDR()
                );

            case "winrate":
                return String.format(
                        "%.2f",
                        stats.getWinRate()
                );

            default:
                return null;
        }
    }
}