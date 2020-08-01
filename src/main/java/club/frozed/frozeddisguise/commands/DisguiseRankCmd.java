package club.frozed.frozeddisguise.commands;

import club.frozed.frozeddisguise.FrozedDisguise;
import club.frozed.frozeddisguise.ranks.RanksMenu;
import club.frozed.frozeddisguise.utils.Messages;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import xyz.haoshoku.nick.NickPlugin;

public class DisguiseRankCmd implements CommandExecutor, Listener {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!sender.hasPermission("frozed.disguise.ranks")) {
            sender.sendMessage(Messages.CC(FrozedDisguise.getInstance().getConfig().getString("MESSAGES.NO-PERMISSION-TO-USE")));
            return true;
        }

        if (!(sender instanceof Player)) {
            return true;
        }

        Player player = (Player) sender;
        if (FrozedDisguise.getInstance().getConfig().getBoolean("BOOLEANS.ALLOW-DISGUISE-RANK-WITHOUT-BEING-DISGUISED")) {
            new RanksMenu().openMenu(player);
        } else if (NickPlugin.getPlugin().getAPI().isNicked(player)) {
            new RanksMenu().openMenu(player);
        } else {
            player.sendMessage(Messages.CC(FrozedDisguise.getInstance().getConfig().getString("MESSAGES.DISGUISE-REQUIRED-FOR-RANK")));
        }

        return true;
    }
}
