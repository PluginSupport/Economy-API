package support.plugin.economy.transaction.dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import redis.clients.jedis.Jedis;
import support.plugin.economy.account.dto.IAccount;
import support.plugin.economy.transaction.Transaction;

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

    public TransactionDao(String hostname, String port, String auth) {

        gson = new GsonBuilder().setPrettyPrinting().create();
        jedis = new Jedis(hostname, Integer.parseInt(port), 3000);

        if (!(auth == null)) {
            jedis.auth(auth);
        }

    }

    public void insert(Transaction transaction) {

        try (Jedis conn = jedis) {

            conn.set(getKey(transaction), gson.toJson(transaction));

        }

    }

    public void update(Transaction transaction) {

        try (Jedis conn = jedis) {

            conn.set(getKey(transaction), gson.toJson(transaction));

        }

    }

    public void delete(Transaction transaction) {

        try (Jedis conn = jedis) {

            conn.del(getKey(transaction));

        }

    }

    public List<Transaction> getAll() {

        try (Jedis conn = jedis) {

            return conn.keys("econ:transactions:*").stream().map(k -> gson.fromJson(conn.get(k), Transaction.class)).collect(Collectors.toList());

        }

    }

    public Transaction getById(UUID id) {

        for (Transaction transaction : getAll()) {

            if (transaction.getId() == id) {
                return transaction;
            }

        }

        return null;

    }

    public List<Transaction> getTransactionsBySender(IAccount account) {

        List<Transaction> transactions = new ArrayList<>();

        for (Transaction transaction : getAll()) {

            if (transaction.getSender() == account) {
                transactions.add(transaction);
            }

        }

        return transactions;

    }

    public List<Transaction> getTransactionsByRecipient(IAccount account) {

        List<Transaction> transactions = new ArrayList<>();

        for (Transaction transaction : getAll()) {

            if (transaction.getRecipient() == account) {
                transactions.add(transaction);
            }

        }

        return transactions;

    }

    public String getKey(Transaction transaction) {
        return "econ:transactions:" + transaction.getId().toString();
    }

}
