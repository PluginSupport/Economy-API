package support.plugin.economy.command.executors.admin;

import org.bukkit.entity.Player;
import support.plugin.economy.Economy;
import support.plugin.economy.command.BaseCommand;
import support.plugin.economy.command.BaseCommandInfo;

/**
 * Created by eric on 30/08/2017.
 */
@BaseCommandInfo(description = "Restrict a user's ability to make payments", usage = "<player>", aliases = {"limit", "restrict"}, permission = "economy.command.admin.limit", op = false)
public class LimitAccountCommand extends BaseCommand {

    private Economy instance;

    public LimitAccountCommand(Economy instance){
        this.instance = instance;
    }

    @Override
    public void onCommand(Player p, String[] args) {



    }

}
