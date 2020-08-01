package club.frozed.frozeddisguise.ranks;

import club.frozed.frozeddisguise.FrozedDisguise;
import club.frozed.frozeddisguise.managers.PlayerManager;
import club.frozed.frozeddisguise.utils.ItemBuilder;
import club.frozed.frozeddisguise.utils.Messages;
import club.frozed.frozeddisguise.utils.menu.Button;
import club.frozed.frozeddisguise.utils.menu.Menu;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class RanksMenu extends Menu {

    private static Button EMPTY_FILLER = Button.placeholder(Material.STAINED_GLASS_PANE, (byte) 15, " ");

    @Override
    public int getSize() {
        return 9 * 4;
    }

    @Override
    public String getTitle(Player player) {
        return ChatColor.DARK_GRAY + "Select a Rank";
    }

    @Override
    public Map<Integer, Button> getButtons(Player player) {
        Map<Integer, Button> buttons = new HashMap<>();

        int i = 10;
        for (Ranks rank : FrozedDisguise.getRankManager().getRanks()) {
            buttons.put(i, new SelectRankButton(rank));
            if (i == 16) {
                i = 18;
            }
            if (i == 26) {
                i = 28;
            }
            i++;
        }

        buttons.put(31, new ResetRankButton());

        for (int fill = 0; fill < 36; fill++) {
            buttons.putIfAbsent(fill, EMPTY_FILLER);
        }

        return buttons;
    }

    private static class SelectRankButton extends Button {
        private Ranks rank;

        @Override
        public ItemStack getButtonItem(Player player) {
            return new ItemBuilder(Material.BOOK_AND_QUILL).name(this.rank.getNameColor() + this.rank.getName())
                    .lore("&7&m--------------------------------",
                            "&fFormat: " + rank.getPrefix() + player.getName() + "&7: &fHi!",
                            " ",
                            "&7Click to select this rank!",
                            "&7&m--------------------------------"
                    ).build();
        }

        @Override
        public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
            if (player.hasPermission("frozed.disguise.ranks." + rank.getName()) || player.hasPermission("frozed.disguise.ranks.*")) {
                playSuccess(player);
                PlayerManager.setRank(player, FrozedDisguise.getRankManager().getRankName(rank.getName()));
                if (FrozedDisguise.getInstance().getConfig().getBoolean("BOOLEANS.TABLIST_NAME_COLOR")) {
                    player.setPlayerListName(Messages.CC(rank.getNameColor() + player.getName()));
                }
                player.sendMessage(Messages.CC(FrozedDisguise.getInstance().getConfig().getString("MESSAGES.DISGUISE-RANK-SELECTED"))
                        .replaceAll("<rankName>", rank.getName())
                        .replaceAll("<rankPrefix>", rank.getPrefix())
                );
            } else {
                playFail(player);
                player.sendMessage(Messages.CC(FrozedDisguise.getInstance().getConfig().getString("MESSAGES.DISGUISE-RANK-NO-PERMISSION"))
                        .replaceAll("<rankName>", rank.getName())
                        .replaceAll("<rankPrefix>", rank.getPrefix())
                );
            }
            player.closeInventory();
        }

        public SelectRankButton(Ranks rank) {
            this.rank = rank;
        }
    }

    private static class ResetRankButton extends Button {
        @Override
        public ItemStack getButtonItem(Player player) {
            return new ItemBuilder(Material.REDSTONE).name("&cRemove Rank").lore(" ", "&7Click here to remove your rank.").build();
        }

        @Override
        public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
            playFail(player);
            PlayerManager.rankData.remove(player);
            if (FrozedDisguise.getInstance().getConfig().getBoolean("BOOLEANS.TABLIST_NAME_COLOR")) {
                player.setPlayerListName(null);
            }
            player.closeInventory();
            player.sendMessage(Messages.CC(FrozedDisguise.getInstance().getConfig().getString("MESSAGES.DISGUISE-RANK-SUCCESSFULLY-REMOVED")));
        }
    }

}
