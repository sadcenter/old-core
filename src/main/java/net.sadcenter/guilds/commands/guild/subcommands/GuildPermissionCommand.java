package net.sadcenter.guilds.commands.guild.subcommands;

import net.sadcenter.commons.command.Command;
import net.sadcenter.commons.command.CommandBuilder;
import net.sadcenter.commons.command.CommandException;
import net.sadcenter.commons.helper.ChatHelper;
import net.sadcenter.guilds.basic.Guild;
import net.sadcenter.guilds.guis.guilds.GuildMembersListGui;
import net.sadcenter.guilds.guis.guilds.GuildPermissionGui;
import net.sadcenter.guilds.utils.GuildUtil;
import net.sadcenter.guilds.utils.MemberUtil;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GuildPermissionCommand extends Command {

    public GuildPermissionCommand() {
        super("uprawnienia");
    }

    @Override
    public void onCommand(CommandSender executor, CommandBuilder commandBuilder, String... args) throws CommandException {
        Player player = (Player) executor;
        Guild g = GuildUtil.getFromPlayer(player.getName());
        if (g == null) {
            ChatHelper.sendMessage(player, "&cNie posiadasz gildii");
            return;
        }
        if (!g.getCreator().equals(player.getName())) {
            ChatHelper.sendMessage(player, "&cNie posiadasz lidera badz zastepcy!");
            return;
        }
        if(args.length == 0) {
            player.openInventory(new GuildMembersListGui(player, g).build());
            return;
        }
        String name = args[0];
        if (!g.isMember(name)) {
            ChatHelper.sendMessage(player, "&cTen gracz nie jest w twojej gildii");
            return;
        }
        player.openInventory(new GuildPermissionGui(player, MemberUtil.get(name), false).build());
    }
}
