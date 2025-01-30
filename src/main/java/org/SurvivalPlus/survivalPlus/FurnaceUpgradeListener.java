package org.SurvivalPlus.survivalPlus;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Furnace;
import org.bukkit.block.BlastFurnace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.FurnaceInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

public class FurnaceUpgradeListener implements Listener {

    private final JavaPlugin plugin;
    private final NamespacedKey diamondFurnaceKey;
    private final NamespacedKey netheriteFurnaceKey;

    public FurnaceUpgradeListener(JavaPlugin plugin) {
        this.plugin = plugin;
        this.diamondFurnaceKey = new NamespacedKey(plugin, "diamond_furnace");
        this.netheriteFurnaceKey = new NamespacedKey(plugin, "netherite_furnace");
    }

    @EventHandler
    public void upgradeFurnace(PlayerInteractEvent event) {
        if (event.getAction() != Action.RIGHT_CLICK_BLOCK) return;

        Block block = event.getClickedBlock();
        if (block == null) return;

        ItemStack mainHand = event.getPlayer().getInventory().getItemInMainHand();

        if (mainHand.getType() == Material.DIAMOND && block.getType() == Material.FURNACE) {
            upgradeToDiamondFurnace(block, mainHand, event);
        } else if (mainHand.getType() == Material.NETHERITE_INGOT && block.getType() == Material.BLAST_FURNACE) {
            upgradeToNetheriteFurnace(block, mainHand, event);
        }
    }

    private void upgradeToDiamondFurnace(Block block, ItemStack item, PlayerInteractEvent event) {
        block.setType(Material.BLAST_FURNACE);
        BlockState state = block.getState();
        if (state instanceof BlastFurnace furnace) {
            furnace.getPersistentDataContainer().set(diamondFurnaceKey, PersistentDataType.INTEGER, 1);
            furnace.setCustomName("Diamond Furnace");
            furnace.update();
        }

        consumeItem(event.getPlayer().getInventory().getItemInMainHand());
        event.getPlayer().sendMessage("💎 Dein Furnace ist jetzt ein Diamond Furnace!");
        startFurnaceSpeedTask(block);
    }

    private void upgradeToNetheriteFurnace(Block block, ItemStack item, PlayerInteractEvent event) {
        BlockState state = block.getState();
        if (!(state instanceof BlastFurnace furnace)) return;

        // Prüfen, ob der Furnace vorher mit Diamant verbessert wurde
        if (!furnace.getPersistentDataContainer().has(diamondFurnaceKey, PersistentDataType.INTEGER)) {
            event.getPlayer().sendMessage("❌ Du musst den Furnace zuerst mit Diamant upgraden!");
            return;
        }

        furnace.getPersistentDataContainer().set(netheriteFurnaceKey, PersistentDataType.INTEGER, 1);
        furnace.setCustomName("Netherite Furnace");
        furnace.update();

        consumeItem(event.getPlayer().getInventory().getItemInMainHand());
        event.getPlayer().sendMessage("🔥 Dein Diamond Furnace ist jetzt ein Netherite Furnace mit doppeltem Output!");
        startDoubleOutputTask(block);
    }

    private void startFurnaceSpeedTask(Block block) {
        Bukkit.getScheduler().runTaskTimer(plugin, () -> {
            if (block.getType() != Material.BLAST_FURNACE) return;

            BlockState state = block.getState();
            if (!(state instanceof BlastFurnace furnace)) return;

            if (furnace.getPersistentDataContainer().has(diamondFurnaceKey, PersistentDataType.INTEGER)) {
                if (furnace.getCookTime() > 0) {
                    furnace.setCookTime((short) (furnace.getCookTime() + 1)); // Schneller schmelzen
                    furnace.update();
                }
            }
        }, 0L, 10L);
    }

    private void startDoubleOutputTask(Block block) {
        Bukkit.getScheduler().runTaskTimer(plugin, () -> {
            if (block.getType() != Material.BLAST_FURNACE) return;

            BlockState state = block.getState();
            if (!(state instanceof BlastFurnace furnace)) return;

            if (furnace.getPersistentDataContainer().has(netheriteFurnaceKey, PersistentDataType.INTEGER)) {
                FurnaceInventory inventory = ((Furnace) furnace).getInventory();
                ItemStack result = inventory.getResult();
                if (result != null && result.getType() != Material.AIR) {
                    result.setAmount(Math.min(result.getAmount() * 2, result.getMaxStackSize())); // Doppelter Output
                    inventory.setResult(result);
                }
            }
        }, 0L, 20L);
    }

    private void consumeItem(ItemStack item) {
        if (item.getAmount() > 1) {
            item.setAmount(item.getAmount() - 1);
        } else {
            item.setType(Material.AIR);
        }
    }
}
