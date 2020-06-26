package club.frozed.frozeddisguise.managers;

import club.frozed.frozeddisguise.FrozedDisguise;
import club.frozed.frozeddisguise.data.Ranks;
import club.frozed.frozeddisguise.utils.Messages;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;
import java.util.List;

public class RankSelector {

    /*public static Inventory menu;
    public static List<String> ranks;
    public static HashMap<String, String> data;

    static {
        menu = Bukkit.createInventory(null, 27, Messages.CC("&8Select a Rank"));
        for (String rank : ranks) {
            ItemStack woolBlock = new ItemStack(Material.WOOL, 1);
            ItemMeta meta = woolBlock.getItemMeta();
            Ranks r = FrozedDisguise.getInstance().getRanksManager().getByName(rank);

            meta.setDisplayName(Messages.CC(r.getPrefix()));
            woolBlock.setItemMeta(meta);

            menu.addItem(woolBlock);
            data.put(woolBlock.getItemMeta().getDisplayName(), rank);
        }
    }*/

}
