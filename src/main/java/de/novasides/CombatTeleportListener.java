package de.novasides;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

public class CombatTeleportListener implements Listener {

    private final CombatManager combatManager;

    public CombatTeleportListener(
            CombatManager combatManager
    ) {
        this.combatManager = combatManager;
    }

    @EventHandler
    public void onPearl(PlayerTeleportEvent event) {

        if (event.getCause()
                != PlayerTeleportEvent.TeleportCause.ENDER_PEARL) {
            return;
        }

        Player player = event.getPlayer();

        if (combatManager.isInCombat(player)) {

            event.setCancelled(true);

            player.sendMessage(
                    "§c⚔ Keine Enderperlen im Kampf."
            );
        }
    }

    @EventHandler
    public void onChorus(PlayerItemConsumeEvent event) {

        if (event.getItem().getType()
                != Material.CHORUS_FRUIT) {
            return;
        }

        Player player = event.getPlayer();

        if (combatManager.isInCombat(player)) {

            event.setCancelled(true);

            player.sendMessage(
                    "§c⚔ Kein Chorus Fruit im Kampf."
            );
        }
    }
}
