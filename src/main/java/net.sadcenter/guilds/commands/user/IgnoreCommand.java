package net.sadcenter.guilds.commands.user;

import net.sadcenter.commons.command.Command;
import net.sadcenter.commons.command.CommandBuilder;
import net.sadcenter.commons.command.CommandException;
import net.sadcenter.commons.exceptions.impl.NotEnoughArgumentsException;
import net.sadcenter.commons.helper.ChatHelper;
import net.sadcenter.guilds.basic.User;
import net.sadcenter.guilds.utils.UserUtil;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author sadcenter on 17.08.2020
 * @project LASTCORE
 */

public class IgnoreCommand extends Command {

    public IgnoreCommand() {
        super("ignore", true);
    }

    @Override
    public void onCommand(CommandSender executor, CommandBuilder commandBuilder, String... args) throws CommandException {
        Player player = (Player) executor;
        commandBuilder
                .newArgumentsBuilder()
                .throwError(1, new NotEnoughArgumentsException("ignore [nick]"));

        User u = UserUtil.get(player.getUniqueId());
        if (args[0].equals("all")) {
            u.setIgnoreAll(!u.isIgnoreAll());
            ChatHelper.sendMessage(player, "&a" + (u.isIgnoreAll() ? "Wlaczyles" : "Wylaczyles") + " mozliwosc pisania do ciebie!");
            return;
        }
        if (args[0].equalsIgnoreCase("add")) {
            if (args.length == 1) {
                ChatHelper.sendMessage(player, "&cNapisz nick gracza ktorego chcesz ignorowac");
                return;
            }
            String nick = args[1];
            if (!u.getIgnores().remove(nick)) {
                u.getIgnores().add(nick);
            }

            ChatHelper.sendMessage(player, "&a" + (u.getIgnores().contains(nick) ? "Zaczales" : "Przestales") + " ignorowac &3" + nick);
        }
    }
}
