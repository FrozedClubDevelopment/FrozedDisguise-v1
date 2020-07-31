package club.frozed.frozeddisguise.utils;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.stream.Collectors;

public class Messages {
    public static String CC(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    public static List<String> CC(List<String> list) {
        return list.stream().map(Messages::CC).collect(Collectors.toList());
    }

    public static void sendMessageToPlayer(String message, Player player) {
        player.sendMessage(Messages.CC(message));
    }
}
