package support.plugin.economy.transaction.builder;

import support.plugin.economy.account.Account;
import support.plugin.economy.transaction.Transaction;
import support.plugin.economy.transaction.enums.TransactionStatus;

import java.util.Date;
import java.util.UUID;

/**
 * Created by eric on 30/08/2017.
 */
public class TransactionBuilder {

    private UUID id;

    private Account sender;

    private Account recipient;

    private double amount;

    private TransactionStatus status;

    private Date date;

    public TransactionBuilder() {
    }

    public TransactionBuilder generateId() {

        id = UUID.randomUUID();

        return this;

    }

    public TransactionBuilder setSender(Account sender) {

        this.sender = sender;

        return this;

    }

    public TransactionBuilder setRecipient(Account recipient) {

        this.recipient = recipient;

        return this;

    }

    public TransactionBuilder setAmount(double amount) {

        this.amount = amount;

        return this;

    }

    public TransactionBuilder setStatus(TransactionStatus status) {

        this.status = status;

        return this;

    }

    public TransactionBuilder setDate(Date date) {

        this.date = date;

        return this;

    }

    public Transaction build() {

        return new Transaction(id, sender, recipient, amount, date, status);

    }

}
