package support.plugin.economy.transaction.enums;

/**
 * Created by eric on 30/08/2017.
 */
public enum TransactionStatus {

    SUCCESS,
    FAILED_INVALID_AMOUNT,
    FAILED_NO_FUNDS,
    FAILED_LIMIT_REACHED,
    FAILED_SINGLE_LIMIT,
    SENDER_LIMITED,
    RECIPIENT_LIMITED,
    CANCELLED;

}
