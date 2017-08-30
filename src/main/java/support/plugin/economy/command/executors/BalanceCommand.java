package support.plugin.economy.command.executors;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import support.plugin.economy.Economy;
import support.plugin.economy.account.Account;
import support.plugin.economy.command.BaseCommand;
import support.plugin.economy.command.BaseCommandInfo;

/**
 * Created by eric on 30/08/2017.
 */
@BaseCommandInfo(description = "View your account balance", usage = "[player]", aliases = {"balance", "bal"}, permission = "economy.command.balance", op = false)
public class BalanceCommand extends BaseCommand {

    private Economy instance;

    public BalanceCommand(Economy instance){
        this.instance = instance;
    }

    @Override
    public void onCommand(Player p, String[] args) {

        if(args.length == 0){

            Account account = instance.getAccountManager().getAccount(p.getUniqueId());

            if(account != null){

                p.sendMessage(ChatColor.GREEN + "Your balance is $"+account.getBalance());

            }else{

                p.sendMessage(ChatColor.RED + "You don't have an account.");

            }

        }else{

            if(!p.hasPermission("economy.command.balance.others")){

                p.sendMessage(ChatColor.RED + "You don't have permission to view other players balances.");
                return;

            }

            if(Bukkit.getPlayer(args[0]) == null){

                p.sendMessage(ChatColor.RED + args[0]+ " is offline, you cannot see their balance.");
                return;

            }

            Player target = Bukkit.getPlayer(args[0]);

            Account account = instance.getAccountManager().getAccount(p.getUniqueId());

            if(account != null){

                p.sendMessage(ChatColor.GREEN + "Your balance is $"+account.getBalance());

            }else{

                p.sendMessage(ChatColor.RED + "That player doesn't have an account.");

            }

        }

    }

}
