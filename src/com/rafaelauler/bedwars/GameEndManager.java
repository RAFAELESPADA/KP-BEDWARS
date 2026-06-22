package com.rafaelauler.bedwars;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class GameEndManager {

    public void checkWinner(
            Arena arena) {

        BWTeam winner = null;

        int aliveTeams = 0;

        for(BWTeam team :
                arena.getTeams().values()) {

            if(!team.isBedAlive() && team.getPlayers().isEmpty())
                continue;

            aliveTeams++;

            winner = team;
        }

        if(aliveTeams != 1
                || winner == null)
            return;

        endGame(
                arena,
                winner
        );
    }

    private void sendToLobby(
            Player player) {

        GamePlayer gp =
                Bedwars.getInstance()
                        .getPlayerManager()
                        .get(player);
        player.teleport(
                Bedwars.getInstance()
                        .getLobbySpawn()
        );
        if(gp != null) {

            if(gp.getTeam() != null) {

                gp.getTeam()
                        .getPlayers()
                        .remove(
                                player.getUniqueId()
                        );
            }
            Arena arena =
                    gp.getArena();

            if(arena != null) {

                arena.getPlayers()
                        .remove(player);

                arena.getGamePlayers()
                        .remove(gp);
            }
            gp.setArena(null);
            gp.setTeam(null);
        }

        player.getInventory()
                .clear();

        player.setHealth(
                20.0
        );

        player.setFoodLevel(
                20
        );

        player.setFireTicks(
                0
        );

        player.setGameMode(
                GameMode.ADVENTURE
        );

        

        player.setScoreboard(
                Bukkit.getScoreboardManager()
                        .getNewScoreboard()
        );

        LobbyItems.give(
                player
        );

        Bedwars.getInstance()
                .getLobbyScoreboard()
                .update(
                        player
                );
    }

    public void endGame(
            Arena arena,
            BWTeam winner) {

        if(arena.getState()
                == ArenaState.ENDING)
            return;

        arena.setState(
                ArenaState.ENDING
        );

        Bukkit.broadcastMessage(
                "§6§lBEDWARS §8» §fO time "
                + winner.getColor()
                        .getColor()
                + winner.getColor()
                        .name()
                + " §fvenceu!"
        );

        rewardPlayers(
                arena,
                winner
        );

        new BukkitRunnable() {

            int seconds = 10;

            @Override
            public void run() {

                if(seconds <= 0) {

                	for(Player player :
                        new ArrayList<>(
                                arena.getPlayers()
                        )) {

                    sendToLobby(player);
                }

                    Bukkit.getScheduler()
                            .runTaskLater(
                                    Bedwars.getInstance(),

                                    () -> Bedwars
                                            .getInstance()
                                            .getArenaReset()
                                            .reset(
                                                    arena
                                            ),

                                    20L
                            );

                    cancel();
                    return;
                }

                if(seconds <= 5) {

                    for(Player player :
                            arena.getPlayers()) {

                        player.sendMessage(
                                "§aVoltando ao lobby em "
                                + seconds
                                + "s"
                        );
                    }
                }

                seconds--;
            }

        }.runTaskTimer(
                Bedwars.getInstance(),
                20L,
                20L
        );
    }

    private void rewardPlayers(
            Arena arena,
            BWTeam winner) {

        /*
         * Vencedores
         */
        for(UUID uuid :
                winner.getPlayers()) {

            PlayerStats stats =
                    Bedwars.getInstance()
                            .getStatsManager()
                            .getStats(uuid);

            stats.setWins(
                    stats.getWins() + 1
            );

            Player player =
                    Bukkit.getPlayer(uuid);

            if(player != null) {

                Bedwars.getInstance()
                        .getRewardManager()
                        .rewardWin(
                                player
                        );
            }
        }

        /*
         * Perdedores
         */
        for(BWTeam team :
                arena.getTeams().values()) {

            if(team == winner)
                continue;

            for(UUID uuid :
                    team.getPlayers()) {

                PlayerStats stats =
                        Bedwars.getInstance()
                                .getStatsManager()
                                .getStats(uuid);

                stats.setLosses(
                        stats.getLosses() + 1
                );

                Player player =
                        Bukkit.getPlayer(uuid);

                if(player != null) {

                    Bedwars.getInstance()
                            .getRewardManager()
                            .rewardLoss(
                                    player
                            );
                }
            }
        }
    }
}