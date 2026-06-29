package de.novasides;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;

public class ExplosionListener implements Listener {

    private final SideManager sideManager;

    public ExplosionListener(SideManager sideManager) {
        this.sideManager = sideManager;
    }

    @EventHandler
    public void onExplode(EntityExplodeEvent event) {

        if (sideManager.getSide(event.getLocation()) == SideType.PEACE) {
            event.setCancelled(true);
        }
    }
}
