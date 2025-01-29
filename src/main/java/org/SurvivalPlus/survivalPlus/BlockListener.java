package org.SurvivalPlus.survivalPlus;

import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockListener implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        // Code für BlockBreakEvent
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        // Code für BlockPlaceEvent
    }
}
