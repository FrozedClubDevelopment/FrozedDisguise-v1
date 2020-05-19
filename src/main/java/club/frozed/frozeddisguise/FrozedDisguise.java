package club.frozed.frozeddisguise;

import club.frozed.frozeddisguise.commands.NickCommand;
import club.frozed.frozeddisguise.commands.NickListCommand;
import club.frozed.frozeddisguise.commands.UnNickCommand;
import club.frozed.frozeddisguise.managers.NickGenerator;
import club.frozed.frozeddisguise.managers.SkinGenerator;
import club.frozed.frozeddisguise.utils.Messages;
import net.haoshoku.nick.NickPlugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class FrozedDisguise extends JavaPlugin {

    public static FrozedDisguise instance;
    public static SkinGenerator skinGenerator;
    public static NickGenerator nickGenerator;

    public void onEnable() {
        FrozedDisguise.instance = this;

        if (!this.getDescription().getAuthors().contains("FrozedDevelopment") || !this.getDescription().getAuthors().contains("Elb1to") ||
                !this.getDescription().getAuthors().contains("Scalebound") || !this.getDescription().getName().equals("FrozedDisguise")) {
            int i;
            for (i = 0; i < 10; i++) {
                Bukkit.getServer().broadcastMessage(ChatColor.RED + "Why are you changing the");
                Bukkit.getServer().broadcastMessage(ChatColor.RED + "plugin yml ( ͡° ͜ʖ ͡°)╭∩╮");
            }
            System.exit(0);
            Bukkit.shutdown();
        }

        FrozedDisguise.skinGenerator = new SkinGenerator();
        FrozedDisguise.nickGenerator = new NickGenerator();
        this.getServer().getConsoleSender().sendMessage(Messages.CC("&7&m-------------------------------------------"));
        this.registerListeners();
        this.registerCommands();
        this.getServer().getConsoleSender().sendMessage(Messages.CC("&7&m-------------------------------------------"));
        NickPlugin.getPlugin().getAPI().setSkinChangingForPlayer(true);
    }

    public void onDisable() {
    }

    public void registerListeners() {
        Bukkit.getPluginManager().registerEvents(new NickCommand(), this);
        this.getServer().getConsoleSender().sendMessage(Messages.CC("&eListeners Registered Successfully"));
    }

    public void registerCommands() {
        this.getCommand("nick").setExecutor(new NickCommand());
        this.getCommand("unnick").setExecutor(new UnNickCommand());
        this.getCommand("nicklist").setExecutor(new NickListCommand());
        this.getServer().getConsoleSender().sendMessage(Messages.CC("&eCommands Registered Successfully"));
    }

    public static FrozedDisguise getInstance() {
        return FrozedDisguise.instance;
    }

    public static SkinGenerator getSkinGenerator() {
        return FrozedDisguise.skinGenerator;
    }

    public static NickGenerator getNickGenerator() {
        return FrozedDisguise.nickGenerator;
    }

}
