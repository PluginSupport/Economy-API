package support.plugin.economy.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by eric on 30/08/2017.
 */
public class BaseCommandManager implements CommandExecutor {

    public ArrayList<BaseCommand> commands;

    public BaseCommandManager() {
        commands = new ArrayList<>();


    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Only players are able to use the Economy command!");
            return true;
        }

        Player p = (Player) sender;

        if (cmd.getName().equalsIgnoreCase("economy")) {
            if (args.length == 0) {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e&lEconomy &7by PluginSupport"));
                p.sendMessage(" ");
                for (BaseCommand baseCommand : commands) {
                    BaseCommandInfo info = baseCommand.getClass().getAnnotation(BaseCommandInfo.class);
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', " &7- &e/economy (&7" + info.usage() + ")"));
                }
                p.sendMessage(" ");
                return true;
            }

            BaseCommand commandWanted = null;

            for (BaseCommand baseCommand : commands) {
                BaseCommandInfo info = baseCommand.getClass().getAnnotation(BaseCommandInfo.class);
                for (String alias : info.aliases()) {
                    if (alias.equals(args[0])) {
                        commandWanted = baseCommand;
                        break;
                    }
                }
            }

            if (commandWanted == null) {
                p.sendMessage(ChatColor.RED + "Could not find sub-command.");
                return true;
            }

            if (commandWanted.getClass().getAnnotation(BaseCommandInfo.class).op() && !p.isOp()) {
                p.sendMessage(ChatColor.RED + "You don't have permission to use this command.");
                return true;
            }

            if (!(p.hasPermission(commandWanted.getClass().getAnnotation(BaseCommandInfo.class).permission()))) {
                p.sendMessage(ChatColor.RED + "You don't have permission to use this command.");
                return true;
            }

            ArrayList<String> newArgs = new ArrayList<String>();
            Collections.addAll(newArgs, args);
            newArgs.remove(0);
            args = newArgs.toArray(new String[newArgs.size()]);

            commandWanted.onCommand(p, args);
        }
        return true;
    }
}
