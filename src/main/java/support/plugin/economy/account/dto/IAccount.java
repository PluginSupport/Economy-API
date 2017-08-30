package support.plugin.economy.account.dto;

import java.util.UUID;

/**
 * Created by eric on 30/08/2017.
 */
public interface IAccount {

    UUID getAccountHolder();

    Double getBalance();

}
