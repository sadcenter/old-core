package net.sadcenter.guilds.commands.user;

import net.sadcenter.commons.command.Command;
import net.sadcenter.commons.command.CommandBuilder;
import net.sadcenter.commons.helper.ChatHelper;
import net.sadcenter.guilds.LastCore;
import net.sadcenter.guilds.basic.Blacklist;
import net.sadcenter.guilds.basic.User;
import net.sadcenter.guilds.utils.UserUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author sadcenter on 10.09.2020
 * @project LASTCORE
 */

public class BlackListCommand extends Command {


    public BlackListCommand() {
        super("blacklist", "core.cmd.blacklist", true);
    }

    @Override
    public void onCommand(CommandSender executor, CommandBuilder commandBuilder, String... args) {
        Player player = (Player) executor;
        User toban = UserUtil.get(args[0]);
        if (toban == null) {
            ChatHelper.sendMessage(player, "&cTaki user nie istnieje!");
            return;
        }

        LastCore.getInstance().getBlacklistManager().registerBlacklist(new Blacklist(toban.getId(), toban.getName(), Bukkit.getOfflinePlayer(toban.getId()).getPlayer().getAddress().getAddress(), args[1]));
    }
}
