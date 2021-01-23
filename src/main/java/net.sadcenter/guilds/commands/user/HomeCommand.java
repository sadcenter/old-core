package net.sadcenter.guilds.commands.user;

import net.sadcenter.commons.command.Command;
import net.sadcenter.commons.command.CommandBuilder;
import net.sadcenter.commons.helper.ChatHelper;
import net.sadcenter.guilds.LastCore;
import net.sadcenter.guilds.basic.User;
import net.sadcenter.guilds.utils.UserUtil;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author sadcenter on 17.08.2020
 * @project LASTCORE
 */

public class HomeCommand extends Command {
    public HomeCommand() {
        super("home");
    }

    @Override
    public void onCommand(CommandSender executor, CommandBuilder commandBuilder, String... args) {
        Player player = (Player) executor;
        if (args.length == 1) {
            if (!player.hasPermission("core.home.other")) {
                ChatHelper.sendMessage(player, "&cNie masz do tego uprawnien!");
                return;
            }
            User u = UserUtil.get(args[0]);
            if (u == null) {
                ChatHelper.sendMessage(player, "&cTen gracz nie istnieje");
                return;
            }
            if (u.getHome() == null) {
                ChatHelper.sendMessage(player, "&cTen gracz nie ma home");
                return;
            }
            player.teleport(u.getHome());
            return;
        }
        User u = UserUtil.get(player.getUniqueId());
        if (u.getHome() == null) {
            ChatHelper.sendMessage(player, "&cNie posiadasz home!");
            return;
        }
        LastCore.getInstance().getTeleportManager().add(player, u.getHome());
    }
}
