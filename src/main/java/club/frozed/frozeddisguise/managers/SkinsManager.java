package club.frozed.frozeddisguise.managers;

import club.frozed.frozeddisguise.utils.Messages;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class SkinsManager {

    public static List<String> skins;
    public static Inventory menu;
    public static HashMap<String, String> data;

    public static String generate() {
        Random random = new Random();
        return skins.get(random.nextInt(skins.size()));
    }

    static {
        data = new HashMap<>();
        skins = Arrays.asList("_rsu:Killer", "E_Girl:E-girl", "MHF_Sheep:Sheep Man", "BackupDancer:L", "Arro:Arabian", "bennyknight:Badman", "MHF_Herobrine:Herobrine",
                "MHF_Villager:Villager", "CocoDeMedellin:Black Goku", "SheAGoldDigger:Blue Goku", "MHF_Enderman:Enderman", "Marcel:Marcel", "Zwergoor:Emoji", "HikakinGames:Youtuber",
                "loudoggydog3010:XXX", "Reinstallation:Rambo Chicken", "SolluxCaptor:Nezuko", "Elb1to:Elb1to");

        menu = Bukkit.createInventory(null, 18, Messages.CC("&8Select an Skin"));
        for (String skin : skins) {
            ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
            SkullMeta skullmeta = (SkullMeta) skull.getItemMeta();
            String nick = skin.split(":")[0];
            String display = skin.split(":")[1];
            skullmeta.setOwner(nick);
            skullmeta.setDisplayName(ChatColor.AQUA + display);
            skull.setItemMeta(skullmeta);
            menu.addItem(skull);
            data.put(skull.getItemMeta().getDisplayName(), nick + ":" + display);
        }
    }

}
