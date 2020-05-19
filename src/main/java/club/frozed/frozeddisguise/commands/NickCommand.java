package club.frozed.frozeddisguise.commands;

import club.frozed.frozeddisguise.FrozedDisguise;
import club.frozed.frozeddisguise.managers.NickGenerator;
import club.frozed.frozeddisguise.managers.SkinGenerator;
import club.frozed.frozeddisguise.utils.Messages;
import net.haoshoku.nick.NickPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;

public class NickCommand implements CommandExecutor, Listener {

    public static HashMap<Player, String> nickdata;
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }

        if (!sender.hasPermission("nick.use")) {
            sender.sendMessage("&cNo Permission.");
            return true;
        }

        Player player = (Player) sender;
        if (args.length == 0) {
            player.openInventory(SkinGenerator.menu);
        }
        if (args.length == 1) {
            if (!sender.hasPermission("nick.use.name")) {
                sender.sendMessage("&cNo Permission.");
                return true;
            }
            for (Player p : FrozedDisguise.getInstance().getServer().getOnlinePlayers()) {
                if (p.getName().equalsIgnoreCase(args[0])) {
                    player.sendMessage(Messages.CC("&cThat name is already used."));
                    return true;
                }
            }
            NickCommand.nickdata.put(player, args[0]);
            player.openInventory(SkinGenerator.menu);
        }

        return true;
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if (event.getInventory().getTitle().equalsIgnoreCase("��8Pick a skin")) {
            event.setCancelled(true);
            if (event.getCurrentItem() == null || event.getCurrentItem() == null || SkinGenerator.data.get(event.getCurrentItem().getItemMeta().getDisplayName()) == null) {
                return;
            }
            Player player = (Player) event.getWhoClicked();
            String before = player.getName();
            String name = SkinGenerator.data.get(event.getCurrentItem().getItemMeta().getDisplayName()).split(":")[0];
            String display = SkinGenerator.data.get(event.getCurrentItem().getItemMeta().getDisplayName()).split(":")[1];

            String nick;
            if (NickCommand.nickdata.get(player) != null) {
                nick = NickCommand.nickdata.get(player);
            } else {
                nick = NickGenerator.generate();
            }

            NickPlugin.getPlugin().getAPI().refreshPlayer(player);
            NickPlugin.getPlugin().getAPI().nick(player, nick);
            NickPlugin.getPlugin().getAPI().setSkin(player, name);
            NickPlugin.getPlugin().getAPI().setGameProfileName(player, nick);
            NickPlugin.getPlugin().getAPI().refreshPlayer(player);
            player.getOpenInventory().close();
            player.sendMessage(Messages.CC("&a&aSuccess! You now look like &f" + nick + " &a(In the skin of &e" + display + "&a) !"));

            for (Player p : FrozedDisguise.getInstance().getServer().getOnlinePlayers()) {
                if (p.hasPermission("nick.alert")) {
                    p.sendMessage(Messages.CC("&f" + before + " &adisguised to &f" + nick + " &a!"));
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
    public void nickCheck(AsyncPlayerPreLoginEvent event) {
        String name = event.getName();
        for (Player player : FrozedDisguise.getInstance().getServer().getOnlinePlayers()) {
            if (name.equalsIgnoreCase(player.getName())) {
                BukkitRunnable task = new BukkitRunnable() {
                    public void run() {
                        player.kickPlayer(Messages.CC("&cYou were kicked because same player login the server."));
                    }
                };
                task.runTaskLater(FrozedDisguise.getInstance(), 3L);
            }
        }
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        if (NickPlugin.getPlugin().getAPI().isNicked(event.getPlayer())) {
            event.setFormat(Messages.CC("&a" + event.getPlayer().getName() + "&f: %2$s"));
        }
    }

    static {
        NickCommand.nickdata = new HashMap<>();
    }

}
