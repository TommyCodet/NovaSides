package de.novasides;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Sign;

public class BorderMarkerManager {

    private final ConfigManager config;

    public BorderMarkerManager(ConfigManager config) {
        this.config = config;
    }

    public void generate(World world, int minZ, int maxZ) {

        int x = config.getBorderX();
        int spacing = config.getMarkerSpacing();

        for (int z = minZ; z <= maxZ; z += spacing) {

            int y = world.getHighestBlockYAt(x, z);

            world.getBlockAt(x, y, z).setType(Material.BEDROCK);
            world.getBlockAt(x, y + 1, z).setType(Material.BEDROCK);
            world.getBlockAt(x, y + 2, z).setType(Material.BEDROCK);

            world.getBlockAt(x - 1, y + 1, z).setType(Material.LANTERN);
            world.getBlockAt(x + 1, y + 1, z).setType(Material.LANTERN);

            world.getBlockAt(x, y + 3, z).setType(Material.OAK_SIGN);

            if (world.getBlockAt(x, y + 3, z).getState() instanceof Sign sign) {

                sign.setLine(0, "NovaSides");
                sign.setLine(1, "PEACE");
                sign.setLine(2, "|");
                sign.setLine(3, "PVP");

                sign.update();
            }
        }
    }
}
