package BossFights;

import net.md_5.bungee.api.ChatMessageType;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import net.md_5.bungee.api.chat.TextComponent;
import org.SurvivalPlus.survivalPlus.SurvivalPlus;
/*
*
* Timer der bis zum Start des Events runterläuft
*
* */
public class CountdownTimer {

    public void startCountdown(Player player) {
        final int[] countdownTime = {300};

        new BukkitRunnable() {
            @Override
            public void run() {
                int minutes = countdownTime[0] / 60;
                int seconds = countdownTime[0] % 60;
                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("Zeit bis zum Event: " + String.format("%02d:%02d", minutes, seconds)));
                countdownTime[0]--;

                if (countdownTime[0] < 0) {
                    cancel();
                    int koords[] = new spawnWitherPlatform().spawnWitherPlatform();
                    for (Player player : Bukkit.getServer().getOnlinePlayers()) {
                        new EndTimer().startCountdown(player, koords[0], koords[1],koords[2]);
                    }

                }
            }
        }.runTaskTimer(player.getServer().getPluginManager().getPlugin("SurvivalPlus"), 0, 20);
    }
}
