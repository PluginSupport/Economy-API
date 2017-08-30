package support.plugin.economy.transaction;

import lombok.Getter;
import support.plugin.economy.Economy;
import support.plugin.economy.transaction.dao.TransactionDao;

import java.util.List;
import java.util.UUID;

/**
 * Created by eric on 30/08/2017.
 */
public class TransactionManager {

    private Economy instance;

    @Getter
    private TransactionDao transactionDao;

    private List<Transaction> transactions;

    public TransactionManager(Economy instance) {
        this.instance = instance;

        this.transactionDao = new TransactionDao(instance.hostname, instance.port, instance.password);

        transactions = transactionDao.getAll();
    }

    public List<Transaction> getAllTransactions() {

        return transactions;

    }

    public Transaction getTransaction(UUID id) {

        for (Transaction transaction : transactions) {

            if (transaction.getId() == id) {
                return transaction;
            }

        }

        return null;

    }

    public void deleteTransaction(Transaction transaction) {

        if (transactions.contains(transaction)) {

            transactions.remove(transaction);
            transactionDao.delete(transaction);

        }

    }

    public void createTransaction(Transaction transaction) {

        if (!transactions.contains(transaction)) {
            transactions.add(transaction);
            transactionDao.insert(transaction);
        }

    }

    public void updateAll() {

        for (Transaction transaction : transactions) {

            transactionDao.update(transaction);

        }

    }

}
