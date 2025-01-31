package org.SurvivalPlus.survivalPlus;

import hilfsklassen.ShopGUI;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.java.JavaPlugin;

/*
*
*
* Skript Regelt die Interaktion zwischen Spieler und Eier Shop GUI.
*
*
* */


public class EggShopListener implements Listener {

    private final JavaPlugin plugin;

    public EggShopListener(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        if (event.getItemInHand().getType() == Material.BOOKSHELF) {
            Block block = event.getBlockPlaced();
            if (event.getItemInHand().getItemMeta() != null &&
                    event.getItemInHand().getItemMeta().getDisplayName().equals("§fEgg Shop")) {
                block.setMetadata("eggshop", new FixedMetadataValue(plugin, true));
                block.getWorld().spawnParticle(org.bukkit.Particle.GLOW, block.getLocation().add(0.5, 1.0, 0.5), 30, 0.2, 0.2, 0.2, 0.1);
            }
        }
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Block block = event.getClickedBlock();

            if (block != null && block.getType() == Material.BOOKSHELF) {
                if (block.hasMetadata("eggshop")) {
                    ShopGUI shop = new ShopGUI();
                    shop.openGUI(player);
                }
            }
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Block block = event.getBlock();

        if (block.getType() == Material.BOOKSHELF && block.hasMetadata("eggshop")) {
            event.setDropItems(false);
            block.getWorld().dropItemNaturally(block.getLocation(), new org.bukkit.inventory.ItemStack(Material.DIAMOND, 3));
        }
    }


}
