package support.plugin.economy.transaction;

import support.plugin.economy.Economy;
import support.plugin.economy.transaction.dao.TransactionDao;
import support.plugin.economy.transaction.dto.ITransaction;
import support.plugin.economy.transaction.events.TransactionEvent;

import java.util.List;
import java.util.UUID;

/**
 * Created by eric on 30/08/2017.
 */
public class TransactionManager {

    private Economy instance;

    private TransactionDao transactionDao;

    private List<ITransaction> transactions;

    public TransactionManager(Economy instance){
        this.instance = instance;

        this.transactionDao = new TransactionDao();

        transactions = transactionDao.getAll();
    }

    public List<ITransaction> getAllTransactions(){

        return transactions;

    }

    public ITransaction getTransaction(UUID id){

        for(ITransaction transaction : transactions){

            if(transaction.getId() == id){
                return transaction;
            }

        }

        return null;

    }

    public void deleteTransaction(ITransaction transaction){

        if(transactions.contains(transaction)){

            transactions.remove(transaction);
            transactionDao.delete(transaction);

        }

    }

    public void createTransaction(ITransaction transaction){

        TransactionEvent transactionEvent = new TransactionEvent(transaction);

        if(!transactions.contains(transaction)){
            transactions.add(transaction);
            transactionDao.insert(transaction);
        }

    }

}
