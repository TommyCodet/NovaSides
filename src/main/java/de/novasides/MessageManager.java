package de.novasides;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class MessageManager {

    private final YamlConfiguration messages;

    public MessageManager(NovaSidesPlugin plugin) {
        File file = new File(plugin.getDataFolder(), "messages.yml");
        messages = YamlConfiguration.loadConfiguration(file);
    }

    public String get(String path) {
        return ChatColor.translateAlternateColorCodes(
                '&',
                messages.getString(path, path)
        );
    }
}
