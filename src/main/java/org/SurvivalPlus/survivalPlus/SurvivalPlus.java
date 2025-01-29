package org.SurvivalPlus.survivalPlus;

import org.bukkit.Instrument;
import org.bukkit.Material;
import org.bukkit.Note;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityBreedEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemType;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.Objects;


public final class SurvivalPlus extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        event.getPlayer().sendMessage("Hallo!");
        event.getPlayer().playSound(event.getPlayer().getLocation(),Sound.BLOCK_BELL_USE,1.0F,1.0F);
    }
    @EventHandler
    public void onBreed(EntityBreedEvent event) {
        if (event.getEntity().getType() == EntityType.PIG) {
            event.getEntity().setGlowing(true);
        }
    }
    @EventHandler
    public void stickJump(PlayerInteractEvent event)        {
        ItemStack mainHand = event.getPlayer().getInventory().getItemInMainHand();
        ItemStack offHand = event.getPlayer().getInventory().getItemInOffHand();

        if(mainHand.getType() == Material.STICK && offHand.getType() == Material.STICK ){
            if(event.getAction()== Action.LEFT_CLICK_BLOCK){
                Vector velocity = Objects.requireNonNull(event.getClickedBlock()).getLocation().toVector().subtract(event.getPlayer().getLocation().toVector()).multiply(3);
                event.getPlayer().setVelocity(velocity);


            }


           }


        }




}
