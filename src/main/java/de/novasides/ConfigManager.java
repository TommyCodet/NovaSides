package de.novasides;

public class ConfigManager {

    private final NovaSidesPlugin plugin;

    public ConfigManager(NovaSidesPlugin plugin) {
        this.plugin = plugin;
    }

    public int getBorderX() {
        return plugin.getConfig().getInt("border-x", 0);
    }

    public boolean borderEnabled() {
        return plugin.getConfig().getBoolean("border.enabled", true);
    }

    public boolean markersEnabled() {
        return plugin.getConfig().getBoolean("markers.enabled", true);
    }

    public int getMarkerSpacing() {
        return plugin.getConfig().getInt("markers.spacing", 500);
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

    public int getMinZ() {
        return plugin.getConfig().getInt("generation.min-z", -5000);
    }

    public int getMaxZ() {
        return plugin.getConfig().getInt("generation.max-z", 5000);
    }

    public String getSpawnWorld() {
        return plugin.getConfig().getString(
                "spawn.world",
                "world"
        );
    }

    public double getSpawnX() {
        return plugin.getConfig().getDouble("spawn.x", 0);
    }

    public double getSpawnY() {
        return plugin.getConfig().getDouble("spawn.y", 100);
    }

    public double getSpawnZ() {
        return plugin.getConfig().getDouble("spawn.z", 0);
    }

    public float getSpawnYaw() {
        return (float) plugin.getConfig().getDouble(
                "spawn.yaw",
                0
        );
    }

    public float getSpawnPitch() {
        return (float) plugin.getConfig().getDouble(
                "spawn.pitch",
                0
        );
    }
}
