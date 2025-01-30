package org.SurvivalPlus.survivalPlus;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.entity.Player;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;


public class PickaxeBreakListener implements Listener {
    private final JavaPlugin plugin;

    public PickaxeBreakListener(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();

        if (item.getType() == Material.NETHERITE_PICKAXE && item.hasItemMeta()) {
            ItemMeta meta = item.getItemMeta();
            if (meta != null && meta.getPersistentDataContainer().has(new NamespacedKey(plugin, "unique_pickaxe_id"), PersistentDataType.STRING)) {
                mine3x3(event.getBlock(), player);
            }
        }
    }

    private void mine3x3(Block center, Player player) {
        World world = center.getWorld();
        Location centerLoc = center.getLocation();

        // Liste der blockierten Blöcke (Ore, Oberfläche, Hard Blöcke)
        List<Material> blockedMaterials = List.of(
                Material.BEDROCK, Material.GRAVEL, Material.DIRT, Material.END_STONE, Material.OBSIDIAN,
                Material.OAK_LOG, Material.ACACIA_LOG, Material.DARK_OAK_LOG, Material.BIRCH_LOG,
                Material.CHERRY_LOG, Material.JUNGLE_LOG, Material.MANGROVE_LOG, Material.PALE_OAK_LOG, Material.SPRUCE_LOG,
                Material.SAND, Material.RED_SAND, Material.GRASS_BLOCK, Material.SOUL_SAND, Material.COAL_ORE,
                Material.DEEPSLATE_COAL_ORE, Material.IRON_ORE, Material.DEEPSLATE_IRON_ORE,Material.COPPER_ORE,
                Material.DEEPSLATE_COPPER_ORE, Material.GOLD_ORE, Material.DEEPSLATE_GOLD_ORE, Material.REDSTONE_ORE,
                Material.DEEPSLATE_REDSTONE_ORE, Material.EMERALD_ORE, Material.DEEPSLATE_EMERALD_ORE, Material.DEEPSLATE_LAPIS_ORE,
                Material.LAPIS_ORE, Material.DEEPSLATE_DIAMOND_ORE, Material.DIAMOND_ORE, Material.DEEPSLATE_DIAMOND_ORE,
                Material.DRAGON_EGG, Material.END_PORTAL_FRAME, Material.SPAWNER
        );

        // 3x3-Feld bestimmen
        for (int dx = -1; dx <= 1; dx++) {
            for (int dz = -1; dz <= 1; dz++) {
                Location loc = centerLoc.clone().add(dx, 0, dz);
                Block block = world.getBlockAt(loc);

                if (!block.equals(center) && !blockedMaterials.contains(block.getType()) && block.getType() != Material.AIR) {
                    block.breakNaturally(player.getInventory().getItemInMainHand());
                }
            }
        }
    }

}

