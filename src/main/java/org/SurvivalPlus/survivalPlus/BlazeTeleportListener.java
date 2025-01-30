package org.SurvivalPlus.survivalPlus;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Blaze;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.HashMap;
import java.util.Random;
import java.util.UUID;

/*
*
* Klasse zum random Teleportieren zum Einschlagpunkt der Feuerkugel einer Blaze.
*
* */

public class BlazeTeleportListener implements Listener {
    private final JavaPlugin plugin;
    private final Random random = new Random();
    private final HashMap<UUID, Blaze> blazeFireballs = new HashMap<>();

    public BlazeTeleportListener(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onProjectileLaunch(ProjectileLaunchEvent event) {
        Projectile projectile = event.getEntity();

        if (projectile instanceof Fireball fireball) {
            if (fireball.getShooter() instanceof Blaze blaze) {
                blazeFireballs.put(fireball.getUniqueId(), blaze);
            }
        }
    }

    @EventHandler
    public void onProjectileHit(ProjectileHitEvent event) {
        Projectile projectile = event.getEntity();
        if (projectile instanceof Fireball fireball) {
            Blaze blaze = blazeFireballs.get(fireball.getUniqueId());
            if (blaze != null) {
                if (random.nextDouble() < 0.1) { //10% teleportationswahrscheinlichkeit
                    Location hitLocation = fireball.getLocation();

                    Bukkit.getScheduler().runTask(plugin, () -> {
                        blaze.teleport(hitLocation);
                        blaze.getWorld().playSound(hitLocation, org.bukkit.Sound.ENTITY_ENDERMAN_TELEPORT, 1.0f, 1.0f); // Teleportationssound
                    });
                    blazeFireballs.remove(fireball.getUniqueId());
                }
            }
        }
    }
}
