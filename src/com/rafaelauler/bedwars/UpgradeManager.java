package com.rafaelauler.bedwars;


public class UpgradeManager {

    public boolean buyForge(
            BWTeam team) {

        if(team.getForgeLevel() >= 4)
            return false;

        team.setForgeLevel(
                team.getForgeLevel() + 1
        );

        return true;
    }

public boolean buySharpness(
        BWTeam team) {

    if(team.hasSharpness())
        return false;

    team.setSharpness(true);

    return true;
}

public boolean buyProtection(
        BWTeam team) {

    if(team.getProtectionLevel() >= 4)
        return false;

    team.setProtectionLevel(
            team.getProtectionLevel() + 1
    );

    return true;
}
}