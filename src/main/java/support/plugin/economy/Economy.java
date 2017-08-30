package support.plugin.economy;

import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;
import support.plugin.economy.account.AccountManager;
import support.plugin.economy.transaction.TransactionManager;
import support.plugin.economy.transaction.builder.TransactionBuilder;

/**
 * Created by eric on 30/08/2017.
 */
public class Economy extends JavaPlugin {

    @Getter
    public static Economy instance;

    @Getter
    private AccountManager accountManager;

    @Getter
    private TransactionManager transactionManager;

    public void onEnable(){
        instance = this;

        accountManager = new AccountManager(this);
        transactionManager = new TransactionManager(this);

        loadDatabase();
        loadCommands();
        loadListeners();
    }

    public void onDisable(){
        instance = null; // Make those skids happy
    }

    public void loadDatabase(){



    }

    public void loadCommands(){

    }

    public void loadListeners(){

    }

}
