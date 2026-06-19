package com.rafaelauler.bedwars;

import net.citizensnpcs.api.event.NPCRightClickEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class NPCListener implements Listener {




	    @EventHandler
	    public void onClick2(
	            NPCRightClickEvent e) {

	        String type =
	                e.getNPC()
	                        .data()
	                        .get(
	                                "bw_npc"
	                        );

	        if(type == null)
	            return;

	        if(type.equals(
	                NPCType.PLAY.name()
	        )) {

	            ArenaSelectorMenu.open(
	                    e.getClicker()
	            );
	        }
	    }
	
    @EventHandler
    public void onClick(
            NPCRightClickEvent e) {

        NPCType type =
                Bedwars.getInstance()
                        .getNpcManager()
                        .getType(
                                e.getNPC().getId()
                        );

        if(type == null)
            return;

        switch(type) {

            case ITEM_SHOP:

                ShopMenu.open(
                        e.getClicker()
                );

                break;

            case TEAM_UPGRADES:

                UpgradeMenu.open(
                        e.getClicker()
                );

                break;
        }
    }
}
