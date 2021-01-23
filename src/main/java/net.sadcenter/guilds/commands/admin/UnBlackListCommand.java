package net.sadcenter.guilds.commands.admin;

import net.sadcenter.commons.command.Command;
import net.sadcenter.commons.command.CommandBuilder;
import net.sadcenter.commons.command.CommandException;
import net.sadcenter.commons.exceptions.impl.NotEnoughArgumentsException;
import net.sadcenter.commons.helper.ChatHelper;
import net.sadcenter.guilds.basic.Blacklist;
import net.sadcenter.guilds.managers.BlacklistManager;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Optional;
import java.util.UUID;

/**
 * @author sadcenter on 14.10.2020
 * @project LASTCORE
 */

public class UnBlackListCommand extends Command {

    private final BlacklistManager blacklistManager;

    public UnBlackListCommand(BlacklistManager blacklistManager) {
        super("unblacklist", "core.cmd.unblacklist", false);
        this.blacklistManager = blacklistManager;
    }

    @Override
    public void onCommand(CommandSender executor, CommandBuilder commandBuilder, String... args) throws CommandException {
        commandBuilder
                .newArgumentsBuilder()
                .throwError(2, new NotEnoughArgumentsException("blacklist [type: uuid/nick/ip] [value]"));

        if (args[0].equalsIgnoreCase("uuid")) {
            @NotNull Optional<Blacklist> blacklist = blacklistManager.getBlacklist(UUID.fromString(args[1]));
            if (!blacklist.isPresent()) {
                ChatHelper.sendMessage(executor, "&cTe uuid nie ma blacklisty!");
                return;
            }
            blacklistManager.removeBlacklist(UUID.fromString(args[1]));
            ChatHelper.sendMessage(executor, "&success!");
        } else if (args[0].equalsIgnoreCase("nick")) {
            @NotNull Optional<Blacklist> blacklist = blacklistManager.getBlacklist(args[1]);
            if (!blacklist.isPresent()) {
                ChatHelper.sendMessage(executor, "&cTen nick nie ma blacklisty!");
                return;
            }
            blacklistManager.removeBlacklist(args[1]);
            ChatHelper.sendMessage(executor, "&success!");
        } else if (args[0].equalsIgnoreCase("ip")) {
            InetAddress inetAddress = null;
            try {
                inetAddress = InetAddress.getByName(args[1]);
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
            @NotNull Optional<Blacklist> blacklist = blacklistManager.getBlacklist(inetAddress);
            if (!blacklist.isPresent()) {
                ChatHelper.sendMessage(executor, "&cTe ip nie ma blacklisty!");
                return;
            }
            blacklistManager.removeBlacklist(inetAddress);
            ChatHelper.sendMessage(executor, "&success!");
        }
    }
}
