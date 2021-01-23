package net.sadcenter.guilds.commands.guild.subcommands;

import net.sadcenter.commons.command.Command;
import net.sadcenter.commons.command.CommandBuilder;
import net.sadcenter.commons.command.CommandException;
import net.sadcenter.commons.helper.ChatHelper;
import net.sadcenter.guilds.basic.Guild;
import net.sadcenter.guilds.guis.guilds.GuildPanelGui;
import net.sadcenter.guilds.utils.GuildUtil;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author sadcenter on 08.11.2020
 * @project LastCore-remake
 */

public class GuildPanelCommand extends Command {

    public GuildPanelCommand() {
        super("panel");
    }

    @Override
    public void onCommand(CommandSender executor, CommandBuilder commandBuilder, String... args) throws CommandException {
        Player player = (Player) executor;
        Guild g = GuildUtil.getFromPlayer(player.getUniqueId());
        if (g == null) {
            ChatHelper.sendMessage(player, "&cNie posiadasz gildii!");
            return;
        }
        if (g.getCreator().equalsIgnoreCase(player.getName()) || g.getLeader().equalsIgnoreCase(player.getName())) {
            player.openInventory(new GuildPanelGui(player, g).build());
        } else {
            ChatHelper.sendMessage(player, "&cNie posiadasz uprawnien!");
        }
    }
}
