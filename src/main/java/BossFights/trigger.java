package BossFights;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;

import java.util.Random;

/*
*
* Trigger ist das Essen eines Pufferfishes. (Aktuell)
*
* */

public class trigger implements Listener {
    @EventHandler
    public void onPufferfishEat(PlayerItemConsumeEvent event) {
        if (event.getItem().getType() == Material.PUFFERFISH) {
            Random random = new Random();
            if (random.nextInt(5) == 0) {
                for (Player player : Bukkit.getServer().getOnlinePlayers()) {
                    new CountdownTimer().startCountdown(player);
                    Bukkit.broadcastMessage("§c§l[Achtung]§f§l Ein einigermaßen zufälliges Witherevent wurde gestartet");
                }
            }
        }
    }
}


