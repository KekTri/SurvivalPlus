package org.SurvivalPlus.survivalPlus;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class SurvivalPlus extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        //Registrieren der Event Handler
        getServer().getPluginManager().registerEvents(new BlockListener(), this);
        getServer().getPluginManager().registerEvents(new EntityListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerListener(), this);
        getServer().getPluginManager().registerEvents(new InventoryListener(), this);
        getServer().getPluginManager().registerEvents(new ServerListener(), this);
        getServer().getPluginManager().registerEvents(new VehicleListener(), this);
        getServer().getPluginManager().registerEvents(new WorldListener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }


}
