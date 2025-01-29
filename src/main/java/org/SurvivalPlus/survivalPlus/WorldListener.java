package org.SurvivalPlus.survivalPlus;

import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.world.WorldLoadEvent;
import org.bukkit.event.world.WorldUnloadEvent;

public class WorldListener implements Listener {

    @EventHandler
    public void onWorldLoad(WorldLoadEvent event) {
        // Code für WorldLoadEvent
    }

    @EventHandler
    public void onWorldUnload(WorldUnloadEvent event) {
        // Code für WorldUnloadEvent
    }
}

