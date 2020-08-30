package club.frozed.frozeddisguise.hook;

import club.frozed.frozeddisguise.FrozedDisguise;
import club.frozed.frozeddisguise.managers.PlayerManager;
import lombok.RequiredArgsConstructor;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;
import xyz.haoshoku.nick.NickPlugin;

/**
 * Created by Elb1to
 * Project: FrozedDisguise
 * Date: 08/27/2020 @ 11:41
 */
@RequiredArgsConstructor
public class HookPlaceholderAPI extends PlaceholderExpansion {

    private FrozedDisguise plugin;

    public HookPlaceholderAPI(FrozedDisguise plugin) {
        this.plugin = plugin;
    }

    @Override
    public String getIdentifier() {
        return "frozed-disguise";
    }

    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public String getAuthor() {
        return "Elb1to";
    }

    @Override
    public String getVersion() {
        return "1.0";
    }

    @Override
    public String onPlaceholderRequest(Player player, String identifier) {

        // %frozed-disguise_player_name%
        if (identifier.equalsIgnoreCase("player_name")) {
            if (!NickPlugin.getPlugin().getAPI().isNicked(player)) {
                return NickPlugin.getPlugin().getAPI().getOriginalGameProfileName(player);
            }

            return NickPlugin.getPlugin().getAPI().getNickedName(player);
        }

        // %frozed-disguise_rank_name%
        if (identifier.equalsIgnoreCase("rank_name")) {
            if (PlayerManager.getRank(player) == null) {
                return "Default";
            }

            return PlayerManager.getRank(player).getName();
        }

        // %frozed-disguise_rank_prefix%
        if (identifier.equalsIgnoreCase("rank_prefix")) {
            if (PlayerManager.getRank(player).getPrefix() == null) {
                return "Default";
            }

            return PlayerManager.getRank(player).getPrefix();
        }

        return null;
    }
}
