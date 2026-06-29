package de.novasides;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeathListener implements Listener {

    private final SideManager sideManager;

    public PlayerDeathListener(SideManager sideManager) {
        this.sideManager = sideManager;
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {

        if (sideManager.getSide(event.getEntity().getLocation()) == SideType.PEACE) {

            event.setKeepInventory(true);
            event.getDrops().clear();
        }
    }
}
