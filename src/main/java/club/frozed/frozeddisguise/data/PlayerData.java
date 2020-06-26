package club.frozed.frozeddisguise.data;

import net.haoshoku.nick.NickPlugin;
import org.bukkit.entity.Player;

public class PlayerData {

    /*
     * The Rank Selector feature is
     * still under development
     */

    public boolean isDisguised(Player player) {
        return NickPlugin.getPlugin().getAPI().isNicked(player);
    }

}
