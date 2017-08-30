package support.plugin.economy.command.executors;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import support.plugin.economy.Economy;
import support.plugin.economy.account.Account;
import support.plugin.economy.command.BaseCommand;
import support.plugin.economy.command.BaseCommandInfo;
import support.plugin.economy.transaction.Transaction;
import support.plugin.economy.transaction.builder.TransactionBuilder;
import support.plugin.economy.transaction.enums.TransactionStatus;

/**
 * Created by eric on 30/08/2017.
 */
@BaseCommandInfo(description = "Pay another user some money", usage = "<player> <amount>", aliases = {"pay", "p", "send"}, permission = "economy.command.pay", op = false)
public class PayCommand extends BaseCommand {

    private Economy instance;

    public PayCommand(Economy instance){
        this.instance = instance;
    }

    @Override
    public void onCommand(Player p, String[] args) {

        if(args.length < 2) {

            p.sendMessage(ChatColor.RED + "Invalid usage: /economy pay <player> <amount>");
            return;

        }


        if(Bukkit.getPlayer(args[0]) == null){

            p.sendMessage(ChatColor.RED + "That player is not online.");
            return;

        }

        Player target = Bukkit.getPlayer(args[0]);

        //Getting player profiles...

        Account playerAccount = instance.getAccountManager().getAccount(p.getUniqueId());

        if(playerAccount == null){

            p.sendMessage(ChatColor.RED + "You don't have an account! Please re-log!");
            return;

        }

        Account targetAccount = instance.getAccountManager().getAccount(target.getUniqueId());

        if(targetAccount == null){

            p.sendMessage(ChatColor.RED + "That player doesn't have an account!");
            return;

        }

        if(!isInteger(args[1])){

            p.sendMessage(ChatColor.RED + "The payment amount must be a number!");
            return;

        }

        int amount = Integer.parseInt(args[1]);

        if(amount < 1){

            p.sendMessage(ChatColor.RED + "The payment amount must be over $1!");
            return;

        }

        Transaction transaction = new TransactionBuilder()
                .generateId()
                .setSender(playerAccount)
                .setRecipient(targetAccount)
                .setAmount(amount)
                .setStatus(TransactionStatus.SUCCESS)
                .build();

        transaction.process();

    }

    public static boolean isInteger(String str) {
        if (str == null) {
            return false;
        }
        int length = str.length();
        if (length == 0) {
            return false;
        }
        int i = 0;
        if (str.charAt(0) == '-') {
            if (length == 1) {
                return false;
            }
            i = 1;
        }
        for (; i < length; i++) {
            char c = str.charAt(i);
            if (c < '0' || c > '9') {
                return false;
            }
        }
        return true;
    }

}
