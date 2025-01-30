package hilfsklassen;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class ShopGUI {

    public void openGUI(Player player) {
        Inventory inventory = Bukkit.createInventory(null, 9, "Egg Shop");

        ItemStack[] items = {
                new ItemStack(Material.CAVE_SPIDER_SPAWN_EGG),
                new ItemStack(Material.ZOMBIE_SPAWN_EGG),
                new ItemStack(Material.VILLAGER_SPAWN_EGG),
                new ItemStack(Material.BEE_SPAWN_EGG),
                new ItemStack(Material.SHEEP_SPAWN_EGG),
                new ItemStack(Material.PIG_SPAWN_EGG),
                new ItemStack(Material.COW_SPAWN_EGG),
                new ItemStack(Material.FOX_SPAWN_EGG),
                new ItemStack(Material.SPAWNER)
        };

        String[] descriptions = {
                "§a§l64x Dias",
                "§a§l64x Dias",
                "§a§l64x Dias",
                "§a§l64x Dias",
                "§a§l64x Dias",
                "§a§l64x Dias",
                "§a§l64x Dias",
                "§a§l64x Dias",
                "§a§l64x Dias"
        };

        for (int i = 0; i < 9; i++) {
            ItemStack item = items[i];
            ItemMeta meta = item.getItemMeta();
            if (meta != null) {
                meta.setDisplayName("§fItem " + (i + 1));
                meta.setLore(java.util.Arrays.asList(descriptions[i]));
                item.setItemMeta(meta);
            }
            inventory.setItem(i, item);  // Position von 0 bis 4
        }

        // Öffne das Inventory für den Spieler
        player.openInventory(inventory);
    }
}
