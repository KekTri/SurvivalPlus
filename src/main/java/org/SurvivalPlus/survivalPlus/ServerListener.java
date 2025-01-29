package org.SurvivalPlus.survivalPlus;

import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.server.ServerLoadEvent;
import org.bukkit.event.server.ServerCommandEvent;

public class ServerListener implements Listener {

    @EventHandler
    public void onServerLoad(ServerLoadEvent event) {
        // Code für ServerLoadEvent
    }

    @EventHandler
    public void onServerCommand(ServerCommandEvent event) {
        // Code für ServerCommandEvent
    }
}
