package club.frozed.frozeddisguise;

import club.frozed.frozeddisguise.commands.CheckUuidCommand;
import club.frozed.frozeddisguise.commands.DisguiseCmd;
import club.frozed.frozeddisguise.commands.ListCmd;
import club.frozed.frozeddisguise.commands.UnDisguiseCmd;
import club.frozed.frozeddisguise.managers.NameGen;
import club.frozed.frozeddisguise.managers.SkinGen;
import club.frozed.frozeddisguise.utils.Messages;
import net.haoshoku.nick.NickPlugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class FrozedDisguise extends JavaPlugin {

    public static FrozedDisguise instance;
    public static SkinGen skinGen;
    public static NameGen nameGen;

    public void onEnable() {
        FrozedDisguise.instance = this;

        if (!this.getDescription().getAuthors().contains("FrozedDevelopment") || !this.getDescription().getAuthors().contains("Elb1to") ||
                !this.getDescription().getAuthors().contains("Scalebound") || !this.getDescription().getName().equals("FrozedDisguise")) {
            for (int i = 0; i < 10; i++) {
                Bukkit.getServer().broadcastMessage(ChatColor.RED + "Why are you changing the");
                Bukkit.getServer().broadcastMessage(ChatColor.RED + "plugin yml ( ͡° ͜ʖ ͡°)╭∩╮");
            }
            System.exit(0);
            Bukkit.shutdown();
        }

        FrozedDisguise.skinGen = new SkinGen();
        FrozedDisguise.nameGen = new NameGen();
        this.getServer().getConsoleSender().sendMessage(Messages.CC("&7-------------------------------------------"));
        this.getServer().getConsoleSender().sendMessage(Messages.CC("&b&lFrozedDisguise &8- &3") + getDescription().getVersion());
        this.getServer().getConsoleSender().sendMessage(" ");
        this.registerListeners();
        this.registerCommands();
        this.getServer().getConsoleSender().sendMessage(Messages.CC("&7-------------------------------------------"));
        NickPlugin.getPlugin().getAPI().setSkinChangingForPlayer(true);
    }

    public void registerListeners() {
        Bukkit.getPluginManager().registerEvents(new DisguiseCmd(), this);
        this.getServer().getConsoleSender().sendMessage(Messages.CC("&8 [&b*&8] &3Listeners Registered Successfully"));
    }

    public void registerCommands() {
        this.getCommand("disguise").setExecutor(new DisguiseCmd());
        this.getCommand("undisguise").setExecutor(new UnDisguiseCmd());
        this.getCommand("disguiselist").setExecutor(new ListCmd());
        this.getCommand("checkuuid").setExecutor(new CheckUuidCommand());
        this.getServer().getConsoleSender().sendMessage(Messages.CC("&8 [&b*&8] &3Commands Registered Successfully"));
    }

    public static FrozedDisguise getInstance() {
        return instance;
    }

    public static SkinGen getSkinGen() {
        return skinGen;
    }

    public static NameGen getNameGen() {
        return nameGen;
    }

}
