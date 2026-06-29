package de.novasides;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerMoveListener implements Listener {

    private final SideManager sideManager;
    private final ConfigManager config;
    private final MessageManager messages;
    private final ActionBarManager actionBar;
    private final ParticleManager particles;
    private final TitleManager titles;
    private final CombatManager combatManager;

    private final Map<UUID, SideType> lastSide = new HashMap<>();

    public PlayerMoveListener(
            SideManager sideManager,
            ConfigManager config,
            MessageManager messages,
            ActionBarManager actionBar,
            ParticleManager particles,
            TitleManager titles,
            CombatManager combatManager
    ) {
        this.sideManager = sideManager;
        this.config = config;
        this.messages = messages;
        this.actionBar = actionBar;
        this.particles = particles;
        this.titles = titles;
        this.combatManager = combatManager;
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event) {

        if (event.getTo() == null) {
            return;
        }

        Location from = event.getFrom();
        Location to = event.getTo();

        if (from.getBlockX() == to.getBlockX()
                && from.getBlockY() == to.getBlockY()
                && from.getBlockZ() == to.getBlockZ()) {
            return;
        }

        Player player = event.getPlayer();

        SideType current = sideManager.getSide(to);

        // Combat-Spieler dürfen nicht in die Friedensseite
        if (current == SideType.PEACE
                && combatManager.isInCombat(player)) {

            event.setTo(from);

            player.sendMessage(
                    "§c⚔ Du bist noch im Kampf. Warte 15 Sekunden."
            );

            return;
        }

        SideType previous = lastSide.get(player.getUniqueId());

        if (sideManager.isNearBorder(to)) {

            if (config.actionBarEnabled()) {
                actionBar.send(player, current);
            }

            particles.show(player, to);
        }

        if (previous != null && previous != current) {

            if (current == SideType.PEACE) {
                player.sendMessage(messages.get("peace-enter"));
            } else {
                player.sendMessage(messages.get("pvp-enter"));
            }

            titles.send(player, current);
        }

        lastSide.put(player.getUniqueId(), current);
    }
}
