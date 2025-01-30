package org.SurvivalPlus.survivalPlus;

import org.bukkit.Server;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.entity.Player;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import hilfsklassen.*;

import java.util.List;

public class VoidProtectionListener implements Listener {

    private final JavaPlugin plugin;

    public VoidProtectionListener(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) { //Erkenne, wenn Spieler ins Void Fällt
        Player player = event.getPlayer();
        Location playerLocation = player.getLocation();
        World world = player.getWorld();

        if (world.getEnvironment() == World.Environment.THE_END) {
            if (playerLocation.getBlockY() <= -30) {
                boolean hasStoneOfTheWhite = false;
                for (ItemStack item : player.getInventory()) {
                    if (item != null && item.getType() == Material.DEEPSLATE_TILES) {
                        ItemMeta meta = item.getItemMeta();
                        if (meta != null && meta.hasDisplayName() && meta.getDisplayName().equals("§f§lStein der Weißen")) {
                            List<String> lore = meta.getLore();
                            if (lore != null && lore.contains("§9§oDer Stein beschützt dich davor, in das Void zu fallen")) {
                                hasStoneOfTheWhite = true;
                                break;
                            }
                        }
                    }
                }

                if (hasStoneOfTheWhite) { //TP Spieler wieder hoch
                    Location highestBlockLocation = player.getWorld().getHighestBlockAt(playerLocation).getLocation();
                    if (highestBlockLocation.getBlockY() <= 0) {
                        highestBlockLocation = playerLocation.add(0, 70, 0);
                    } else {
                        highestBlockLocation = highestBlockLocation.add(0, 1, 0);
                    }
                    Location safeLocation = highestBlockLocation;
                    player.setFallDistance(0);
                    player.teleport(safeLocation);

                    Location blockUnderPlayer = safeLocation.clone().subtract(0, 1, 0);
                    blockUnderPlayer.getBlock().setType(Material.END_STONE);

                    Server server = plugin.getServer();
                    server.getPlayer(player.getName()).sendMessage("§2Der Stein der Weißen hat dich vor dem Tod beschützt und einen Block unter dir platziert");
                }

            }
        }
    }
}
