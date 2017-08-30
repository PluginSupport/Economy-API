package support.plugin.economy.account.dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import redis.clients.jedis.Jedis;
import support.plugin.economy.account.Account;
import support.plugin.economy.account.dto.IAccount;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Created by eric on 30/08/2017.
 */
public class AccountDao {

    private final Gson gson;
    private final Jedis jedis;

    public AccountDao(String hostname, String port, String auth) {

        gson = new GsonBuilder().setPrettyPrinting().create();
        jedis = new Jedis(hostname, Integer.parseInt(port), 3000);

        if (!(auth == null)) {
            jedis.auth(auth);
        }

    }

    public void insert(IAccount account) {

        try (Jedis conn = jedis) {

            conn.set(getKey(account), gson.toJson(account));

        }

    }

    public void update(IAccount account) {

        try (Jedis conn = jedis) {

            conn.set(getKey(account), gson.toJson(account));

        }

    }

    public void delete(IAccount account) {

        try (Jedis conn = jedis) {

            conn.del(getKey(account));

        }

    }

    public List<Account> getAll() {

        try (Jedis conn = jedis) {

            return conn.keys("econ:accounts:*").stream().map(k -> gson.fromJson(conn.get(k), Account.class)).collect(Collectors.toList());

        }

    }

    public Account getAccount(UUID uuid) {

        for (Account account : getAll()) {

            if (account.getAccountHolder() == uuid) {
                return account;
            }

        }

        return null;

    }

    public String getKey(IAccount account) {
        return "econ:accounts:" + account.getAccountHolder().toString();
    }

}
