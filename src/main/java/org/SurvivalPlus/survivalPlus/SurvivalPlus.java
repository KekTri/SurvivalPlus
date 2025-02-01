package org.SurvivalPlus.survivalPlus;


import org.bukkit.plugin.java.JavaPlugin;
import hilfsklassen.*;
import randomFruitEffekts.*;
import BossFights.*;


/*
*
* Main Klasse - Hier werden Prozesse bei Beginn und Ende des Server registriert.
*
* */


public final class SurvivalPlus extends JavaPlugin {
    private static SurvivalPlus instance;

    @Override
    public void onEnable() {
        //Erstellen einer Plugin Instanz
        instance = this;

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
        getServer().getPluginManager().registerEvents(new PickaxeBreakListener(this), this);
        getServer().getPluginManager().registerEvents(new EggShopListener(this), this);
        getServer().getPluginManager().registerEvents(new AppleHeartBonus(), this);
        getServer().getPluginManager().registerEvents(new trigger(), this);



        //Registrierung der Item Rezepte
        custom_recipes rezept = new custom_recipes();
        rezept.SteinDerWeißen();
        rezept.UpgradetNetheriteSword(this);
        rezept.UpgradetNetheritePX(this);
        rezept.EggShop(this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    public static SurvivalPlus getInstance() {
        return instance;
    }

}
