package hilfsklassen;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;


public class custom_recipes {
    public void SteinDerWeißen() {
        ItemStack stoneOfTheWhite = new ItemStack(Material.DEEPSLATE_TILES); // Verwende 'STONE' als Platzhalter
        ItemMeta meta = stoneOfTheWhite.getItemMeta();
        if (meta != null) {
            // Setze den Namen des Items
            meta.setDisplayName("§f§lStein der Weißen");

            // Füge die Lore hinzu (lange Beschreibung)
            List<String> lore = new ArrayList<>();
            lore.add("§9§oDer Stein beschützt dich davor, in das Void zu fallen");
            meta.setLore(lore);
            meta.addEnchant(Enchantment.POWER, 10, true);
            meta.getPersistentDataContainer().set(new NamespacedKey("survivalplus", "unique_stein_id"), PersistentDataType.STRING, "stein_001");
            stoneOfTheWhite.setItemMeta(meta);
        }

        // Rezept hinzufügen
        ShapedRecipe recipe = new ShapedRecipe(stoneOfTheWhite);
        recipe.shape("NNN", "   ", "   "); // # ist der Platzhalter für das 'freie Feld'
        recipe.setIngredient('N', Material.NETHERITE_BLOCK); // 'N' für Netherite-Ingot
        Bukkit.getServer().addRecipe(recipe);
    }

}
