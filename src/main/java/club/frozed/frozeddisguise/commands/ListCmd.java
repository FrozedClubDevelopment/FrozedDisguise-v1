package club.frozed.frozeddisguise.commands;

import club.frozed.frozeddisguise.utils.Messages;
import net.haoshoku.nick.NickPlugin;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.Map;
import java.util.UUID;

public class ListCmd implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!sender.hasPermission("frozed.disguise.list")) {
            sender.sendMessage(Messages.CC("&cNo permission."));
            return true;
        }

        if (NickPlugin.getPlugin().getAPI().getNickedPlayers().size() == 0) {
            sender.sendMessage(Messages.CC("&cNobody is disguised."));
            return true;
        }

        sender.sendMessage(Messages.CC("&7&m--------------------------------------------------"));
        sender.sendMessage(Messages.CC("&b&lDISGUISED PLAYERS"));
        sender.sendMessage(Messages.CC(" "));

        for (Map.Entry<UUID, String> entry : NickPlugin.getPlugin().getAPI().getNickedPlayers().entrySet()) {
            sender.sendMessage(Messages.CC("&8 * &b" + NickPlugin.getPlugin().getAPI().getOriginalGameProfileName(Bukkit.getPlayer(entry.getKey())) + "&7 is disguised as &f" + entry.getValue()));
        }

        sender.sendMessage(Messages.CC("&7&m--------------------------------------------------"));
        return true;
    }

}
