package hilfsklassen;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/*
*
* In der Serverklasse sollen Operationen des Servers gegenüber dem Spieler sein. Beispiel: Nachricht, Scoreboard...
*
* */

public class server {


    /*
    *
    * Methode zum Senden von Nachrichten vom Server an Spieler.
    * Beispielaufruf im PlayerListener Greeting System
    *
    * */
    public static void sendeMessageAnSpieler(String spielerName, String msg) {
        Player player = Bukkit.getPlayer(spielerName);
        if (player != null && player.isOnline()) {
            player.sendMessage(msg);
        } else {
            System.out.println("ERROR : Spieler " + spielerName + " ist nicht online oder existiert nicht");
        }
    }
}
