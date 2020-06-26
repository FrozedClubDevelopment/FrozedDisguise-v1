package club.frozed.frozeddisguise.commands;

import club.frozed.frozeddisguise.utils.Messages;
import net.haoshoku.nick.NickPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class UnDisguiseCmd implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }

        if (!sender.hasPermission("frozed.disguise")) {
            sender.sendMessage(Messages.CC("&cNo permission."));
            return true;
        }

        if (args.length == 0) {
            Player player = (Player) sender;
            if (NickPlugin.getPlugin().getAPI().isNicked(player)) {
                NickPlugin.getPlugin().getAPI().unnick(player);
                NickPlugin.getPlugin().getAPI().resetGameProfileName(player);
                NickPlugin.getPlugin().getAPI().refreshPlayer(player);
                player.sendMessage(Messages.CC("&aSuccess! You are no longer disguised."));
            } else {
                player.sendMessage(Messages.CC("&cYou are not disguised."));
            }
            return true;
        }
        sender.sendMessage(Messages.CC("Usage: /undisguise <name>"));

        return true;
    }

}
