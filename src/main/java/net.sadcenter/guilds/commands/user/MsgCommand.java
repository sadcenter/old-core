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

public class MsgCommand extends Command {

    public MsgCommand() {
        super("msg", true);
    }


    @Override
    public void onCommand(CommandSender executor, CommandBuilder commandBuilder, String... args) throws CommandException {
        commandBuilder
                .newArgumentsBuilder()
                .throwError(2, new NotEnoughArgumentsException("msg [nick] [wiadomosc]"));

        Player player = (Player) executor;

        String to = args[0];
        String message = StringUtils.join(args, " ", 1, args.length);
        User u = UserUtil.get(player.getUniqueId());
        if (u == null) {
            player.kickPlayer("blad");
            return;
        }
        if (u.isIgnoreAll()) {
            ChatHelper.sendMessage(player, "&cIgnorujesz kazdego gracza!");
            return;
        }
        if (u.getIgnores().contains(to)) {
            ChatHelper.sendMessage(player, "&cIgnorujesz tego gracza!");
            return;
        }
        User tooo = UserUtil.get(to);
        if (tooo.isIgnoreAll()) {
            ChatHelper.sendMessage(player, "&cTen gracz ignoruje kazdego!");
            return;
        }
        if (tooo.getIgnores().contains(player.getName())) {
            ChatHelper.sendMessage(player, "&cTen gracz cie ignoruje!");
            return;
        }
        Player too = Bukkit.getPlayerExact(to);
        if (too == null) {
            ChatHelper.sendMessage(player, "&cTen gracz jest offline");
            return;
        }
        tooo.setReplyPlayer(player.getName());
        u.setReplyPlayer(to);
        ChatHelper.sendMessage(player, "&BJa -> " + to + ": &7" + message);
        ChatHelper.sendMessage(too, "&b" + player.getName() + " -> Ja: &7" + message);
    }
}
