package neptune.utils;

import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

// TODO: Rewrite to be actually be good!
public class ChatUtils implements MinecraftInterface {
    public static final String PREFIX = Formatting.GRAY + "[" + Formatting.BLUE + "Neptune" + Formatting.GRAY + "] ";
    public static final char COLOR_CHAR = '§';

    public static void message(String message) {
        mc.inGameHud.getChatHud().addMessage(Text.of(message));
    }

    public static void messagef(String message, Object... args) {
        message(message.formatted(args));
    }

    public static void messageBranding(String message) {
        message(PREFIX + message);
    }

    public static void messagefBranding(String message, Object... args) {
        message(PREFIX + message.formatted(args));
    }

    public static String translate(String message) {
        return message.replace('&', COLOR_CHAR);
    }

    public static String title(String message) {
        return message.charAt(0) + message.substring(1).toLowerCase();
    }
}