package com.rafaelauler.bedwars;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Location;
import org.bukkit.event.Listener;

import eu.decentsoftware.holograms.api.DHAPI;
import eu.decentsoftware.holograms.api.holograms.Hologram;

public class Leaderboard implements Listener {

    private final String id;
    private final String placeholder;
    private final String title;
    private static final Map<String, Hologram> CACHE = new HashMap<>();
    private int page = 0;

    private Hologram hologram;

    public Leaderboard(Location location,
            String id,
            String title,
            String placeholder) {

this.id = id;
this.title = title;
this.placeholder = placeholder;

this.hologram = CACHE.get(id);

if (this.hologram == null) {

 this.hologram = DHAPI.getHologram(id);

 if (this.hologram == null) {

     this.hologram = DHAPI.createHologram(
             id,
             location,
             true
     );
 }

 CACHE.put(id, this.hologram);
}

update();
}

    public void next() {

        if (page < 9) {
            page++;
            update();
        }
    }

    public void previous() {

        if (page > 0) {
            page--;
            update();
        }
    }

    public void update() {

        List<String> lines = new ArrayList<>();

        lines.add(title);
        lines.add("§7Página " + (page + 1) + "/10");
        lines.add("");

        int start = page * 10 + 1;

        for (int i = start; i < start + 10; i++) {

            lines.add(
                    "§e#" + i
                    + " §f%ajlb_lb_"
                    + placeholder
                    + "_"
                    + i+"_alltime"
                    + "_name%"
                    + " §7- §6%ajlb_lb_"
                    + placeholder
                    + "_"
                    + i +"_alltime"
                    + "_value%"
            );
        }

        lines.add("");
        lines.add("§c◀ Página Anterior");
        lines.add("§aPróxima Página ▶");
        DHAPI.setHologramLines(hologram, lines);
    }

    public Hologram getHologram() {
        return hologram;
    }


}
