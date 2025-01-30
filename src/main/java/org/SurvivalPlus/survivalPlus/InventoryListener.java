package org.SurvivalPlus.survivalPlus;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class InventoryListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {

        /*
        *
        * Verhindere das Entfernen von Items aus dem Egg Shop (/shop)
        *
        * */

        if (event.getView().getTitle().equals("Egg Shop")) {
            if (event.getWhoClicked() instanceof Player) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        // Code für InventoryCloseEvent
    }
}
