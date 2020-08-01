package club.frozed.frozeddisguise;

import club.frozed.frozeddisguise.commands.*;
import club.frozed.frozeddisguise.listeners.PlayerListener;
import club.frozed.frozeddisguise.managers.NamesManager;
import club.frozed.frozeddisguise.managers.PlayerManager;
import club.frozed.frozeddisguise.managers.SkinsManager;
import club.frozed.frozeddisguise.ranks.RanksManager;
import club.frozed.frozeddisguise.utils.Messages;
import club.frozed.frozeddisguise.utils.menu.ButtonListener;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

@Getter
public class FrozedDisguise extends JavaPlugin {

    public static FrozedDisguise instance;

    public static SkinsManager skinsManager;
    public static NamesManager namesManager;

    public static RanksManager ranksManager;
    public static PlayerManager playerManager;

    public static File rank;
    public static FileConfiguration rankConfig;

    public void onEnable() {
        instance = this;

        if (!this.getDescription().getAuthors().contains("FrozedClubDevelopment") || !this.getDescription().getAuthors().contains("Elb1to") || !this.getDescription().getName().equals("FrozedDisguise")) {
            for (int i = 0; i < 10; i++) {
                Bukkit.getServer().broadcastMessage(ChatColor.RED + "Why are you changing the");
                Bukkit.getServer().broadcastMessage(ChatColor.RED + "plugin yml ( ͡° ͜ʖ ͡°)╭∩╮");
            }
            Bukkit.getPluginManager().disablePlugins();
        }

        this.getServer().getConsoleSender().sendMessage(Messages.CC("&7-------------------------------------------"));
        this.getServer().getConsoleSender().sendMessage(Messages.CC("&b&lFrozedDisguise &8- &3") + getDescription().getVersion());
        this.getServer().getConsoleSender().sendMessage(" ");
        this.registerConfigs();
        this.registerListeners();
        this.registerManagers();
        this.registerCommands();
        this.getServer().getConsoleSender().sendMessage(Messages.CC("&7-------------------------------------------"));
    }

    public void registerListeners() {
        Bukkit.getPluginManager().registerEvents(new DisguiseCmd(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);
        Bukkit.getPluginManager().registerEvents(new ButtonListener(), this);

        this.getServer().getConsoleSender().sendMessage(Messages.CC("&8 [&b*&8] &3Listeners Registered Successfully"));
    }

    public void registerConfigs() {
        this.saveDefaultConfig();

        saveResource("ranks.yml", false);
        rank = new File(this.getDataFolder() + "/ranks.yml");
        rankConfig = YamlConfiguration.loadConfiguration(rank);

        this.getServer().getConsoleSender().sendMessage(Messages.CC("&8 [&b*&8] &3Configs Registered Successfully"));
    }

    public void registerManagers() {
        skinsManager = new SkinsManager();
        namesManager = new NamesManager();
        ranksManager = new RanksManager();
        playerManager = new PlayerManager();
        ranksManager.loadRanks();

        this.getServer().getConsoleSender().sendMessage(Messages.CC("&8 [&b*&8] &3Managers Registered Successfully"));
    }

    public void registerCommands() {
        this.getCommand("disguise").setExecutor(new DisguiseCmd());
        this.getCommand("undisguise").setExecutor(new UnDisguiseCmd());
        this.getCommand("disguiselist").setExecutor(new ListCmd());
        this.getCommand("disguisehelp").setExecutor(new HelpCmd());
        this.getCommand("checkuuid").setExecutor(new CheckUuidCmd());
        this.getCommand("disguiserank").setExecutor(new DisguiseRankCmd());
        this.getCommand("undisguiserank").setExecutor(new UndisguiseRankCmd());
        this.getCommand("frozeddisguise").setExecutor(new FrozedDisguiseCmd());

        this.getServer().getConsoleSender().sendMessage(Messages.CC("&8 [&b*&8] &3Commands Registered Successfully"));
    }

    public static FrozedDisguise getInstance() {
        return instance;
    }

    public static SkinsManager getSkinsManager() {
        return skinsManager;
    }

    public static NamesManager getNamesManager() {
        return namesManager;
    }

    public static RanksManager getRankManager() {
        return ranksManager;
    }

    @Override
    public FileConfiguration getConfig() {
        return YamlConfiguration.loadConfiguration(new File(this.getDataFolder(), "config.yml"));
    }

}
