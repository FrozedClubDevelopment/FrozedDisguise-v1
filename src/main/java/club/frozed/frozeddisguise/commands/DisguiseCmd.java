package club.frozed.frozeddisguise.commands;

import club.frozed.frozeddisguise.FrozedDisguise;
import club.frozed.frozeddisguise.managers.NameGen;
import club.frozed.frozeddisguise.managers.SkinGen;
import club.frozed.frozeddisguise.utils.Messages;
import net.haoshoku.nick.NickPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.List;

public class DisguiseCmd implements CommandExecutor, Listener {

    public static HashMap<Player, String> nickData;
    List<String> filteredWord = FrozedDisguise.getInstance().getConfig().getStringList("FILTERED-WORDS");

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }

        if (!sender.hasPermission("frozed.disguise")) {
            sender.sendMessage(Messages.CC("&cNo permission."));
            return true;
        }

        Player player = (Player) sender;
        if (args.length == 0) {
            player.openInventory(SkinGen.menu);
        }

        if (args.length == 1) {
            if (!sender.hasPermission("frozed.disguise.name")) {
                sender.sendMessage(Messages.CC("&cNo permission."));
                return true;
            }

            if (isNameUsed(args, player)) {
                return true;
            }

            if (isFiltered(args, player)) {
                return true;
            }

            nickData.put(player, args[0]);
            player.openInventory(SkinGen.menu);
            //player.openInventory(RankSelector.menu);
        }

        if (args.length == 2) {
            if (!sender.hasPermission("frozed.disguise.name.skin")) {
                sender.sendMessage(Messages.CC("&cNo permission."));
                return true;
            }

            if (isNameUsed(args, player)) {
                return true;
            }

            if (isFiltered(args, player)) {
                return true;
            }

            nickData.put(player, args[0]);

            NickPlugin.getPlugin().getAPI().nick(player, args[0]);
            NickPlugin.getPlugin().getAPI().setSkin(player, args[1]);
            NickPlugin.getPlugin().getAPI().setGameProfileName(player, args[0]);
            NickPlugin.getPlugin().getAPI().refreshPlayer(player);

            player.sendMessage(Messages.CC("&7&m--------------------------------------------------"));
            player.sendMessage(Messages.CC("&b&lYOU ARE NOW DISGUISED!"));
            player.sendMessage(Messages.CC(" "));
            player.sendMessage(Messages.CC("&bName&7: &f") + args[0]);
            player.sendMessage(Messages.CC("&bSkin&7: &f") + args[1]);
            player.sendMessage(Messages.CC(" "));
            player.sendMessage(Messages.CC("&4&lIF &ca player with the name you've chosen"));
            player.sendMessage(Messages.CC("&cto disguise as connects, you'll be kicked."));
            player.sendMessage(Messages.CC("&7&m--------------------------------------------------"));

            for (Player p : FrozedDisguise.getInstance().getServer().getOnlinePlayers()) {
                if (p.hasPermission("frozed.disguise.alerts")) {
                    p.sendMessage(Messages.CC("&8[&b❖&8] &f" + NickPlugin.getPlugin().getAPI().getOriginalGameProfileName(player) + " &7has disguised as &f" + args[0] + "&7 with the skin of &f" + args[1]));
                }
            }
        }

        return true;
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if (event.getInventory().getTitle().equalsIgnoreCase(Messages.CC("&8Select an Skin"))) {
            event.setCancelled(true);

            if (event.getCurrentItem() == null || event.getCurrentItem() == null || SkinGen.data.get(event.getCurrentItem().getItemMeta().getDisplayName()) == null) {
                return;
            }

            Player player = (Player) event.getWhoClicked();
            String before = player.getName();
            String name = SkinGen.data.get(event.getCurrentItem().getItemMeta().getDisplayName()).split(":")[0];
            String display = SkinGen.data.get(event.getCurrentItem().getItemMeta().getDisplayName()).split(":")[1];

            String nick;
            if (nickData.get(player) != null) {
                nick = nickData.get(player);
            } else {
                nick = NameGen.generate();
            }

            NickPlugin.getPlugin().getAPI().nick(player, nick);
            NickPlugin.getPlugin().getAPI().setSkin(player, name);
            NickPlugin.getPlugin().getAPI().setGameProfileName(player, nick);
            NickPlugin.getPlugin().getAPI().refreshPlayer(player);

            player.getOpenInventory().close();
            player.sendMessage(Messages.CC("&7&m--------------------------------------------------"));
            player.sendMessage(Messages.CC("&b&lYOU ARE NOW DISGUISED!"));
            player.sendMessage(Messages.CC(" "));
            player.sendMessage(Messages.CC("&bName&7: &f") + nick);
            player.sendMessage(Messages.CC("&bSkin&7: &f") + display);
            player.sendMessage(Messages.CC(" "));
            player.sendMessage(Messages.CC("&4&lIF &ca player with the name you've chosen"));
            player.sendMessage(Messages.CC("&cto disguise as connects, you'll be kicked."));
            player.sendMessage(Messages.CC("&7&m--------------------------------------------------"));

            for (Player p : FrozedDisguise.getInstance().getServer().getOnlinePlayers()) {
                if (p.hasPermission("frozed.disguise.alerts")) {
                    p.sendMessage(Messages.CC("&8[&b❖&8] &f" + before + " &7has disguised as &f" + nick));
                }
            }

            BukkitRunnable fix = new BukkitRunnable() {
                public void run() {
                    NickPlugin.getPlugin().getAPI().refreshPlayer(player);
                }
            };
            fix.runTaskLater(FrozedDisguise.getInstance(), 20L);
        }
    }

    @EventHandler
    public void disguiseCheck(AsyncPlayerPreLoginEvent event) {
        for (Player player : FrozedDisguise.getInstance().getServer().getOnlinePlayers()) {
            if (event.getName().equalsIgnoreCase(player.getName())) {
                BukkitRunnable bukkitRunnable = new BukkitRunnable() {
                    public void run() {
                        player.kickPlayer(Messages.CC("&cYou were kicked because a player with the name you were disguised connected."));
                    }
                };
                bukkitRunnable.runTaskLater(FrozedDisguise.getInstance(), 3L);
            }
        }
    }

    private boolean isNameUsed(String[] args, Player player) {
        for (Player p : FrozedDisguise.getInstance().getServer().getOnlinePlayers()) {
            if (p.getName().equalsIgnoreCase(args[0])) {
                player.sendMessage(Messages.CC("&cThat name is already used."));
                return true;
            }
        }
        return false;
    }

    // TODO: Make it check if the name contains a filtered word in between, before or after the text

    private boolean isFiltered(String[] args, Player player) {
        if (filteredWord.contains(args[0].toLowerCase())) {
            player.sendMessage(Messages.CC("&cYou can't use that name because it contains a filtered word in it!"));
            return true;
        }
        return false;
    }

    static {
        nickData = new HashMap<>();
    }

}
