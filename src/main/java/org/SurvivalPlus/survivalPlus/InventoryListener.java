package org.SurvivalPlus.survivalPlus;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;

public class InventoryListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {

        /*
        *
        * Verhindere das Entfernen von Items aus dem Egg Shop
        *
        * */

        if (event.getView().getTitle().equals("Egg Shop")) {
            if (event.getWhoClicked() instanceof Player) {
                event.setCancelled(true);
            }
        }

        /*
        *
        * Kaufen eines Items im EGG Shop
        *
        * */

        if (event.getView().getTitle().equals("Egg Shop")) {
            event.setCancelled(true);
            Player player = (Player) event.getWhoClicked();
            ItemStack clickedItem = event.getCurrentItem();

            if (clickedItem != null && clickedItem.getType() != Material.AIR) {
                if (player.getInventory().contains(Material.DIAMOND_BLOCK, 16)) {

                    player.getInventory().removeItem(new ItemStack(Material.DIAMOND_BLOCK, 16));
                    ItemStack itemToGive = new ItemStack(clickedItem.getType());
                    player.getInventory().addItem(itemToGive);
                    player.sendMessage("§aDu hast " + clickedItem.getType().toString().toLowerCase().replace("_", " ") + " gekauft!");
                    player.closeInventory();
                } else {
                    player.sendMessage("§cDu benötigst 16 Diablöcke, um dieses Item zu kaufen.");
                }
            }
        }


    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        // Code für InventoryCloseEvent
    }
}
