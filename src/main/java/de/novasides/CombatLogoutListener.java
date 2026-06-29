package de.novasides;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class CombatLogoutListener implements Listener {

    private final CombatManager combatManager;

    public CombatLogoutListener(
            CombatManager combatManager
    ) {
        this.combatManager = combatManager;
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {

        if (!combatManager.isInCombat(event.getPlayer())) {
            return;
        }

        event.getPlayer().setHealth(0.0);

        event.getPlayer().getWorld().strikeLightningEffect(
                event.getPlayer().getLocation()
        );
    }
}
