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
    private double balance;

    @Setter
    private Date creationDate;

    @Setter
    private boolean accountLimited;

    @Setter
    private double maximumTransaction;

    public Account(UUID accountHolder, double balance, Date creationDate, boolean limitation, double maximumTransaction) {
        this.accountHolder = accountHolder;
        this.balance = balance;
        this.creationDate = creationDate;
        this.accountLimited = limitation;
        this.maximumTransaction = maximumTransaction;
    }

    public UUID getAccountHolder() {
        return accountHolder;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(Double amount) {
        this.balance = amount;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public boolean getLimitation() {
        return accountLimited;
    }

    public double getMaximumTransaction() {
        return maximumTransaction;
    }

}
