package club.frozed.frozeddisguise.commands;

import club.frozed.frozeddisguise.FrozedDisguise;
import club.frozed.frozeddisguise.utils.Messages;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import xyz.haoshoku.nick.NickPlugin;

public class UnDisguiseCmd implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }

        if (!sender.hasPermission("frozed.disguise")) {
            sender.sendMessage(Messages.CC(FrozedDisguise.getInstance().getConfig().getString("MESSAGES.NO-PERMISSION-TO-USE")));
            return true;
        }

        if (args.length == 0) {
            Player player = (Player) sender;
            if (NickPlugin.getPlugin().getAPI().isNicked(player)) {
                NickPlugin.getPlugin().getAPI().unnick(player);
                NickPlugin.getPlugin().getAPI().resetGameProfileName(player);
                NickPlugin.getPlugin().getAPI().refreshPlayer(player);
                sender.sendMessage(Messages.CC(FrozedDisguise.getInstance().getConfig().getString("MESSAGES.SUCCESSFULLY-UNDISGUISED")));
            } else {
                sender.sendMessage(Messages.CC(FrozedDisguise.getInstance().getConfig().getString("MESSAGES.NOT-DISGUISED")));
            }
            return true;
        }
        sender.sendMessage(Messages.CC("&cUsage: /undisguise <name>"));

        return true;
    }

}
