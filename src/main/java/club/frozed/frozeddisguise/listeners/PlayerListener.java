package club.frozed.frozeddisguise.listeners;

import club.frozed.frozeddisguise.FrozedDisguise;
import club.frozed.frozeddisguise.managers.PlayerManager;
import club.frozed.frozeddisguise.ranks.Ranks;
import club.frozed.frozeddisguise.utils.Messages;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import xyz.haoshoku.nick.NickPlugin;

public class PlayerListener implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerChat(AsyncPlayerChatEvent event) {

        Player player = event.getPlayer();
        String message = event.getMessage();
        Ranks rank = PlayerManager.getRank(player);

        if (FrozedDisguise.getInstance().getConfig().getBoolean("BOOLEANS.CHAT-FORMAT")) {
            if (FrozedDisguise.getRankManager().getRanks().contains(rank)) {
                event.setFormat(Messages.CC(FrozedDisguise.getInstance().getConfig().getString("BOOLEANS.FORMAT-WITH-RANK")
                        .replaceAll("<rank>", rank.getPrefix())
                        .replaceAll("<player>", player.getName())
                        .replaceAll("<msg>", message
                                .replaceAll("%", "%%")
                                .replaceAll("\\$", "\\\\\\$")))
                );
            } else if (NickPlugin.getPlugin().getAPI().isNicked(player.getPlayer())) {
                event.setFormat(Messages.CC(FrozedDisguise.getInstance().getConfig().getString("BOOLEANS.FORMAT-WITHOUT-RANK")
                        .replaceAll("<player>", player.getName())
                        .replaceAll("<msg>", message.replaceAll("%", "%%").replaceAll("\\$", "\\\\\\$")))
                );
            }
        }
    }
}
