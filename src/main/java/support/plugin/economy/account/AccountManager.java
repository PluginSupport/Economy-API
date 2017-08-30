package support.plugin.economy.account;

import lombok.Getter;
import support.plugin.economy.Economy;
import support.plugin.economy.account.dao.AccountDao;
import support.plugin.economy.account.events.AccountCreationEvent;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by eric on 30/08/2017.
 */
public class AccountManager {

    private Economy instance;

    @Getter
    private AccountDao accountDao;

    private List<Account> accounts;

    public AccountManager(Economy instance) {

        this.instance = instance;

        this.accountDao = new AccountDao(instance.hostname, instance.port, instance.password);

        accounts = new ArrayList<>(); //this.accountDao.getAll(); // Loading all records, maybe not the best if there's loads of records. TODO: Load account on join, not all at once.

    }

    public List<Account> getAllAccounts() {

        return this.accountDao.getAll();  // Using this because when I use an event based account loading system, it will only load the online player's accounts.

    }

    public Account getAccount(UUID uuid) {

        for (Account account : accounts) {

            if (account.getAccountHolder() == uuid) {
                return account;
            }

        }

        return null;

    }

    public void closeAccount(Account account) {

        if (accounts.contains(account)) {
            this.accounts.remove(account);
            this.accountDao.delete(account);
        }

    }

    public void createAccount(Account account) {

        AccountCreationEvent creationEvent = new AccountCreationEvent(account, new Date());

        if (creationEvent.isCancelled()) {
            return;
        }

        if (!accounts.contains(account)) {
            this.accounts.add(account);
            this.accountDao.insert(account);
        } else {
            creationEvent.setCancelled(true);
        }

    }

    public void updateAll() {

        for (Account account : accounts) {

            accountDao.update(account);

        }

    }

}
