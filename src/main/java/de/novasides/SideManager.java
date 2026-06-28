package de.novasides;

import org.bukkit.Location;

public class SideManager {

    private final ConfigManager config;

    public SideManager(ConfigManager config) {
        this.config = config;
    }

    public SideType getSide(Location location) {
        return location.getBlockX() < config.getBorderX()
                ? SideType.PEACE
                : SideType.PVP;
    }

    public boolean isNearBorder(Location location) {
        return Math.abs(
                location.getX() - config.getBorderX()
        ) <= config.getParticleDistance();
    }
}
