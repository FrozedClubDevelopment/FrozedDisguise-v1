package club.frozed.frozeddisguise.commands;

import club.frozed.frozeddisguise.FrozedDisguise;
import club.frozed.frozeddisguise.managers.NamesManager;
import club.frozed.frozeddisguise.managers.PlayerManager;
import club.frozed.frozeddisguise.managers.SkinsManager;
import club.frozed.frozeddisguise.ranks.Ranks;
import club.frozed.frozeddisguise.utils.Messages;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.scheduler.BukkitRunnable;
import xyz.haoshoku.nick.NickPlugin;

import java.util.HashMap;
import java.util.List;

public class DisguiseCmd implements CommandExecutor, Listener {

    public static HashMap<Player, String> nickData = new HashMap<>();

    private Ranks rank;

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        boolean toggleSkinsMenu = FrozedDisguise.getInstance().getConfig().getBoolean("BOOLEANS.ENABLE-SKINS-MENU");

        if (!(sender instanceof Player)) {
            return true;
        }

        if (!sender.hasPermission("frozed.disguise")) {
            sender.sendMessage(Messages.CC(FrozedDisguise.getInstance().getConfig().getString("MESSAGES.NO-PERMISSION-TO-USE")));
            return true;
        }

        Player player = (Player) sender;
        if (args.length == 0) {
            if (toggleSkinsMenu) {
                player.openInventory(SkinsManager.menu);
            } else {
                String disguiseName;
                String disguiseSkin = SkinsManager.generate().split(":")[0];

                if (nickData.get(player) != null) {
                    disguiseName = nickData.get(player);
                } else {
                    disguiseName = NamesManager.generate();
                }

                setPlayerDisguise(player, disguiseName, disguiseSkin);
            }
        }

        if (args.length == 1) {
            if (!sender.hasPermission("frozed.disguise.name")) {
                sender.sendMessage(Messages.CC(FrozedDisguise.getInstance().getConfig().getString("MESSAGES.NO-PERMISSION-TO-USE")));
                return true;
            }

            if (isNameUsed(args, player)) {
                return true;
            }

            if (isFiltered(args, player)) {
                return true;
            }

            if (toggleSkinsMenu) {
                player.openInventory(SkinsManager.menu);
            } else {
                String disguiseSkin = SkinsManager.generate().split(":")[0];

                setPlayerDisguise(player, args[0], disguiseSkin);
            }
        }

        if (args.length == 2) {
            if (!sender.hasPermission("frozed.disguise.name.skin")) {
                sender.sendMessage(Messages.CC(FrozedDisguise.getInstance().getConfig().getString("MESSAGES.NO-PERMISSION-TO-USE")));
                return true;
            }

            if (isNameUsed(args, player)) {
                return true;
            }

            if (isFiltered(args, player)) {
                return true;
            }

            setPlayerDisguise(player, args[0], args[1]);
        }

