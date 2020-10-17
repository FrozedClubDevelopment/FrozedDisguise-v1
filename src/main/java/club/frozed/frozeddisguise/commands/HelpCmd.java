package club.frozed.frozeddisguise.commands;

import club.frozed.frozeddisguise.FrozedDisguise;
import club.frozed.frozeddisguise.utils.Messages;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HelpCmd implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }

        if (!sender.hasPermission("frozed.disguise.help")) {
            sender.sendMessage(Messages.CC(FrozedDisguise.getInstance().getConfig().getString("MESSAGES.NO-PERMISSION-TO-USE")));
            return true;
        }

        Player player = (Player) sender;
        player.sendMessage(Messages.CC("&8&m--------------------------------------------------"));
        player.sendMessage(Messages.CC("&b&lFrozedDisguise &7- &fCommands Help"));
        player.sendMessage(Messages.CC(""));
        player.sendMessage(Messages.CC("&7 * &f/disguise"));
        player.sendMessage(Messages.CC("&7 * &f/disguise &b<name>"));
        player.sendMessage(Messages.CC("&7 * &f/disguise &b<name> &3<skin>"));
        player.sendMessage(Messages.CC(""));
        player.sendMessage(Messages.CC("&7 * &f/undisguise"));
        player.sendMessage(Messages.CC("&7 * &f/disguiserank"));
        player.sendMessage(Messages.CC("&7 * &f/disguiselist"));
        player.sendMessage(Messages.CC("&7 * &f/undisguiserank"));
        player.sendMessage(Messages.CC("&7 * &f/checkuuid &b<player>"));
        player.sendMessage(Messages.CC("&8&m--------------------------------------------------"));

        return true;
    }
}
