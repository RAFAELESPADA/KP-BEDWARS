package com.rafaelauler.bedwars;

public class LevelManager {

    public int getRequiredXP(
            int level) {

        return 500 + ((level - 1) * 250);
    }

    public void addXP(
            PlayerStats stats,
            int xp) {

        stats.setExperience(
                stats.getExperience() + xp
        );

        while(stats.getExperience()
                >= getRequiredXP(
                        stats.getLevel()
                )) {

            stats.setExperience(
                    stats.getExperience()
                    - getRequiredXP(
                            stats.getLevel()
                    )
            );

            stats.setLevel(
                    stats.getLevel() + 1
            );
        }
    }
}