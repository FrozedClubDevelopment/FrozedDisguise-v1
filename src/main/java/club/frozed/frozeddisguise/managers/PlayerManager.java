package club.frozed.frozeddisguise.managers;

import club.frozed.frozeddisguise.ranks.Ranks;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class PlayerManager {

    public static HashMap<Player, Ranks> rankData = new HashMap<>();

    public static Ranks getRank(Player player) {
        return rankData.get(player);
    }

    public static void setRank(Player player, Ranks ranks) {
        rankData.put(player, ranks);
    }

}
