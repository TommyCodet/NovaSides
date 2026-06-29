package de.novasides;

import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

public final class NovaSidesPlugin extends JavaPlugin {

    private static NovaSidesPlugin instance;

    private ConfigManager configManager;
    private MessageManager messageManager;
    private SideManager sideManager;

    @Override
    public void onEnable() {

        instance = this;

        saveDefaultConfig();

        if (!getDataFolder().exists()) {
            getDataFolder().mkdirs();
        }

        saveResource("messages.yml", false);

        configManager = new ConfigManager(this);
        messageManager = new MessageManager(this);
        sideManager = new SideManager(configManager);

        // Bewegung / Grenze
        getServer().getPluginManager().registerEvents(
                new PlayerMoveListener(
                        sideManager,
                        configManager,
                        messageManager,
                        new ActionBarManager(messageManager),
                        new ParticleManager(configManager),
                        new TitleManager(messageManager)
                ),
                this
        );

        // PvP-Regeln
        getServer().getPluginManager().registerEvents(
                new PlayerDamageListener(sideManager),
                this
        );

        World world = getServer().getWorlds().get(0);

        if (configManager.borderEnabled()) {

            new BorderRenderer(configManager).generate(
                    world,
                    configManager.getMinZ(),
                    configManager.getMaxZ()
            );
        }

        if (configManager.markersEnabled()) {

            new BorderMarkerManager(configManager).generate(
                    world,
                    configManager.getMinZ(),
                    configManager.getMaxZ()
            );
        }

        getLogger().info("================================");
        getLogger().info("NovaSides aktiviert");
        getLogger().info("Border X: " + configManager.getBorderX());
        getLogger().info("================================");
    }

    @Override
    public void onDisable() {
        getLogger().info("NovaSides deaktiviert");
    }

    public static NovaSidesPlugin getInstance() {
        return instance;
    }

    public ConfigManager getConfigManager() {
        return configManager;
    }

    public MessageManager getMessageManager() {
        return messageManager;
    }

    public SideManager getSideManager() {
        return sideManager;
    }
}
