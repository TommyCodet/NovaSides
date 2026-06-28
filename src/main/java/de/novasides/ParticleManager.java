package de.novasides;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;

public class ParticleManager {

    private final ConfigManager config;

    public ParticleManager(ConfigManager config) {
        this.config = config;
    }

    public void show(Player player, Location location) {

        if (!config.particlesEnabled()) {
            return;
        }

        player.spawnParticle(
                Particle.END_ROD,
                location,
                5,
                0.3,
                0.5,
                0.3,
                0
        );
    }
}
