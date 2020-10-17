package club.frozed.frozeddisguise;

import club.frozed.frozeddisguise.actionbar.ActionBar;
import club.frozed.frozeddisguise.actionbar.versions.*;
import club.frozed.frozeddisguise.commands.*;
import club.frozed.frozeddisguise.hook.HookPlaceholderAPI;
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

    private ActionBar actionBar;

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
        this.registerActionBar();

        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            new HookPlaceholderAPI(this).register();
            Bukkit.getConsoleSender().sendMessage(Messages.CC("&aPlaceholder API expansion successfully registered."));
        }

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
        this.getCommand("undisguiserank").setExecutor(new UnDisguiseRankCmd());
        this.getCommand("frozeddisguise").setExecutor(new FrozedDisguiseCmd());

        this.getServer().getConsoleSender().sendMessage(Messages.CC("&8 [&b*&8] &3Commands Registered Successfully"));
    }

    private boolean registerActionBar() {
        String version;

        try {
            version = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
        } catch (ArrayIndexOutOfBoundsException whatVersionAreYouUsingException) {
            return false;
        }

        getLogger().info("[Additional-Info] Your server is running version " + version);

        switch (version) {
            case "v1_8_R3":
                actionBar = new v1_8_R3();
                break;
            case "v1_9_R1":
                actionBar = new v1_9_R1();
                break;
            case "v1_9_R2":
                actionBar = new v1_9_R2();
                break;
            case "v1_10_R1":
                actionBar = new v1_10_R1();
                break;
            case "v1_11_R1":
                actionBar = new v1_11_R1();
                break;
            case "v1_12_R1":
                actionBar = new v1_12_R1();
                break;
            default:
                getLogger().info("ActionBar NMS won't be enabled.");
                getLogger().info("Currently supported versions: 1.8, 1.9, 1.10, 1.11, 1.12");
        }
        
        this.getServer().getConsoleSender().sendMessage(Messages.CC("&8 [&b*&8] &3ActionBar Registered Successfully"));

        return actionBar != null;
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

    public ActionBar getActionbar() {
        return actionBar;
    }

    @Override
    public FileConfiguration getConfig() {
        return YamlConfiguration.loadConfiguration(new File(this.getDataFolder(), "config.yml"));
    }
}
