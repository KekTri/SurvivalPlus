package org.SurvivalPlus.survivalPlus;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import hilfsklassen.server;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.HashSet;
import java.util.Set;

public class PlayerListener implements Listener {

    //Klassen Interne Variablen
    private final Set<Player> playersGreeted = new HashSet<>(); //Speichern der Begrüßten Spieler im Arbeitsspeicher
    private  Player player;


    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        player = event.getPlayer();
        player.sendMessage("Hallo!");
        player.playSound(event.getPlayer().getLocation(), Sound.BLOCK_BELL_USE,1.0F,1.0F);
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
    @EventHandler
    public void stickClimb(PlayerInteractEvent event)        {
        /*
         *  lässt spieler mit stick in main und offhand vertikal blöcke climben
         *
         *
         *
         * */
        ItemStack mainHand = event.getPlayer().getInventory().getItemInMainHand();
        ItemStack offHand = event.getPlayer().getInventory().getItemInOffHand();

        if(mainHand.getType() == Material.STICK && offHand.getType() == Material.STICK ){
            if(event.getAction()== Action.RIGHT_CLICK_BLOCK){
                Vector velocity = new Vector(0,0.4,0) ;
                event.getPlayer().setVelocity(velocity);
            }


        }


    }







}