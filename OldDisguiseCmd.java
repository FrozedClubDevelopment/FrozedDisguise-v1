package club.frozed.frozeddisguise.commands;

import club.frozed.frozeddisguise.FrozedDisguise;
import club.frozed.frozeddisguise.managers.NamesManager;
import club.frozed.frozeddisguise.managers.PlayerManager;
import club.frozed.frozeddisguise.managers.SkinsManager;
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

public class OldDisguiseCmd implements CommandExecutor, Listener {

    public static HashMap<Player, String> nickData = new HashMap<>();

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        boolean toggleSkinsMenu = FrozedDisguise.getInstance().getConfig().getBoolean("BOOLEANS.ENABLE-SKINS-MENU");
        //boolean sendDisguiseMsg = FrozedDisguise.getInstance().getConfig().getBoolean("BOOLEANS.SHOW-DISGUISED-MSG");
        //boolean sendStaffAlert = FrozedDisguise.getInstance().getConfig().getBoolean("BOOLEANS.SEND-DISGUISE-ALERT");

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

                //player.setDisplayName(disguiseName);
                /*NickPlugin.getPlugin().getAPI().nick(player, disguiseName);
                NickPlugin.getPlugin().getAPI().setGameProfileName(player, disguiseName);

                NickPlugin.getPlugin().getAPI().setSkin(player, disguiseSkin);
                NickPlugin.getPlugin().getAPI().refreshPlayer(player);

                PlayerManager.rankData.remove(player);

                updatePlayerListNameColor(player);

                if (sendDisguiseMsg) {
                    for (String message : FrozedDisguise.getInstance().getConfig().getStringList("MESSAGES.DISGUISE-MESSAGE")) {
                        Messages.sendMessageToPlayer(message.replaceAll("<disguise_name>", disguiseName).replaceAll("<disguise_skin>", disguiseSkin), player);
                    }
                }

                for (Player p : FrozedDisguise.getInstance().getServer().getOnlinePlayers()) {
                    if (p.hasPermission("frozed.disguise.alerts")) {
                        if (sendStaffAlert) {
                            p.sendMessage(Messages.CC(FrozedDisguise.getInstance().getConfig().getString("MESSAGES.DISGUISE-ALERT-WITH-SKIN"))
                                    .replaceAll("<player>", NickPlugin.getPlugin().getAPI().getOriginalGameProfileName(player))
                                    .replaceAll("<disguise_name>", disguiseName)
                                    .replaceAll("<disguise_skin>", disguiseSkin)
                            );
                        }
                    }
                }*/
            }

            /*if (NickPlugin.getPlugin().getAPI().isCurrentlyRefreshing(player)) {
                if (FrozedDisguise.getInstance().getConfig().getBoolean("BOOLEANS.SHOW-ACTION-BAR")) {
                    FrozedDisguise.getInstance().getActionbar().sendActionbar(player, Messages.CC("&b&lYOU ARE NOW DISGUISED AS&7: &f" + NickPlugin.getPlugin().getAPI().getNickedName(player)));
                }
            }*/
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

            //nickData.put(player, args[0]);

            if (toggleSkinsMenu) {
                player.openInventory(SkinsManager.menu);
            } else {

                String disguiseSkin = SkinsManager.generate().split(":")[0];

                setPlayerDisguise(player, args[0], disguiseSkin);

                //player.setDisplayName(args[0]);
                /*NickPlugin.getPlugin().getAPI().nick(player, args[0]);
                NickPlugin.getPlugin().getAPI().setGameProfileName(player, args[0]);

                NickPlugin.getPlugin().getAPI().setSkin(player, disguiseSkin);
                NickPlugin.getPlugin().getAPI().refreshPlayer(player);

                updatePlayerListNameColor(player);

                if (sendDisguiseMsg) {
                    for (String message : FrozedDisguise.getInstance().getConfig().getStringList("MESSAGES.DISGUISE-MESSAGE")) {
                        Messages.sendMessageToPlayer(message.replaceAll("<disguise_name>", args[0]).replaceAll("<disguise_skin>", disguiseSkin), player);
                    }
                }

                for (Player p : FrozedDisguise.getInstance().getServer().getOnlinePlayers()) {
                    if (p.hasPermission("frozed.disguise.alerts")) {
                        if (sendStaffAlert) {
                            p.sendMessage(Messages.CC(FrozedDisguise.getInstance().getConfig().getString("MESSAGES.DISGUISE-ALERT-WITH-SKIN"))
                                    .replaceAll("<player>", NickPlugin.getPlugin().getAPI().getOriginalGameProfileName(player))
                                    .replaceAll("<disguise_name>", args[0])
                                    .replaceAll("<disguise_skin>", disguiseSkin)
                            );
                        }
                    }
                }*/
            }

            /*if (NickPlugin.getPlugin().getAPI().isCurrentlyRefreshing(player)) {
                if (FrozedDisguise.getInstance().getConfig().getBoolean("BOOLEANS.SHOW-ACTION-BAR")) {
                    FrozedDisguise.getInstance().getActionbar().sendActionbar(player, Messages.CC("&b&lYOU ARE NOW DISGUISED AS&7: &f" + NickPlugin.getPlugin().getAPI().getNickedName(player)));
                }
            }*/
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

            //nickData.put(player, args[0]);

            //player.setDisplayName(args[0]);
            /*NickPlugin.getPlugin().getAPI().nick(player, args[0]);
            NickPlugin.getPlugin().getAPI().setGameProfileName(player, args[0]);

            NickPlugin.getPlugin().getAPI().setSkin(player, args[1]);
            NickPlugin.getPlugin().getAPI().refreshPlayer(player);

            updatePlayerListNameColor(player);

            if (sendDisguiseMsg) {
                for (String message : FrozedDisguise.getInstance().getConfig().getStringList("MESSAGES.DISGUISE-MESSAGE")) {
                    Messages.sendMessageToPlayer(message.replaceAll("<disguise_name>", String.valueOf(args[0])).replaceAll("<disguise_skin>", String.valueOf(args[1])), player);
                }
            }

            for (Player p : FrozedDisguise.getInstance().getServer().getOnlinePlayers()) {
                if (p.hasPermission("frozed.disguise.alerts")) {
                    if (sendStaffAlert) {
                        p.sendMessage(Messages.CC(FrozedDisguise.getInstance().getConfig().getString("MESSAGES.DISGUISE-ALERT-WITH-SKIN"))
                                .replaceAll("<player>", NickPlugin.getPlugin().getAPI().getOriginalGameProfileName(player))
                                .replaceAll("<disguise_name>", String.valueOf(args[0]))
                                .replaceAll("<disguise_skin>", String.valueOf(args[1]))
                        );
                    }
                }
            }

            if (NickPlugin.getPlugin().getAPI().isCurrentlyRefreshing(player)) {
                if (FrozedDisguise.getInstance().getConfig().getBoolean("BOOLEANS.SHOW-ACTION-BAR")) {
                    FrozedDisguise.getInstance().getActionbar().sendActionbar(player, Messages.CC("&b&lYOU ARE NOW DISGUISED AS&7: &f" + NickPlugin.getPlugin().getAPI().getNickedName(player)));
                }
            }*/
        }

        return true;
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {

        //boolean sendDisguiseMsg = FrozedDisguise.getInstance().getConfig().getBoolean("BOOLEANS.SHOW-DISGUISED-MSG");
        //boolean sendStaffAlert = FrozedDisguise.getInstance().getConfig().getBoolean("BOOLEANS.SEND-DISGUISE-ALERT");

        if (event.getInventory().getTitle().equalsIgnoreCase(Messages.CC("&8Select an Skin"))) {
            event.setCancelled(true);

            if (event.getCurrentItem() == null || !event.getCurrentItem().hasItemMeta() || SkinsManager.data.get(event.getCurrentItem().getItemMeta().getDisplayName()) == null) {
                return;
            }

            Player player = (Player) event.getWhoClicked();
            //String before = player.getName();
            String skin = SkinsManager.data.get(event.getCurrentItem().getItemMeta().getDisplayName()).split(":")[0];
            //String display = SkinsManager.data.get(event.getCurrentItem().getItemMeta().getDisplayName()).split(":")[1];

            String nick;
            if (nickData.get(player) != null) {
                nick = nickData.get(player);
            } else {
                nick = NamesManager.generate();
            }

            setPlayerDisguise(player, nick, skin);

            player.getOpenInventory().close();

            //player.setDisplayName(nick);
            /*NickPlugin.getPlugin().getAPI().nick(player, nick);
            NickPlugin.getPlugin().getAPI().setGameProfileName(player, nick);

            NickPlugin.getPlugin().getAPI().setSkin(player, skin);
            NickPlugin.getPlugin().getAPI().refreshPlayer(player);

            updatePlayerListNameColor(player);

            player.getOpenInventory().close();

            if (sendDisguiseMsg) {
                for (String message : FrozedDisguise.getInstance().getConfig().getStringList("MESSAGES.DISGUISE-MESSAGE")) {
                    Messages.sendMessageToPlayer(message.replaceAll("<disguise_name>", String.valueOf(nick)).replaceAll("<disguise_skin>", String.valueOf(display)), player);
                }
            }

            for (Player p : FrozedDisguise.getInstance().getServer().getOnlinePlayers()) {
                if (p.hasPermission("frozed.disguise.alerts")) {
                    if (sendStaffAlert) {
                        p.sendMessage(Messages.CC(FrozedDisguise.getInstance().getConfig().getString("MESSAGES.DISGUISE-ALERT"))
                                .replaceAll("<player>", before)
                                .replaceAll("<disguise_name>", nick)
                        );
                    }
                }
            }*/

            /*BukkitRunnable fix = new BukkitRunnable() {
                public void run() {
                    NickPlugin.getPlugin().getAPI().refreshPlayer(player);
                }
            };

            fix.runTaskLater(FrozedDisguise.getInstance(), 20L);*/
        }
    }

    public void setPlayerDisguise(Player player, String disguiseName, String disguiseSkin) {

        boolean sendDisguiseMsg = FrozedDisguise.getInstance().getConfig().getBoolean("BOOLEANS.SHOW-DISGUISED-MSG");
        boolean sendStaffAlert = FrozedDisguise.getInstance().getConfig().getBoolean("BOOLEANS.SEND-DISGUISE-ALERT");

        NickPlugin.getPlugin().getAPI().nick(player, disguiseName);
        NickPlugin.getPlugin().getAPI().setGameProfileName(player, disguiseName);

        NickPlugin.getPlugin().getAPI().setSkin(player, disguiseSkin);
        NickPlugin.getPlugin().getAPI().refreshPlayer(player);

        PlayerManager.rankData.remove(player);

        updatePlayerListNameColor(player);

        if (sendDisguiseMsg) {
            for (String message : FrozedDisguise.getInstance().getConfig().getStringList("MESSAGES.DISGUISE-MESSAGE")) {
                Messages.sendMessageToPlayer(message.replaceAll("<disguise_name>", disguiseName).replaceAll("<disguise_skin>", disguiseSkin), player);
            }
        }

        for (Player p : FrozedDisguise.getInstance().getServer().getOnlinePlayers()) {
            if (p.hasPermission("frozed.disguise.alerts")) {
                if (sendStaffAlert) {
                    p.sendMessage(Messages.CC(FrozedDisguise.getInstance().getConfig().getString("MESSAGES.DISGUISE-ALERT-WITH-SKIN"))
                            .replaceAll("<player>", NickPlugin.getPlugin().getAPI().getOriginalGameProfileName(player))
                            .replaceAll("<disguise_name>", disguiseName)
                            .replaceAll("<disguise_skin>", disguiseSkin)
                    );
                }
            }
        }

        if (NickPlugin.getPlugin().getAPI().isCurrentlyRefreshing(player)) {
            if (FrozedDisguise.getInstance().getConfig().getBoolean("BOOLEANS.ENABLE-ACTION-BAR")) {
                FrozedDisguise.getInstance().getActionbar().sendActionbar(player, Messages.CC("&b&lYOU ARE NOW DISGUISED AS&7: &f" + NickPlugin.getPlugin().getAPI().getNickedName(player)));
            }
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
                    //if (!PlayerManager.rankData.containsKey(player)) {
                    player.setPlayerListName(Messages.CC(FrozedDisguise.getInstance().getConfig().getString("BOOLEANS.DEFAULT-TABLIST-NAME-COLOR")) + player.getName());
                    //}
                }
            };
            fixPlayerListNameColor.runTaskLater(FrozedDisguise.getInstance(), 30L);
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
