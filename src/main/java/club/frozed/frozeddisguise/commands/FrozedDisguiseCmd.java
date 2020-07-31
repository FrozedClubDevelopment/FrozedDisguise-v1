package club.frozed.frozeddisguise.commands;

import club.frozed.frozeddisguise.FrozedDisguise;
import club.frozed.frozeddisguise.utils.Messages;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class FrozedDisguiseCmd implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        sender.sendMessage(Messages.CC("&8&m--------------------------------------------------"));
        sender.sendMessage(Messages.CC("&b&lFrozedDisguise &7- &fv" + FrozedDisguise.getInstance().getDescription().getVersion()));
        sender.sendMessage(Messages.CC("&bDeveloped by&7: &f" + FrozedDisguise.getInstance().getDescription().getAuthors()));
        sender.sendMessage(Messages.CC(""));
        sender.sendMessage(Messages.CC("&7&oMore information &8- &f/disguisehelp"));
        sender.sendMessage(Messages.CC("&8&m--------------------------------------------------"));

        return true;
    }
}
