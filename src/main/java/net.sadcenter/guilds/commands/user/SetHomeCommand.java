package net.sadcenter.guilds.commands.user;

import net.sadcenter.commons.command.Command;
import net.sadcenter.commons.command.CommandBuilder;
import net.sadcenter.commons.helper.ChatHelper;
import net.sadcenter.guilds.utils.UserUtil;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author sadcenter on 17.08.2020
 * @project LASTCORE
 */

public class SetHomeCommand extends Command {

    public SetHomeCommand() {
        super("sethome", true);
    }

    @Override
    public void onCommand(CommandSender executor, CommandBuilder commandBuilder, String... args) {
        Player player = (Player) executor;
        UserUtil.get(player.getUniqueId()).setHome(player.getLocation());
        ChatHelper.sendMessage(player, "&aUstawiono home!");
    }
}
