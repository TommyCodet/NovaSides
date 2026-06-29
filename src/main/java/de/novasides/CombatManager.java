package de.novasides;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CombatManager {

    private static final long COMBAT_TIME = 15_000L;

    private final Map<UUID, Long> combatTagged = new HashMap<>();

    public void tag(Player player) {
        combatTagged.put(player.getUniqueId(), System.currentTimeMillis());
    }

    public boolean isInCombat(Player player) {
        Long last = combatTagged.get(player.getUniqueId());

        if (last == null) {
            return false;
        }

        if (System.currentTimeMillis() - last > COMBAT_TIME) {
            combatTagged.remove(player.getUniqueId());
            return false;
        }

        return true;
    }
}
