package support.plugin.economy.account.builder;

import support.plugin.economy.account.Account;

import java.util.Date;
import java.util.UUID;

/**
 * Created by eric on 30/08/2017.
 */
public class AccountBuilder {

    private UUID accountHolder;

    private Double balance;

    private Date creationDate;

    public AccountBuilder(UUID accountHolder){

        this.accountHolder = accountHolder;

    }

    public AccountBuilder setBalance(Double balance){

        this.balance = balance;

        return this;

    }

    public AccountBuilder setDate(Date date){

        this.creationDate = date;

        return this;

    }

    public Account build(){

        return new Account(accountHolder, balance,creationDate);

    }

}
