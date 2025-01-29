package org.SurvivalPlus.survivalPlus;

import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        // Code für PlayerJoinEvent
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        // Code für PlayerQuitEvent
    }
}

