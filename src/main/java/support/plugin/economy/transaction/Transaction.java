package support.plugin.economy.transaction;

import support.plugin.economy.account.dto.IAccount;
import support.plugin.economy.transaction.dto.ITransaction;
import support.plugin.economy.transaction.enums.TransactionStatus;

import java.util.Date;
import java.util.UUID;

/**
 * Created by eric on 30/08/2017.
 */
public class Transaction implements ITransaction {

    private UUID id;

    private IAccount sender;

    private IAccount recipient;

    private Double amount;

    private TransactionStatus status;

    private Date date;

    public Transaction(UUID id, IAccount sender, IAccount recipient, Double amount, Date date, TransactionStatus transactionResult){
        this.id = id;
        this.sender = sender;
        this.recipient = recipient;
        this.amount = amount;
        this.status = transactionResult;
        this.date = date;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public IAccount getSender() {
        return sender;
    }

    public void setSender(IAccount sender) {
        this.sender = sender;
    }

    public IAccount getRecipient() {
        return recipient;
    }

    public void setRecipient(IAccount recipient) {
        this.recipient = recipient;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public TransactionStatus getStatus() {
        return status;
    }

    public void setStatus(TransactionStatus status) {
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
