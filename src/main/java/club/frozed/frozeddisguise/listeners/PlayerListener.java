package club.frozed.frozeddisguise.listeners;

import club.frozed.frozeddisguise.FrozedDisguise;
import club.frozed.frozeddisguise.data.PlayerData;
import club.frozed.frozeddisguise.data.Ranks;
import club.frozed.frozeddisguise.utils.Messages;
import net.haoshoku.nick.NickPlugin;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerListener implements Listener {

    /*
     * The Rank Selector feature is
     * still under development
     */

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        NickPlugin.getPlugin().getAPI().setSkin(player, String.valueOf(player));
        NickPlugin.getPlugin().getAPI().refreshPlayer(player);
    }

    /*@EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        Player player = e.getPlayer();
        Ranks ranks = FrozedDisguise.getInstance().getRanks();
        PlayerData playerData = FrozedDisguise.getInstance().getPlayerData();

        if (playerData.isDisguised(player)) {
            e.setFormat(Messages.CC(ranks.getPrefix() + player.getName() + ranks.getSuffix() + "&7: " + ranks.getChatColor() + e.getMessage()));
        }
    }*/
}
