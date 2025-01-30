package org.SurvivalPlus.survivalPlus;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Blaze;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.Fireball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.Random;



public class ProjectileLaunchListener {
    @EventHandler
    public void onProjectileLaunch(ProjectileLaunchEvent event) {
        if (event.getEntity() instanceof Fireball && event.getEntity().getShooter() instanceof Blaze) {
            Random random = new Random();
            if (random.nextDouble() < 0.5) { // 0.5% Chance = 0.0005
                Fireball fireball = (Fireball) event.getEntity();
                Blaze blaze = (Blaze) fireball.getShooter();
                Location targetLocation = fireball.getLocation();

                // Entferne den Feuerball und ersetze ihn durch eine Enderperle
                fireball.remove();
                EnderPearl enderPearl = blaze.launchProjectile(EnderPearl.class);
                enderPearl.setVelocity(fireball.getVelocity());

                // Teleportiere die Blaze zur Enderperle
                Bukkit.getScheduler().runTaskLater((Plugin) this, () -> blaze.teleport(targetLocation), 1L);
            }
        }
    }
}
