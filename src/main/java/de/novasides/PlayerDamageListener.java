package de.novasides;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class PlayerDamageListener implements Listener {

    private final SideManager sideManager;

    public PlayerDamageListener(SideManager sideManager) {
        this.sideManager = sideManager;
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {

        if (!(event.getEntity() instanceof Player victim)) {
            return;
        }

        if (!(event.getDamager() instanceof Player attacker)) {
            return;
        }

        SideType victimSide = sideManager.getSide(victim.getLocation());

        if (victimSide == SideType.PEACE) {

            event.setCancelled(true);

            attacker.sendMessage("§cPvP ist auf der Friedensseite deaktiviert.");
        }
    }
}
