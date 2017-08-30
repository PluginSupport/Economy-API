package support.plugin.economy.command.executors;

import org.bukkit.entity.Player;
import support.plugin.economy.command.BaseCommand;
import support.plugin.economy.command.BaseCommandInfo;

/**
 * Created by eric on 30/08/2017.
 */
@BaseCommandInfo(description = "View your account balance", usage = "[player]", aliases = {"balance", "bal"}, permission = "economy.command.balance", op = false)
public class BalanceCommand extends BaseCommand {

    @Override
    public void onCommand(Player p, String[] args) {


    }

}
