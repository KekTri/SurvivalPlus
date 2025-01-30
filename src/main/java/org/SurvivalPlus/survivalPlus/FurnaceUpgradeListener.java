package org.SurvivalPlus.survivalPlus;

import org.bukkit.event.block.Action;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Furnace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.FurnaceSmeltEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

public class FurnaceUpgradeListener implements Listener {

    private final SurvivalPlus plugin;
    private final NamespacedKey diamondFurnaceKey;
    private final NamespacedKey netheriteFurnaceKey;

    public FurnaceUpgradeListener(SurvivalPlus plugin) {
        this.plugin = plugin;
        this.diamondFurnaceKey = new NamespacedKey(plugin, "diamond_furnace");
        this.netheriteFurnaceKey = new NamespacedKey(plugin, "netherite_furnace");
    }

    @EventHandler
    public void onFurnaceUpgrade(PlayerInteractEvent event) {
        Block block = event.getClickedBlock();
        if (event.getAction() != Action.LEFT_CLICK_BLOCK) return;
        if (block == null) return;

        ItemStack mainHand = event.getPlayer().getInventory().getItemInMainHand();

        if (mainHand.getType() == Material.DIAMOND && block.getType() == Material.FURNACE) {
            upgradeToDiamondFurnace(block, event);
        } else if (mainHand.getType() == Material.NETHERITE_INGOT && block.getType() == Material.FURNACE) {
            upgradeToNetheriteFurnace(block, event);
        }
    }

    private void upgradeToDiamondFurnace(Block block, PlayerInteractEvent event) {
        BlockState state = block.getState();
        if (state instanceof Furnace furnace) {
            // **Überprüfung, ob der Ofen bereits ein Diamond Furnace ist**
            if (furnace.getPersistentDataContainer().has(diamondFurnaceKey, PersistentDataType.INTEGER)) {
                event.getPlayer().sendMessage("❌ Dieser Ofen ist bereits ein Diamond Furnace!");
                return; // Abbruch des Upgrade-Prozesses
            }

            furnace.getPersistentDataContainer().set(diamondFurnaceKey, PersistentDataType.INTEGER, 1);
            furnace.setCustomName("💎 Diamond Furnace");
            furnace.update();
            startFurnaceSpeedTask(block); // Schmelzgeschwindigkeit starten
        }

        consumeItem(event.getPlayer().getInventory().getItemInMainHand());
        event.getPlayer().sendMessage("💎 Dein Furnace wurde auf einen Diamond Furnace verbessert! (2x Speed)");
    }

    private void upgradeToNetheriteFurnace(Block block, PlayerInteractEvent event) {
        BlockState state = block.getState();
        if (!(state instanceof Furnace furnace)) return;

        // **Überprüfung, ob der Ofen zuerst ein Diamond Furnace sein muss**
        if (!furnace.getPersistentDataContainer().has(diamondFurnaceKey, PersistentDataType.INTEGER)) {
            event.getPlayer().sendMessage("❌ Du musst den Furnace zuerst mit Diamant upgraden!");
            return; // Abbruch des Upgrade-Prozesses
        }

        // **Überprüfung, ob der Ofen bereits ein Netherite Furnace ist**
        if (furnace.getPersistentDataContainer().has(netheriteFurnaceKey, PersistentDataType.INTEGER)) {
            event.getPlayer().sendMessage("❌ Dieser Ofen ist bereits ein Netherite Furnace!");
            return; // Abbruch des Upgrade-Prozesses
        }

        furnace.getPersistentDataContainer().set(netheriteFurnaceKey, PersistentDataType.INTEGER, 1);
        furnace.setCustomName("🔥 Netherite Furnace");
        furnace.update();

        consumeItem(event.getPlayer().getInventory().getItemInMainHand());
        event.getPlayer().sendMessage("🔥 Dein Furnace wurde auf einen Netherite Furnace verbessert! (Doppelter Output)");
    }

    private void startFurnaceSpeedTask(Block block) {
        Bukkit.getScheduler().runTaskTimer(plugin, () -> {
            // Sicherstellen, dass der Block ein Furnace ist
            if (block.getType() != Material.FURNACE) return;

            BlockState state = block.getState();
            if (!(state instanceof Furnace furnace)) return;

            // Wenn der Furnace ein Diamond Furnace ist, beschleunige ihn
            if (furnace.getPersistentDataContainer().has(diamondFurnaceKey, PersistentDataType.INTEGER)) {
                // Wir erhöhen die Schmelzgeschwindigkeit, indem wir die CookTime verringern
                if (furnace.getCookTime() < furnace.getCookTimeTotal()) {
                    furnace.setCookTime((short) (furnace.getCookTime() + 1)); // Schneller schmelzen
                    furnace.update();
                }
            }
        }, 0L, 1L); // Task wird alle 1 Ticks ausgeführt
    }

    @EventHandler
    public void onFurnaceSmelt(FurnaceSmeltEvent event) {
        Block block = event.getBlock();
        BlockState state = block.getState();
        if (!(state instanceof Furnace furnace)) return;

        // Netherite Furnace: Doppelter Output
        if (furnace.getPersistentDataContainer().has(netheriteFurnaceKey, PersistentDataType.INTEGER)) {
            ItemStack result = event.getResult();
            result.setAmount(Math.min(result.getAmount() * 2, result.getMaxStackSize())); // Verdopple den Output
            event.setResult(result);
        }
    }

    private void consumeItem(ItemStack item) {
        item.setAmount(item.getAmount() - 1); // Reduziert die Anzahl des Items um 1
    }
}
