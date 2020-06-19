package club.frozed.frozeddisguise.commands;

import club.frozed.frozeddisguise.utils.Messages;
import net.haoshoku.nick.NickPlugin;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CheckUuidCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!sender.hasPermission("frozed.disguise.checkuuid")) {
            sender.sendMessage(Messages.CC("&cNo permission."));
            return true;
        }

        if (args.length == 0) {
            sender.sendMessage(Messages.CC("&cCorrect usage: /checkuuid <player>"));
            return true;
        }

        Player target = Bukkit.getPlayer(args[0]);
        if (args.length == 1) {
            sender.sendMessage(Messages.CC("&7&m--------------------------------------------------"));
            sender.sendMessage(Messages.CC("&b&lUUID CHECKER"));
            sender.sendMessage(Messages.CC(" "));
            sender.sendMessage(Messages.CC("&bPlayer&7: &f" + NickPlugin.getPlugin().getAPI().getOriginalGameProfileName(target)));
            sender.sendMessage(Messages.CC("&bUUID&7: &f" + target.getUniqueId()));
            sender.sendMessage(Messages.CC(" "));
            sender.sendMessage(Messages.CC("&bDisguised as&7: &f" + NickPlugin.getPlugin().getAPI().getNickedName(target)));
            sender.sendMessage(Messages.CC("&7&m--------------------------------------------------"));
        }

        return true;
    }
}
