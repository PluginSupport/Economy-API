package support.plugin.economy.transaction.dto;

import support.plugin.economy.account.Account;
import support.plugin.economy.transaction.enums.TransactionStatus;

import java.util.Date;
import java.util.UUID;

/**
 * Created by eric on 30/08/2017.
 */
public interface ITransaction {

    UUID getId();

    Account getSender();

    Account getRecipient();

    double getAmount();

    TransactionStatus getStatus();

    Date getDate();

}
