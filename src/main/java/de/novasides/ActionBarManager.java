package de.novasides;

import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;

public class ActionBarManager {

    private final MessageManager messages;

    public ActionBarManager(MessageManager messages) {
        this.messages = messages;
    }

    public void send(Player player, SideType side) {

        String text = side == SideType.PEACE
                ? messages.get("peace-actionbar")
                : messages.get("pvp-actionbar");

        player.sendActionBar(Component.text(text));
    }
}
