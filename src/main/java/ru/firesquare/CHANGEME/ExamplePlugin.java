package ru.firesquare.CHANGEME;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import redempt.redlib.commandmanager.CommandParser;
import redempt.redlib.config.ConfigManager;
import redempt.redlib.dev.ChainCommand;
import redempt.redlib.dev.StructureTool;
import ru.firesquare.CHANGEME.commands.ExampleCommand;
import ru.firesquare.CHANGEME.config.Config;
import ru.firesquare.CHANGEME.config.Messages;
import ru.firesquare.CHANGEME.listeners.PlayerJoinListener;
import ru.firesquare.CHANGEME.sql.Player;

import java.sql.SQLException;

public class ExamplePlugin extends JavaPlugin {
    @Override
    public void onEnable () {
        // Load config
        ConfigManager.create(this).target(Config.class).saveDefaults().load();
        ConfigManager.create(this, "lang.yml").target(Messages.class).saveDefaults().load();

        // Load messages
        reloadFileConfig();

        // Set static instance
        ExamplePlugin.instance = this;

        // Load and init SQL
        initSQL();

        // Register the commands
        ChainCommand chain = new ChainCommand();
        new CommandParser(this.getResource("command.rdcml"))
                .parse()
                .register("example", new ExampleCommand(), StructureTool.enable(), chain);
        
        // Register listeners
        this.getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);

        // Setup Vault
        setupPermissions();
    }

    private Dao<Player, String> playerDao;

    public void initSQL() {
        try {
            ConnectionSource connectionSource = new JdbcConnectionSource(Config.database);

            // instantiate the dao's
            playerDao = DaoManager.createDao(connectionSource, Player.class);

            // create tables
            TableUtils.createTableIfNotExists(connectionSource, Player.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void reloadFileConfig() {
        ConfigManager.create(this, "lang.yml").target(Messages.class).saveDefaults().reload();
        ConfigManager.create(this).target(Config.class).saveDefaults().reload();
    }

    private static Permission perms = null;

    private void setupPermissions() {
        RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
        assert rsp != null;
        perms = rsp.getProvider();
    }

    public static Permission getPermissions() {
        return perms;
    }

    private static ExamplePlugin instance;

    public static ExamplePlugin getInstance () {
        return ExamplePlugin.instance;
    }

    public Dao<Player, String> getPlayerDao() {
        return playerDao;
    }
}