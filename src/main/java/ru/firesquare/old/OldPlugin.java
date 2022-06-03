package ru.firesquare.old;

import net.luckperms.api.LuckPerms;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import redempt.redlib.config.ConfigManager;
import ru.firesquare.old.config.Config;
import ru.firesquare.old.config.Messages;
import ru.firesquare.old.listeners.PlayerJoinListener;

public class OldPlugin extends JavaPlugin {
    @Override
    public void onEnable () {
        // Load config
        ConfigManager.create(this).target(Config.class).saveDefaults().load();
        ConfigManager.create(this, "lang.yml").target(Messages.class).saveDefaults().load();

        // Load messages
        reloadFileConfig();

        // Set static instance
        OldPlugin.instance = this;
        
        // Register listeners
        this.getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);

        // Setup Vault
        setupPermissions();
    }

    public void reloadFileConfig() {
        ConfigManager.create(this, "lang.yml").target(Messages.class).saveDefaults().reload();
        ConfigManager.create(this).target(Config.class).saveDefaults().reload();
    }

    private static LuckPerms perms = null;

    private void setupPermissions() {
        RegisteredServiceProvider<LuckPerms> provider = Bukkit.getServicesManager().getRegistration(LuckPerms.class);
        if (provider != null) {
            perms = provider.getProvider();
        }
    }

    public static LuckPerms getPermissions() {
        return perms;
    }

    private static OldPlugin instance;

    public static OldPlugin getInstance () {
        return OldPlugin.instance;
    }
}