package de.novasides;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnCommand implements CommandExecutor {

    private final ConfigManager config;
    private final CombatManager combatManager;

    public SpawnCommand(
            ConfigManager config,
            CombatManager combatManager
    ) {
        this.config = config;
        this.combatManager = combatManager;
    }

    @Override
    public boolean onCommand(
            CommandSender sender,
            Command command,
            String label,
            String[] args
    ) {

        if (!(sender instanceof Player player)) {
            return true;
        }

        if (combatManager.isInCombat(player)) {

            player.sendMessage(
                    "§c⚔ Du kannst /spawn nicht im Kampf benutzen."
            );

            return true;
        }

        World world = Bukkit.getWorld(
                config.getSpawnWorld()
        );

        if (world == null) {
            player.sendMessage("§cSpawn-Welt nicht gefunden.");
            return true;
        }

        Location spawn = new Location(
                world,
                config.getSpawnX(),
                config.getSpawnY(),
                config.getSpawnZ(),
                config.getSpawnYaw(),
                config.getSpawnPitch()
        );

        player.teleport(spawn);

        player.sendMessage(
                "§aDu wurdest zum Spawn teleportiert."
        );

        return true;
    }
}
