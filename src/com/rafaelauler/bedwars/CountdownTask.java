package com.rafaelauler.bedwars;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class CountdownTask
        extends BukkitRunnable {

    private final Arena arena;

    private int seconds = 30;

    public CountdownTask(
            Arena arena) {

        this.arena = arena;

        arena.setStarting(true);

        arena.setState(
                ArenaState.STARTING
        );
    }

    @Override
    public void run() {
    	  if(arena.getState()
    	            == ArenaState.PLAYING) {

    	        cancel();
    	        return;
    	    }

    	    if(arena.getState()
    	            == ArenaState.ENDING) {

    	        cancel();
    	        return;
    	    }
        if(arena.getPlayers()
                .size() < 2) {

            arena.setStarting(false);

            arena.setCountdown(30);

            arena.setState(
                    ArenaState.WAITING
            );

            cancel();

            return;
        }

        arena.setCountdown(
                seconds
        );

        if(seconds <= 0) {

            arena.setStarting(false);

            GameStart.start(
                    arena
            );

            cancel();

            return;
        }

        if(seconds == 30
                || seconds == 20
                || seconds == 10
                || seconds <= 5) {

            for(Player player :
                    arena.getPlayers()) {

                player.sendMessage(
                        "§aIniciando em "
                                + seconds
                                + " segundos."
                );

                player.playSound(
                        player.getLocation(),
                        Sound.valueOf("CLICK"),
                        1F,
                        1F
                );
            }
        }

        seconds--;
    }
}