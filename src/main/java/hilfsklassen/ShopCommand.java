package hilfsklassen;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ShopCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            //öffne Shop GUI
            ShopGUI playerShopGui = new ShopGUI();
            playerShopGui.openGUI(player);
        } else {
            sender.sendMessage("404 Shop konnte nicht geöffnet werden");
        }
        return true;
    }
}
