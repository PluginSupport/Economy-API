package support.plugin.economy.transaction.events;

import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import support.plugin.economy.account.dto.IAccount;
import support.plugin.economy.transaction.dto.ITransaction;

import java.util.Date;

/**
 * Created by eric on 30/08/2017.
 */
public class TransactionEvent extends Event implements Cancellable {

    private HandlerList handlers = new HandlerList();
    private boolean cancelled;

    private ITransaction transaction;


    public TransactionEvent(ITransaction transaction){
        this.transaction = transaction;
    }

    public HandlerList getHandlers(){
        return handlers;
    }

    public boolean isCancelled(){
        return cancelled;
    }

    public void setCancelled(boolean cancelled){
        this.cancelled = cancelled;
    }

}
