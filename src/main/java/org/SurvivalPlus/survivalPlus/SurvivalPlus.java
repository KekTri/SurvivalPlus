package org.SurvivalPlus.survivalPlus;

import com.google.gson.Gson;
import org.bukkit.*;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityBreedEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

import java.util.List;
import java.util.Objects;
import hilfsklassen.*;



import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;




public final class SurvivalPlus extends JavaPlugin {
    @Override
    public void onEnable() {
        //Registriere Event Listener
        getServer().getPluginManager().registerEvents(new BlockListener(), this);
        getServer().getPluginManager().registerEvents(new EntityListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerListener(), this);
        getServer().getPluginManager().registerEvents(new InventoryListener(), this);
        getServer().getPluginManager().registerEvents(new ServerListener(), this);
        getServer().getPluginManager().registerEvents(new VehicleListener(), this);
        getServer().getPluginManager().registerEvents(new WorldListener(), this);
        getServer().getPluginManager().registerEvents(new FurnaceUpgradeListener(this), this);
        getServer().getPluginManager().registerEvents(new BlazeTeleportListener(this), this);
        getServer().getPluginManager().registerEvents(new VoidProtectionListener(this), this);

        //Registrierung der Item Rezepte
        custom_recipes rezept = new custom_recipes();
        rezept.SteinDerWeißen();
        rezept.UpgradetNetheriteSword(this);
        rezept.UpgradetNetheritePX(this);


    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

}
