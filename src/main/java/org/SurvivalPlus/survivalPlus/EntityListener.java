package org.SurvivalPlus.survivalPlus;

import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;

public class EntityListener implements Listener {

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        // Code für EntityDamageEvent
    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        // Code für EntityDeathEvent
    }
}
