package support.plugin.economy.account.builder;

import support.plugin.economy.account.Account;

import java.util.Date;
import java.util.UUID;

/**
 * Created by eric on 30/08/2017.
 */
public class AccountBuilder {

    private UUID accountHolder;

    private double balance;

    private Date creationDate;

    private boolean limitation;

    private double maximumTransaction;

    public AccountBuilder(UUID accountHolder) {

        this.accountHolder = accountHolder;

        balance = 0.00;
        creationDate = new Date();
        limitation = false;
        maximumTransaction = -1.0;

    }

    public AccountBuilder setBalance(double balance) {

        this.balance = balance;

        return this;

    }

    public AccountBuilder setDate(Date date) {

        this.creationDate = date;

        return this;

    }

    public AccountBuilder setLimited(boolean limited) {

        this.limitation = limited;

        return this;

    }

    public AccountBuilder setMaximumTransaction(double maximumTransaction) {

        this.maximumTransaction = maximumTransaction;

        return this;

    }

    public Account build() {

        return new Account(accountHolder, balance, creationDate, limitation, maximumTransaction);

    }

}
