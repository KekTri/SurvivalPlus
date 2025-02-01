package randomFruitEffekts;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import java.util.Random;

public class AppleHeartBonus implements Listener {
/*
*
* Beim Essen eines Apfels kann man zu 1% ein weiteres Herz bekommen
*
* */
    @EventHandler
    public void onAppleEat(PlayerItemConsumeEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();
        if (item.getType().toString().contains("APPLE")) {
            Random random = new Random();
            if (random.nextInt(100) == 0) { //1% Chance
                double maxHealth = player.getMaxHealth();

                if (maxHealth < 30.0) {
                    player.setMaxHealth(maxHealth + 2.0);
                    player.setHealth(player.getHealth() + 2.0);
                    player.sendMessage("Der Apfel hat dir ein zusätzliches Herz geschenkt");
                }
            }
        }
    }
}
