package club.frozed.frozeddisguise.utils;

import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.List;

public class Messages {

    public static String CC(String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }

    public static List<String> CC(List<String> msgs) {
        List<String> result = new ArrayList<>();
        for (String msg : msgs) {
            result.add(ChatColor.translateAlternateColorCodes('&', msg));
        }
        return result;
    }

}
