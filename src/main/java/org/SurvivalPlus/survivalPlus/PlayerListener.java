package org.SurvivalPlus.survivalPlus;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import hilfsklassen.server;

import java.util.HashSet;
import java.util.Set;

public class PlayerListener implements Listener {

    //Klassen Interne Variablen
    private final Set<Player> playersGreeted = new HashSet<>(); //Speichern der Begrüßten Spieler im Arbeitsspeicher
    private  Player player;


    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        // Code für PlayerJoinEvent
        player = event.getPlayer();
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        // Code für PlayerQuitEvent
        player = event.getPlayer();
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        //Methode zum Erkennen vom Playermovment
        player = event.getPlayer();

        /*
        *
        * Code zum Begrüßen eines Spielers. Der Spieler wird dabei begrüßt, wenn der eine in einen anderen
        * Spieler läuft.
        *
        * */
        event.getPlayer().getNearbyEntities(0.5, 0.5, 0.5).forEach(entity -> {
            if (entity instanceof Player) {
                Player otherPlayer = (Player) entity;

                if (!playersGreeted.contains(player) && !playersGreeted.contains(otherPlayer)) {
                    server nachricht = new server();
                    nachricht.sendeMessageAnSpieler(player.getName(), otherPlayer.getName()+" begrüßt dich");
                    nachricht.sendeMessageAnSpieler(otherPlayer.getName(), player.getName()+" begrüßt dich");

                    playersGreeted.add(player);
                    playersGreeted.add(otherPlayer);
                }
            }
        });


    }
}