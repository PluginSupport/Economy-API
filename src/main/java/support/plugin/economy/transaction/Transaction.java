package support.plugin.economy.transaction;

import support.plugin.economy.account.Account;
import support.plugin.economy.transaction.dto.ITransaction;
import support.plugin.economy.transaction.enums.TransactionStatus;
import support.plugin.economy.transaction.events.TransactionEvent;

import java.util.Date;
import java.util.UUID;

/**
 * Created by eric on 30/08/2017.
 */
public class Transaction implements ITransaction {

    private UUID id;

    private Account sender;

    private Account recipient;

    private double amount;

    private TransactionStatus status;

    private Date date;

    public Transaction(UUID id, Account sender, Account recipient, double amount, Date date, TransactionStatus transactionResult) {
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

    public Account getSender() {
        return sender;
    }

    public void setSender(Account sender) {
        this.sender = sender;
    }

    public Account getRecipient() {
        return recipient;
    }

    public void setRecipient(Account recipient) {
        this.recipient = recipient;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
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

    public void process() {

        //Player player = Bukkit.getPlayer(sender.getAccountHolder());

        setStatus(TransactionStatus.SUCCESS);

        if (amount <= 0) {
            setStatus(TransactionStatus.FAILED_INVALID_AMOUNT);
        }

        if (sender.getBalance() <= 0) {
            setStatus(TransactionStatus.FAILED_NO_FUNDS);
        }

        if ((sender.getBalance() - amount) <= 0) {
            setStatus(TransactionStatus.FAILED_NO_FUNDS);
        }

        if (recipient.getBalance() >= 999999999) {
            setStatus(TransactionStatus.FAILED_LIMIT_REACHED);
        }

        if ((recipient.getBalance() + amount) >= 999999999) {
            setStatus(TransactionStatus.FAILED_LIMIT_REACHED);
        }

        if (sender.getLimitation()) {
            setStatus(TransactionStatus.SENDER_LIMITED);
        }

        if (recipient.getLimitation()) {
            setStatus(TransactionStatus.RECIPIENT_LIMITED);
        }

        if (amount > sender.getMaximumTransaction()) {
            setStatus(TransactionStatus.FAILED_SINGLE_LIMIT);
        }

        TransactionEvent transactionEvent = new TransactionEvent(this);

        if (!transactionEvent.isCancelled()) {
            handleTransfer();
        } else {
            setStatus(TransactionStatus.CANCELLED);
        }

    }

    private void handleTransfer() {

        sender.setBalance(sender.getBalance() - amount);
        recipient.setBalance(recipient.getBalance() + amount);

    }

}
