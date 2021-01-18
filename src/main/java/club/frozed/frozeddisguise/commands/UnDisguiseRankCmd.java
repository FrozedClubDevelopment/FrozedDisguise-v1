package club.frozed.frozeddisguise.commands;

import club.frozed.frozeddisguise.FrozedDisguise;
import club.frozed.frozeddisguise.managers.PlayerManager;
import club.frozed.frozeddisguise.utils.Messages;
import com.nametagedit.plugin.NametagEdit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class UnDisguiseRankCmd implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) return true;

        if (!sender.hasPermission("frozed.disguise")) {
            sender.sendMessage(Messages.CC(FrozedDisguise.getInstance().getConfig().getString("MESSAGES.NO-PERMISSION-TO-USE")));
            return true;
        }

        Player player = (Player) sender;
        if (PlayerManager.rankData.containsKey(player)) {
            PlayerManager.rankData.remove(player);
            /*if (FrozedDisguise.getInstance().getConfig().getBoolean("BOOLEANS.TABLIST-NAME-COLOR")) {
                player.setPlayerListName(Messages.CC(FrozedDisguise.getInstance().getConfig().getString("BOOLEANS.DEFAULT-TABLIST-NAME-COLOR")) + player.getName());
            }*/
            player.setDisplayName(Messages.CC(FrozedDisguise.getInstance().getConfig().getString("BOOLEANS.DEFAULT-NAMETAG-COLOR")) + player.getName());
            player.setPlayerListName(Messages.CC(FrozedDisguise.getInstance().getConfig().getString("BOOLEANS.DEFAULT-TABLIST-NAME-COLOR")) + player.getName());
            NametagEdit.getApi().setPrefix(player, Messages.CC(FrozedDisguise.getInstance().getConfig().getString("BOOLEANS.DEFAULT-NAMETAG-COLOR")));
            //NametagEdit.getApi().reloadNametag(player);

            player.sendMessage(Messages.CC(FrozedDisguise.getInstance().getConfig().getString("MESSAGES.DISGUISE-RANK-SUCCESSFULLY-REMOVED")));
        } else {
            player.sendMessage(Messages.CC(FrozedDisguise.getInstance().getConfig().getString("MESSAGES.NO-DISGUISE-RANK-ACTIVE")));
        }

        return true;
    }
}
