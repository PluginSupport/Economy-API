package support.plugin.economy.transaction.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import support.plugin.economy.transaction.enums.TransactionStatus;
import support.plugin.economy.transaction.events.TransactionEvent;

/**
 * Created by eric on 30/08/2017.
 */
public class TransactionListener implements Listener {

    @EventHandler
    public void onTransaction(TransactionEvent e) {

        if (e.getSender() == null) {
            return;
        }

        Player sender = e.getSender();

        if (e.getRecipient() == null) {
            sender.sendMessage(ChatColor.RED + "That player is offline!");
            e.setCancelled(true);
            return;
        }

        TransactionStatus status = e.getTransaction().getStatus();

        switch (status) {
            case FAILED_LIMIT_REACHED:
                sender.sendMessage(ChatColor.RED + "That player has already reached their account balance limit.");
                e.setCancelled(true);
                return;
            case FAILED_NO_FUNDS:
                sender.sendMessage(ChatColor.RED + "You do not have the funds to send $" + e.getTransaction().getAmount() + ".");
                e.setCancelled(true);
                return;
            case FAILED_INVALID_AMOUNT:
                sender.sendMessage(ChatColor.RED + "You tried to send an invalid amount.");
                e.setCancelled(true);
                return;
            case CANCELLED:
                sender.sendMessage(ChatColor.RED + "Transaction has been cancelled, the system has detected an issue.");
                e.setCancelled(true);
                return;
            case SENDER_LIMITED:
                sender.sendMessage(ChatColor.RED + "Your account has been limited, you are not able to send or receive money.");
                e.setCancelled(true);
                return;
            case RECIPIENT_LIMITED:
                sender.sendMessage(ChatColor.RED + e.getSender().getName() + " is limited, you may not send them money.");
                e.setCancelled(true);
                return;
            case FAILED_SINGLE_LIMIT:
                sender.sendMessage(ChatColor.RED + "You cannot send that amount. Your maximum-per-transaction is " + e.getTransaction().getSender().getMaximumTransaction() + ".");
                e.setCancelled(true);
                return;
            case SUCCESS:
                e.setCancelled(false);
                sender.sendMessage(ChatColor.GREEN + "You have sent $" + e.getTransaction().getAmount() + " to " + e.getRecipient().getName() + ".");
        }

    }

}
