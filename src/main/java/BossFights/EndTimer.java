package BossFights;

import net.md_5.bungee.api.ChatMessageType;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import net.md_5.bungee.api.chat.TextComponent;
import org.SurvivalPlus.survivalPlus.SurvivalPlus;

/*
*
* Timer bis das Event beendet ist.
*
* */


public class EndTimer {

    public void startCountdown(Player player, int x,int y,int z) {
        final int[] countdownTime = {120};

        new BukkitRunnable() {
            @Override
            public void run() {
                int minutes = countdownTime[0] / 60;
                int seconds = countdownTime[0] % 60;
                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("Überlebe: " + String.format("%02d:%02d", minutes, seconds)));
                countdownTime[0]--;
                if (countdownTime[0] < 0) {
                    cancel();
                    World world = Bukkit.getWorlds().get(0);
                    Location platformLocation = new Location(world, x, y, z);
                    new spawnWitherPlatform().killWitherAndRemoveObsidian(platformLocation);

                }
            }
        }.runTaskTimer(player.getServer().getPluginManager().getPlugin("SurvivalPlus"), 0, 20);
    }
}

