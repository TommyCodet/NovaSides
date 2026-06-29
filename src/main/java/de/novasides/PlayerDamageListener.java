package de.novasides;

import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Tameable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class PlayerDamageListener implements Listener {

    private final SideManager sideManager;
    private final CombatManager combatManager;

    public PlayerDamageListener(
            SideManager sideManager,
            CombatManager combatManager
    ) {
        this.sideManager = sideManager;
        this.combatManager = combatManager;
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {

        if (!(event.getEntity() instanceof Player victim)) {
            return;
        }

        Player attacker = null;

        if (event.getDamager() instanceof Player player) {
            attacker = player;
        }

        if (event.getDamager() instanceof Projectile projectile
                && projectile.getShooter() instanceof Player player) {
            attacker = player;
        }

        if (event.getDamager() instanceof Tameable tameable
                && tameable.getOwner() instanceof Player player) {
            attacker = player;
        }

        if (attacker == null) {
            return;
        }

        if (sideManager.getSide(victim.getLocation()) == SideType.PEACE) {

            event.setCancelled(true);

            attacker.sendMessage(
                    "§cPvP ist auf der Friedensseite deaktiviert."
            );

            return;
        }

        combatManager.tag(attacker);
        combatManager.tag(victim);
    }
}
