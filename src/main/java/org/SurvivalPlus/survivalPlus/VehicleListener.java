package org.SurvivalPlus.survivalPlus;

import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.vehicle.VehicleCreateEvent;
import org.bukkit.event.vehicle.VehicleDestroyEvent;

public class VehicleListener implements Listener {

    @EventHandler
    public void onVehicleCreate(VehicleCreateEvent event) {
        // Code für VehicleCreateEvent
    }

    @EventHandler
    public void onVehicleDestroy(VehicleDestroyEvent event) {
        // Code für VehicleDestroyEvent
    }
}
