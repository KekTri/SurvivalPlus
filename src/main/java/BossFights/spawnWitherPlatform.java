package BossFights;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wither;
import org.bukkit.util.Vector;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;
import java.util.Random;


public class spawnWitherPlatform {

/*
*
* Klasse, die Wither Arena Spawnt und Entfernt
*
* */

    public int[] spawnWitherPlatform() {

        Bukkit.broadcastMessage("§c§l[Achtung]§f§l Spieler wurden teleportiert");
        Random random = new Random();
        World world = Bukkit.getWorlds().get(0);
        int x_platform = random.nextInt(2000) - 1000;
        int z_platform = random.nextInt(2000) - 1000;

        int y_platform = world.getHighestBlockYAt(x_platform, z_platform) + 50;
        //Boden
        for (int dx = -10; dx < 10; dx++) {
            for (int dz = -10; dz < 10; dz++) {
                world.getBlockAt(x_platform + dx, y_platform, z_platform + dz).setType(Material.OBSIDIAN);
            }
        }
        //Wand
        for (int dx = -10; dx <= 10; dx++) {
            for (int dz = -10; dz <= 10; dz++) {
                if (dx == -10 || dx == 9 || dz == -10 || dz == 9) {
                    for (int dy = 1; dy <= 30; dy++) { // Setzt die Wandhöhe auf 30
                        world.getBlockAt(x_platform + dx, y_platform + dy, z_platform + dz).setType(Material.OBSIDIAN);
                    }
                }
            }
        }

        new BukkitRunnable() {
            @Override
            public void run() {
                spawnWither(new Location(world, x_platform, y_platform+5, z_platform));
                spawnWither(new Location(world, x_platform+2, y_platform+10, z_platform+2));
                List<Player> players = (List<Player>) Bukkit.getOnlinePlayers();
                for (Player player : players) {
                    player.teleport(new Vector(x_platform, y_platform + 1, z_platform).toLocation(world));
                }
            }
        }.runTaskLater(Bukkit.getPluginManager().getPlugin("SurvivalPlus"), 20L);
        return new int[] {x_platform, y_platform, z_platform};
    }

    public void spawnWither(Location location) {
        if (location.getWorld() != null) {
            Wither wither = (Wither) location.getWorld().spawnEntity(location, EntityType.WITHER);
            wither.setCustomName("Wither Magier");
        }
    }

    public void killWitherAndRemoveObsidian(Location platformLocation) {
        //Wither Entfernen
        World world = platformLocation.getWorld();
        if (world != null) {
            List<Wither> withers = (List<Wither>) world.getEntitiesByClass(Wither.class);
            for (Wither wither : withers) {
                if (wither.getLocation().distance(platformLocation) < 50) {
                    wither.remove();
                }
            }
            //Boden Entfernen
            int x = platformLocation.getBlockX();
            int y = platformLocation.getBlockY();
            int z = platformLocation.getBlockZ();
            for (int dx = -10; dx < 10; dx++) {
                for (int dz = -10; dz < 10; dz++) {
                    world.getBlockAt(x + dx, y, z + dz).setType(Material.AIR);
                }
            }
            //Wand Entfernen
            for (int dx = -10; dx <= 10; dx++) {
                for (int dz = -10; dz <= 10; dz++) {
                    if (dx == -10 || dx == 9 || dz == -10 || dz == 9) {
                        for (int dy = 1; dy <= 30; dy++) {
                            world.getBlockAt(x + dx, y + dy, z + dz).setType(Material.AIR);
                        }
                    }
                }
            }
            Bukkit.broadcastMessage("§a§l[Succes]§f§l Du hast überlebt");
        }
    }
}

