package support.plugin.economy.transaction.dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import redis.clients.jedis.Jedis;
import support.plugin.economy.account.Account;
import support.plugin.economy.account.dto.IAccount;
import support.plugin.economy.transaction.Transaction;
import support.plugin.economy.transaction.dto.ITransaction;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Created by eric on 30/08/2017.
 */
public class TransactionDao {

    private final Gson gson;
    private final Jedis jedis;

    public TransactionDao(){

        gson = new GsonBuilder().setPrettyPrinting().create();
        jedis = new Jedis();

    }

    public void insert(ITransaction transaction){

        try(Jedis conn = jedis){

            conn.set(getKey(transaction), gson.toJson(transaction));

        }

    }

    public void update(ITransaction transaction){

        try(Jedis conn = jedis){

            conn.set(getKey(transaction), gson.toJson(transaction));

        }

    }

    public void delete(ITransaction transaction){

        try(Jedis conn = jedis){

            conn.del(getKey(transaction));

        }

    }

    public List<ITransaction> getAll(){

        try(Jedis conn = jedis){

            return conn.keys("econ:transactions:*").stream().map(k -> gson.fromJson(conn.get(k), Transaction.class)).collect(Collectors.toList());

        }

    }

    public ITransaction getById(UUID id){

        for(ITransaction transaction : getAll()){

            if(transaction.getId() == id){
                return transaction;
            }

        }

        return null;

    }

    public List<ITransaction> getTransactionsBySender(IAccount account){

        List<ITransaction> transactions = new ArrayList<>();

        for(ITransaction transaction : getAll()){

            if(transaction.getSender() == account){
                transactions.add(transaction);
            }

        }

        return transactions;

    }

    public List<ITransaction> getTransactionsByRecipient(IAccount account){

        List<ITransaction> transactions = new ArrayList<>();

        for(ITransaction transaction : getAll()){

            if(transaction.getRecipient() == account){
                transactions.add(transaction);
            }

        }

        return transactions;

    }

    public String getKey(ITransaction transaction){
        return "econ:transactions:"+transaction.getId().toString();
    }

}
