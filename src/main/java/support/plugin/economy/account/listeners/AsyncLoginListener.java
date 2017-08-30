package support.plugin.economy.account.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import support.plugin.economy.Economy;
import support.plugin.economy.account.Account;
import support.plugin.economy.account.builder.AccountBuilder;

import java.util.Date;

/**
 * Created by eric on 30/08/2017.
 */
public class AsyncLoginListener implements Listener {

    private Economy instance;

    public AsyncLoginListener(Economy instance) {

        this.instance = instance;

    }

    @EventHandler
    public void asyncJoin(AsyncPlayerPreLoginEvent e) {

        Account account = instance.getAccountManager().getAccount(e.getUniqueId());

        if (account == null) {

            account = instance.getAccountManager().getAccountDao().getAccount(e.getUniqueId());

            if (account == null) {

                instance.getAccountManager().createAccount(new AccountBuilder(e.getUniqueId()).setBalance(200.00).setDate(new Date()).setLimited(false).setMaximumTransaction(100000).build());

            } else {

                instance.getAccountManager().createAccount(account);

            }

        }

    }

}
