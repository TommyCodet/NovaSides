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
        saveResource("messages.yml", false);

        configManager = new ConfigManager(this);
        messageManager = new MessageManager(this);
        sideManager = new SideManager(configManager);

        getServer().getPluginManager().registerEvents(
                new PlayerMoveListener(
                        sideManager,
                        messageManager,
                        new ActionBarManager(messageManager),
                        new ParticleManager(configManager),
                        new TitleManager(messageManager)
                ),
                this
        );

        World world = getServer().getWorlds().get(0);

        if (configManager.borderEnabled()) {

            BorderRenderer renderer = new BorderRenderer(configManager);

            renderer.generate(
                    world,
                    configManager.getMinZ(),
                    configManager.getMaxZ()
            );
        }

        if (configManager.markersEnabled()) {

            BorderMarkerManager markerManager =
                    new BorderMarkerManager(configManager);

            markerManager.generate(
                    world,
                    configManager.getMinZ(),
                    configManager.getMaxZ()
            );
        }

        getLogger().info("================================");
        getLogger().info("NovaSides aktiviert");
        getLogger().info("Grenze X = " + configManager.getBorderX());
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
