package me.neptune.util.minecraft;

import me.neptune.Neptune;
import me.neptune.api.InstanceHolder;
import net.minecraft.text.Text;

public class ChatUtil implements InstanceHolder {

    /**
     * Sends a message in the in-game Chat.
     * @param message the message to send.
     */
    public static void sendMessage(String message)
    {
        sendMessage(message, false);
    }

    /**
     * Sends a message in the in-game Chat.
     * @param message the message to send.
     * @param debug if you want to prefix the message with "[DEBUG]".
     */
    public static void sendMessage(String message, boolean debug)
    {
        mc.inGameHud
                .getChatHud()
                .addMessage(Text.of(
                        (debug ? "§e[DEBUG] §9[" + Neptune.NAME + "]§r " : "§9[" + Neptune.NAME + "]§r ") + message
                        )
                );
    }
}
