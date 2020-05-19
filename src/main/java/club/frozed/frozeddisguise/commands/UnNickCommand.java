package club.frozed.frozeddisguise.commands;

import club.frozed.frozeddisguise.utils.Messages;
import net.haoshoku.nick.NickPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class UnNickCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }

        if (!sender.hasPermission("nick.use")) {
            sender.sendMessage("&cNo Permission.");
            return true;
        }
        if (args.length == 0) {
            Player player = (Player) sender;
            if (NickPlugin.getPlugin().getAPI().isNicked(player)) {
                NickPlugin.getPlugin().getAPI().unnick(player);
                NickPlugin.getPlugin().getAPI().resetGameProfileName(player);
                NickPlugin.getPlugin().getAPI().refreshPlayer(player);
                player.sendMessage(Messages.CC("&aSuccess! You are no longer have nick."));
            } else {
                player.sendMessage(Messages.CC("&cYou are not nicked."));
            }
            return true;
        }
        sender.sendMessage(Messages.CC("Usage: /unnick <name>"));
        return true;
    }

}
