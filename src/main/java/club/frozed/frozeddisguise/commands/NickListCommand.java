package club.frozed.frozeddisguise.commands;

import club.frozed.frozeddisguise.utils.Messages;
import net.haoshoku.nick.NickPlugin;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.Map;
import java.util.UUID;

public class NickListCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!sender.hasPermission("nick.nicklist")) {
            sender.sendMessage("&cNo Permission.");
            return true;
        }
        if (NickPlugin.getPlugin().getAPI().getNickedPlayers().size() == 0) {
            sender.sendMessage(Messages.CC("&cNo nicked players"));
            return true;
        }
        sender.sendMessage(Messages.CC("&7&m---------------------------------------------"));
        sender.sendMessage(Messages.CC(" &6Nicked Players:"));
        for (Map.Entry<UUID, String> entry : NickPlugin.getPlugin().getAPI().getNickedPlayers().entrySet()) {
            sender.sendMessage(Messages.CC("  &e" + NickPlugin.getPlugin().getAPI().getOriginalGameProfileName(Bukkit.getPlayer(entry.getKey())) + " -> &f" + entry.getValue()));
        }
        sender.sendMessage(Messages.CC("&7&m---------------------------------------------"));
        return true;
    }

}