        return true;
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if (event.getInventory().getTitle().equalsIgnoreCase(Messages.CC("&8Select an Skin"))) {
            event.setCancelled(true);

            if (event.getCurrentItem() == null || !event.getCurrentItem().hasItemMeta() || SkinsManager.data.get(event.getCurrentItem().getItemMeta().getDisplayName()) == null) {
                return;
            }

            Player player = (Player) event.getWhoClicked();
            String skin = SkinsManager.data.get(event.getCurrentItem().getItemMeta().getDisplayName()).split(":")[0];

            String nick;
            if (nickData.get(player) != null) {
                nick = nickData.get(player);
            } else {
                nick = NamesManager.generate();
            }
            setPlayerDisguise(player, nick, skin);

            player.getOpenInventory().close();
        }
    }

    public void setPlayerDisguise(Player player, String disguiseName, String disguiseSkin) {
        boolean sendDisguiseMsg = FrozedDisguise.getInstance().getConfig().getBoolean("BOOLEANS.SHOW-DISGUISED-MSG");
        boolean sendStaffAlert = FrozedDisguise.getInstance().getConfig().getBoolean("BOOLEANS.SEND-DISGUISE-ALERT");
        boolean toggleActionBar = FrozedDisguise.getInstance().getConfig().getBoolean("BOOLEANS.ENABLE-ACTION-BAR");

        NickPlugin.getPlugin().getAPI().nick(player, disguiseName);
        NickPlugin.getPlugin().getAPI().setGameProfileName(player, disguiseName);

        NickPlugin.getPlugin().getAPI().setSkin(player, disguiseSkin);
        NickPlugin.getPlugin().getAPI().refreshPlayer(player);

        PlayerManager.rankData.remove(player);

        isHealthModifier(player);
        updatePlayerListNameColor(player);

        player.setDisplayName(player.getName());

        if (sendDisguiseMsg) {
            for (String message : FrozedDisguise.getInstance().getConfig().getStringList("MESSAGES.DISGUISE-MESSAGE")) {
                Messages.sendMessageToPlayer(message.replaceAll("<disguise_name>", disguiseName).replaceAll("<disguise_skin>", disguiseSkin), player);
            }
        }

        for (Player p : FrozedDisguise.getInstance().getServer().getOnlinePlayers()) {
            if (p.hasPermission("frozed.disguise.alerts") && sendStaffAlert) {
                p.sendMessage(Messages.CC(FrozedDisguise.getInstance().getConfig().getString("MESSAGES.DISGUISE-ALERT-WITH-SKIN"))
                        .replaceAll("<player>", NickPlugin.getPlugin().getAPI().getOriginalGameProfileName(player))
                        .replaceAll("<disguise_name>", disguiseName)
                        .replaceAll("<disguise_skin>", disguiseSkin)
                );
            }
        }

        if (NickPlugin.getPlugin().getAPI().isCurrentlyRefreshing(player) && toggleActionBar) {
            FrozedDisguise.getInstance().getActionbar().sendActionbar(
                    player, Messages.CC(FrozedDisguise.getInstance().getConfig().getString("MESSAGES.DISGUISE-ACTION-BAR")).replaceAll("<disguise_name>", NickPlugin.getPlugin().getAPI().getNickedName(player))
            );
        }
    }

    @EventHandler
    public void disguiseCheck(AsyncPlayerPreLoginEvent event) {
        for (Player player : FrozedDisguise.getInstance().getServer().getOnlinePlayers()) {
            if (event.getName().equalsIgnoreCase(player.getName())) {
                BukkitRunnable bukkitRunnable = new BukkitRunnable() {
                    public void run() {
                        player.kickPlayer(Messages.CC(FrozedDisguise.getInstance().getConfig().getString("MESSAGES.KICKED-BECAUSE-OF-PLAYER-CONNECTION")));
                    }
                };
                bukkitRunnable.runTaskLater(FrozedDisguise.getInstance(), 3L);
            }
        }
    }

    public void updatePlayerListNameColor(Player player) {
        if (FrozedDisguise.getInstance().getConfig().getBoolean("BOOLEANS.TABLIST-NAME-COLOR")) {
            BukkitRunnable fixPlayerListNameColor = new BukkitRunnable() {
                public void run() {
                    player.setPlayerListName(Messages.CC(FrozedDisguise.getInstance().getConfig().getString("BOOLEANS.DEFAULT-TABLIST-NAME-COLOR")) + player.getName());
                }
            };
            fixPlayerListNameColor.runTaskLater(FrozedDisguise.getInstance(), 25L);
        }
    }

    private void isHealthModifier(Player player) {
        if (FrozedDisguise.getInstance().getConfig().getBoolean("BOOLEANS.HEALTH-MODIFIER")) {
            if (NickPlugin.getPlugin().getAPI().isNicked(player)) {
                BukkitRunnable updateHealth = new BukkitRunnable() {
                    public void run() {
                        player.setMaxHealth((double) FrozedDisguise.getInstance().getConfig().getInt("BOOLEANS.HEALTH-MODIFIER-AMOUNT"));
                        player.setHealth((double) FrozedDisguise.getInstance().getConfig().getInt("BOOLEANS.HEALTH-MODIFIER-AMOUNT"));
                    }
                };
                updateHealth.runTaskLater(FrozedDisguise.getInstance(), 10L);
            }
        }
    }

    private boolean isNameUsed(String[] args, Player player) {
        for (Player p : FrozedDisguise.getInstance().getServer().getOnlinePlayers()) {
            if (p.getName().equalsIgnoreCase(args[0])) {
                player.sendMessage(Messages.CC(FrozedDisguise.getInstance().getConfig().getString("MESSAGES.DISGUISE-NAME-IN-USE")));
                return true;
            }
        }

        return false;
    }

    // TODO: Make it check if the name contains a filtered word in between, before or after the text
    private boolean isFiltered(String[] args, Player player) {
        List<String> filteredWord = FrozedDisguise.getInstance().getConfig().getStringList("FILTERED-WORDS");
        if (filteredWord.contains(args[0].toLowerCase())) {
            player.sendMessage(Messages.CC(FrozedDisguise.getInstance().getConfig().getString("MESSAGES.DISGUISE-NAME-FILTERED")));
            return true;
        }

        return false;
    }

}
