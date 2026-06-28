package de.novasides;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;

public class BorderRenderer {

    private final ConfigManager config;

    public BorderRenderer(ConfigManager config) {
        this.config = config;
    }

    public void generate(World world, int minZ, int maxZ) {

        int x = config.getBorderX();

        for (int z = minZ; z <= maxZ; z++) {

            int y = world.getHighestBlockYAt(x, z);

            Block block = world.getBlockAt(x, y, z);

            block.setType(Material.BEDROCK, false);
        }

        Bukkit.getLogger().info("NovaSides Grenze generiert.");
    }
}
