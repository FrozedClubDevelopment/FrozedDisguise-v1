package club.frozed.frozeddisguise.commands;

import club.frozed.frozeddisguise.FrozedDisguise;
import club.frozed.frozeddisguise.utils.Messages;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ReloadCmd implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!sender.hasPermission("frozed.disguise.reload")) {
            sender.sendMessage(Messages.CC("&cYou don't have permission to do this!"));
            return true;
        }

        FrozedDisguise.getInstance().reloadConfig();
        FrozedDisguise.getInstance().saveConfig();
        sender.sendMessage(Messages.CC("&8[&bFrozed&lDisguise&8] &aSuccessfully reloaded config.yml!"));

        return true;
    }
}
