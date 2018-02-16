package support.plugin.economy;

import lombok.Getter;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import support.plugin.economy.account.AccountManager;
import support.plugin.economy.account.listeners.AsyncLoginListener;
import support.plugin.economy.command.BaseCommandManager;
import support.plugin.economy.transaction.TransactionManager;
import support.plugin.economy.transaction.listeners.TransactionListener;

/**
 * Created by eric on 30/08/2017.
 */
public class Economy extends JavaPlugin {

    @Getter
    private static Economy instance;
    @Getter
    public String hostname, port, password;
    @Getter
    private AccountManager accountManager;
    @Getter
    private TransactionManager transactionManager;

    public void onEnable() {
        instance = this;

        // TODO: Load from config file
        hostname = "127.0.0.1";
        port = "6379";
        password = null;

        accountManager = new AccountManager(this);
        transactionManager = new TransactionManager(this);

        loadCommands();
        loadListeners();
    }

    public void onDisable() {

        accountManager.updateAll();
        transactionManager.updateAll();

        instance = null; // Make those skids happy

    }

    private void loadCommands() {
        getCommand("economy").setExecutor(new BaseCommandManager());
    }

    private void loadListeners() {

        PluginManager pm = getServer().getPluginManager();

        pm.registerEvents(new AsyncLoginListener(this), this);
        pm.registerEvents(new TransactionListener(), this);

    }

}
