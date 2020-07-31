package club.frozed.frozeddisguise.commands;

import club.frozed.frozeddisguise.FrozedDisguise;
import club.frozed.frozeddisguise.managers.PlayerManager;
import club.frozed.frozeddisguise.utils.Messages;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import xyz.haoshoku.nick.NickPlugin;

public class CheckUuidCmd implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!sender.hasPermission("frozed.disguise.checkuuid")) {
            sender.sendMessage(Messages.CC(FrozedDisguise.getInstance().getConfig().getString("MESSAGES.NO-PERMISSION-TO-USE")));
            return true;
        }

        if (args.length == 0) {
            sender.sendMessage(Messages.CC("&cCorrect usage: /checkuuid <player>"));
            return true;
        }

        Player target = Bukkit.getPlayer(args[0]);
        if (args.length == 1) {
            if (target != null) {
                sender.sendMessage(Messages.CC("&7&m--------------------------------------------------"));
                sender.sendMessage(Messages.CC("&b&lUUID CHECKER"));
                sender.sendMessage(Messages.CC(" "));
                sender.sendMessage(Messages.CC("&bPlayer&7: &f" + NickPlugin.getPlugin().getAPI().getOriginalGameProfileName(target)));
                sender.sendMessage(Messages.CC("&bUUID&7: &f" + target.getUniqueId()));
                if (PlayerManager.rankData.containsKey(target)) {
                    sender.sendMessage(Messages.CC("&bDisguise Rank&7: &f" + PlayerManager.getRank(target).getName()));
                }
                if (NickPlugin.getPlugin().getAPI().isNicked(target)) {
                    sender.sendMessage(Messages.CC(" "));
                    sender.sendMessage(Messages.CC("&bDisguised as&7: &f" + NickPlugin.getPlugin().getAPI().getNickedName(target)));
                }
                sender.sendMessage(Messages.CC("&7&m--------------------------------------------------"));
            } else {
                sender.sendMessage(Messages.CC(FrozedDisguise.getInstance().getConfig().getString("MESSAGES.UUID-CHECK-PLAYER-NOT-FOUND")));
            }
        }

        return true;
    }
}
