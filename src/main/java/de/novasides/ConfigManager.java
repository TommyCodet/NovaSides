package de.novasides;

import org.bukkit.configuration.file.FileConfiguration;

public class ConfigManager {

    private final NovaSidesPlugin plugin;

    public ConfigManager(NovaSidesPlugin plugin) {
        this.plugin = plugin;
    }

    public int getBorderX() {
        return plugin.getConfig().getInt("border-x", 0);
    }

    public boolean particlesEnabled() {
        return plugin.getConfig().getBoolean("particles.enabled", true);
    }

    public int getParticleDistance() {
        return plugin.getConfig().getInt("particles.distance", 10);
    }

    public boolean actionBarEnabled() {
        return plugin.getConfig().getBoolean("actionbar.enabled", true);
    }
}
