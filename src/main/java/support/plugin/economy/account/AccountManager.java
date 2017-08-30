package support.plugin.economy.account;

import org.bukkit.event.Event;
import support.plugin.economy.Economy;
import support.plugin.economy.account.dao.AccountDao;
import support.plugin.economy.account.dto.IAccount;
import support.plugin.economy.account.events.AccountCreationEvent;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by eric on 30/08/2017.
 */
public class AccountManager {

    private Economy instance;

    private AccountDao accountDao;

    private List<IAccount> accounts;

    public AccountManager(Economy instance){

        this.instance = instance;

        this.accountDao = new AccountDao();

        accounts = this.accountDao.getAll(); // Loading all records, maybe not the best if there's loads of records. TODO: Load account on join, not all at once.

    }

    public List<IAccount> getAllAccounts(){

        return this.accountDao.getAll();  // Using this because when I use an event based account loading system, it will only load the online player's accounts.

    }

    public IAccount getAccount(UUID uuid){

        for(IAccount account : accounts){

            if(account.getAccountHolder() == uuid){
                return account;
            }

        }

        return null;

    }

    public void closeAccount(IAccount account){

        if(accounts.contains(account)){
            this.accounts.remove(account);
            this.accountDao.delete(account);
        }

    }

    public void createAccount(IAccount account){

        AccountCreationEvent creationEvent = new AccountCreationEvent(account, new Date());

        if(creationEvent.isCancelled()){
            return;
        }

        if(!accounts.contains(account)){
            this.accounts.add(account);
            this.accountDao.insert(account);
        }else{
            creationEvent.setCancelled(true);
        }

    }

}
