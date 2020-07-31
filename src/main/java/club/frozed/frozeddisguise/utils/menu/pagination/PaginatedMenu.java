package club.frozed.frozeddisguise.utils.menu.pagination;

import club.frozed.frozeddisguise.utils.menu.Button;
import club.frozed.frozeddisguise.utils.menu.Menu;
import lombok.Getter;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public abstract class PaginatedMenu extends Menu {

    @Getter private int page = 1;

    {
        setUpdateAfterClick(false);
    }

    @Override
    public String getTitle(Player player) {
        return getPrePaginatedTitle(player) + " - " + page + "/" + getPages(player);
    }

    public void modPage(Player player, int mod) {
        page += mod;
        getButtons().clear();
        openMenu(player);
    }

    public int getPages(Player player) {
        int buttonAmount = getAllPagesButtons(player).size();

        if (buttonAmount == 0) {
            return 1;
        }

        return (int) Math.ceil(buttonAmount / (double) getMaxItemsPerPage(player));
    }

    @Override
    public Map<Integer, Button> getButtons(Player player) {
        int minIndex = (int) ((double) (page - 1) * getMaxItemsPerPage(player));
        int maxIndex = (int) ((double) (page) * getMaxItemsPerPage(player));

        HashMap<Integer, Button> buttons = new HashMap<>();

        buttons.put(0, new PageButton(-1, this));
        buttons.put(8, new PageButton(1, this));

        for (Map.Entry<Integer, Button> entry : getAllPagesButtons(player).entrySet()) {
            int ind = entry.getKey();

            if (ind >= minIndex && ind < maxIndex) {
                ind -= (int) ((double) (getMaxItemsPerPage(player)) * (page - 1)) - 9;
                buttons.put(ind, entry.getValue());
            }
        }

        Map<Integer, Button> global = getGlobalButtons(player);

        if (global != null) {
            for (Map.Entry<Integer, Button> gent : global.entrySet()) {
                buttons.put(gent.getKey(), gent.getValue());
            }
        }

        return buttons;
    }

    public int getMaxItemsPerPage(Player player) {
        return 18;
    }

    public Map<Integer, Button> getGlobalButtons(Player player) {
        return null;
    }

    public abstract String getPrePaginatedTitle(Player player);

    public abstract Map<Integer, Button> getAllPagesButtons(Player player);

}
