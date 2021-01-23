package net.sadcenter.guilds.commands.user;

import net.sadcenter.commons.command.Command;
import net.sadcenter.commons.command.CommandBuilder;
import net.sadcenter.commons.helper.ChatHelper;
import net.sadcenter.guilds.basic.User;
import net.sadcenter.guilds.utils.UserUtil;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author sadcenter on 16.08.2020
 * @project LASTCORE
 */

public class GodCommand extends Command {
    public GodCommand() {
        super("god", "core.cmd.god", false);
    }

    @Override
    public void onCommand(CommandSender executor, CommandBuilder commandBuilder, String... args) {
        User u;
        if (args.length == 0)
            u = UserUtil.get(((Player) executor).getUniqueId());
        else
            u = UserUtil.get(args[0]);

        if (u == null)
            return;

        u.setGod(!u.isGod());
        ChatHelper.sendMessage(executor, "" + (u.isGod() ? "&aWlaczyles" : "&cWylaczyles") + " goda " + (args.length == 1 ? "dla " + u.getName() : ""));
    }
}
