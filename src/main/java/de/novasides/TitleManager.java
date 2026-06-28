package de.novasides;

import org.bukkit.entity.Player;

public class TitleManager {

    private final MessageManager messages;

    public TitleManager(MessageManager messages) {
        this.messages = messages;
    }

    public void send(Player player, SideType side) {

        if (side == SideType.PEACE) {

            player.sendTitle(
                    messages.get("peace-title"),
                    messages.get("peace-subtitle"),
                    10,
                    60,
                    20
            );

        } else {

            player.sendTitle(
                    messages.get("pvp-title"),
                    messages.get("pvp-subtitle"),
                    10,
                    60,
                    20
            );
        }
    }
}
