package support.plugin.economy.transaction.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import support.plugin.economy.transaction.Transaction;

/**
 * Created by eric on 30/08/2017.
 */
public class TransactionEvent extends Event implements Cancellable {

    private HandlerList handlers = new HandlerList();
    private boolean cancelled;

    private Transaction transaction;
    private Player sender;
    private Player recipient;


    public TransactionEvent(Transaction transaction) {
        this.transaction = transaction;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public Player getSender() {

        if (Bukkit.getPlayer(transaction.getSender().getAccountHolder()) != null) {

            return Bukkit.getPlayer(transaction.getSender().getAccountHolder());

        }

        return null;

    }

    public Player getRecipient() {

        if (Bukkit.getPlayer(transaction.getRecipient().getAccountHolder()) != null) {

            return Bukkit.getPlayer(transaction.getRecipient().getAccountHolder());

        }

        return null;

    }

}
