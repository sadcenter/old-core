package net.sadcenter.guilds.commands.user;

import net.sadcenter.commons.command.Command;
import net.sadcenter.commons.command.CommandBuilder;
import net.sadcenter.commons.command.CommandException;
import net.sadcenter.commons.exceptions.impl.NotEnoughArgumentsException;
import net.sadcenter.commons.helper.ChatHelper;
import net.sadcenter.guilds.basic.User;
import net.sadcenter.guilds.utils.UserUtil;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author sadcenter on 17.08.2020
 * @project LASTCORE
 */

public class ReplyCommand extends Command {

    public ReplyCommand() {
        super("r", true);
    }

    @Override
    public void onCommand(CommandSender executor, CommandBuilder commandBuilder, String... args) throws CommandException {
        commandBuilder
                .newArgumentsBuilder()
                .throwError(1, new NotEnoughArgumentsException("r [wiadomosc]"));

        Player player = (Player) executor;

        final String message = StringUtils.join(args, " ", 0, args.length);
        final User u = UserUtil.get(player.getUniqueId());
        if (u.getReplyPlayer().equals("null")) {
            ChatHelper.sendMessage(player, "&cNie masz komu odpisac!");
            return;
        }
        if (u.isIgnoreAll()) {
            ChatHelper.sendMessage(player, "&cIgnorujesz kazdego gracza!");
            return;
        }
        if (u.getIgnores().contains(u.getReplyPlayer())) {
            ChatHelper.sendMessage(player, "&cIgnorujesz tego gracza!");
            return;
        }
        final Player too = Bukkit.getPlayerExact(u.getReplyPlayer());
        if (too == null) {
            ChatHelper.sendMessage(player, "&cGracz ktoremu chcesz odpisac jest offline");
            return;
        }
        final User tooo = UserUtil.get(u.getReplyPlayer());
        if (tooo.isIgnoreAll()) {
            ChatHelper.sendMessage(player, "&cTen gracz ignoruje kazdego!");
            return;
        }
        if (tooo.getIgnores().contains(player.getName())) {
            ChatHelper.sendMessage(player, "&cTen gracz cie ignoruje!");
            return;
        }
        ChatHelper.sendMessage(player, "&BJa -> " + u.getReplyPlayer() + ": &7" + message);
        ChatHelper.sendMessage(too, "&b" + player.getName() + " -> Ja: &7" + message);
    }
}
