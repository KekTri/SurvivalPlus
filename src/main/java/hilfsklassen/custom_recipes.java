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

    /*
     *
     * Rezept des Steines der Weißen
     *
     * */

    public void SteinDerWeißen() {
        ItemStack stoneOfTheWhite = new ItemStack(Material.DEEPSLATE_TILES);
        ItemMeta meta = stoneOfTheWhite.getItemMeta();
        if (meta != null) {
            meta.setDisplayName("§f§lStein der Weißen");

            List<String> lore = new ArrayList<>();
            lore.add("§9§oDer Stein beschützt dich davor, in das Void zu fallen");
            meta.setLore(lore);
            meta.addEnchant(Enchantment.POWER, 10, true);
            meta.getPersistentDataContainer().set(new NamespacedKey("survivalplus", "unique_stein_id"), PersistentDataType.STRING, "stein_001");
            stoneOfTheWhite.setItemMeta(meta);
        }

        ShapedRecipe recipe = new ShapedRecipe(stoneOfTheWhite);
        recipe.shape("NNN", "   ", "   ");
        recipe.setIngredient('N', Material.NETHERITE_BLOCK);
        Bukkit.getServer().addRecipe(recipe);
    }

    /*
     *
     * Rezept für Netherite Schwert
     *
     * */

    public void UpgradetNetheriteSword(JavaPlugin plugin) {
        ItemStack upgradedSword = new ItemStack(Material.NETHERITE_SWORD);
        ItemMeta meta = upgradedSword.getItemMeta();

        if (meta != null) {
            meta.setDisplayName("§fNetherite Sword (Upgraded)");

            meta.addEnchant(Enchantment.UNBREAKING, 5, true);
            meta.addEnchant(Enchantment.LOOTING, 6, true);

            meta.getPersistentDataContainer().set(new NamespacedKey(plugin, "unique_sword_id"), PersistentDataType.STRING, "upgraded_netherite_sword");

            upgradedSword.setItemMeta(meta);
        }

        NamespacedKey key = new NamespacedKey(plugin, "upgraded_netherite_sword");
        ShapedRecipe recipe = new ShapedRecipe(key, upgradedSword);
        recipe.shape(" N ", " N ", " S ");
        recipe.setIngredient('N', Material.NETHERITE_BLOCK);
        recipe.setIngredient('S', Material.STICK);

        Bukkit.getServer().addRecipe(recipe);
    }

    /*
     *
     * Rezept für Netherite Pickaxe
     *
     * */
    public void UpgradetNetheritePX(JavaPlugin plugin) {
        ItemStack UpgradetNetheritePX = new ItemStack(Material.NETHERITE_PICKAXE);
        ItemMeta meta = UpgradetNetheritePX.getItemMeta();

        if (meta != null) {
            meta.setDisplayName("§fNetherite Pickaxe (Upgraded)");
            meta.addEnchant(Enchantment.UNBREAKING, 5, true);

            List<String> lore = new ArrayList<>();
            lore.add("§9Hat besondere Features");
            meta.setLore(lore);

            meta.getPersistentDataContainer().set(new NamespacedKey(plugin, "unique_pickaxe_id"), PersistentDataType.STRING, "upgraded_netherite_pickaxe");

            UpgradetNetheritePX.setItemMeta(meta);
        }

        NamespacedKey key = new NamespacedKey(plugin, "upgraded_netherite_pickaxe");
        ShapedRecipe recipe = new ShapedRecipe(key, UpgradetNetheritePX);
        recipe.shape("NNN", " S ", " S ");
        recipe.setIngredient('N', Material.NETHERITE_BLOCK);
        recipe.setIngredient('S', Material.STICK);

        Bukkit.getServer().addRecipe(recipe);
    }

    public void EggShop(JavaPlugin plugin) {
        ItemStack EggShop = new ItemStack(Material.BOOKSHELF);
        ItemMeta meta = EggShop.getItemMeta();

        if (meta != null) {
            meta.setDisplayName("§fEgg Shop");

            List<String> lore = new ArrayList<>();
            lore.add("§9Öffnet einen Eier Shop");
            meta.setLore(lore);

            meta.getPersistentDataContainer().set(new NamespacedKey(plugin, "unique_eggshop_id"), PersistentDataType.STRING, "eggshop");

            EggShop.setItemMeta(meta);
        }

        NamespacedKey key = new NamespacedKey(plugin, "eggshop");
        ShapedRecipe recipe = new ShapedRecipe(key, EggShop);
        recipe.shape("D D", "D D", "D D");
        recipe.setIngredient('D', Material.DIAMOND);

        Bukkit.getServer().addRecipe(recipe);
    }
}