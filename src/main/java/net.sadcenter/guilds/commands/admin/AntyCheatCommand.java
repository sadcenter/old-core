package net.sadcenter.guilds.commands.admin;

import net.sadcenter.commons.command.Command;
import net.sadcenter.commons.command.CommandBuilder;
import net.sadcenter.commons.command.CommandException;
import net.sadcenter.commons.exceptions.impl.NotEnoughArgumentsException;
import net.sadcenter.commons.helper.ChatHelper;
import net.sadcenter.guilds.basic.Blacklist;
import net.sadcenter.guilds.managers.BlacklistManager;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author sadcenter on 14.10.2020
 * @project LASTCORE
 */

public class AntyCheatCommand extends Command {

    private final BlacklistManager blacklistManager;

    public AntyCheatCommand(BlacklistManager manager) {
        super("antycheat", "core.cmd.onlyconsole", false);
        this.blacklistManager = manager;

    }

    @Override
    public void onCommand(CommandSender executor, CommandBuilder commandBuilder, String... args) throws CommandException {
        commandBuilder
                .newArgumentsBuilder()
                .throwError(2, new NotEnoughArgumentsException("antycheat [nick] [reason]"));

        Bukkit.broadcastMessage(ChatHelper.fixColor("&7[&4Anty-Cheat&7] &3" + args[0] + " &7zostal zablokowany za uzywanie wspomagaczy!"));
        Bukkit.getOnlinePlayers().forEach(online -> {
            if (online.hasPermission("scode.root"))
                ChatHelper.sendMessage(online, "&3" + args[0] + " &8-> &3" + args[1]);

        });
        Player exec = Bukkit.getPlayer(args[0]);
        blacklistManager.registerBlacklist(new Blacklist(exec.getUniqueId(), exec.getName(), exec.getAddress().getAddress(), "&3Uzywanie wspomagaczy &7[&3BLACKLISTA&7]"));

    }
}
