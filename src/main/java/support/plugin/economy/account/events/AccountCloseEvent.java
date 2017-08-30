package support.plugin.economy.account.events;

import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import support.plugin.economy.account.dto.IAccount;

import java.util.Date;

/**
 * Created by eric on 30/08/2017.
 */
public class AccountCloseEvent extends Event implements Cancellable {

    private HandlerList handlers = new HandlerList();
    private boolean cancelled;

    private IAccount account;

    private Date creationDate;

    public AccountCloseEvent(IAccount account, Date creationDate){
        this.account = account;
        this.creationDate = creationDate;
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
