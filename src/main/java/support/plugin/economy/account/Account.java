package support.plugin.economy.account;

import lombok.Setter;
import support.plugin.economy.account.dto.IAccount;

import java.util.Date;
import java.util.UUID;

/**
 * Created by eric on 30/08/2017.
 */
public class Account implements IAccount {

    @Setter
    private UUID accountHolder;

    @Setter
    private Double balance;

    @Setter
    private Date creationDate;

    public Account(UUID accountHolder, Double balance, Date creationDate){
        this.accountHolder = accountHolder;
        this.balance = balance;
        this.creationDate = creationDate;
    }

    public UUID getAccountHolder(){
        return accountHolder;
    }

    public Double getBalance(){
        return balance;
    }

}
