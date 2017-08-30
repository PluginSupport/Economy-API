package support.plugin.economy.command.executors;

import org.bukkit.entity.Player;
import support.plugin.economy.command.BaseCommand;
import support.plugin.economy.command.BaseCommandInfo;

/**
 * Created by eric on 30/08/2017.
 */
@BaseCommandInfo(description = "Pay another user some money", usage = "<player> <amount>", aliases = {"pay", "p", "send"}, permission = "economy.command.pay", op = false)
public class PayCommand extends BaseCommand {

    @Override
    public void onCommand(Player p, String[] args) {


    }

}
