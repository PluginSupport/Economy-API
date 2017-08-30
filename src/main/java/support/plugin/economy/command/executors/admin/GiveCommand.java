package support.plugin.economy.command.executors.admin;

import org.bukkit.entity.Player;
import support.plugin.economy.command.BaseCommand;
import support.plugin.economy.command.BaseCommandInfo;

/**
 * Created by eric on 30/08/2017.
 */
@BaseCommandInfo(description = "Give a user funds", usage = "<player> <amount>", aliases = {"g"}, permission = "economy.command.admin.give", op = false)
public class GiveCommand extends BaseCommand {

    @Override
    public void onCommand(Player p, String[] args) {


    }

}
