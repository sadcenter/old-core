package net.sadcenter.guilds.commands.guild.subcommands;

import net.sadcenter.commons.command.Command;
import net.sadcenter.commons.command.CommandBuilder;
import net.sadcenter.commons.command.CommandException;
import net.sadcenter.commons.helper.ChatHelper;
import net.sadcenter.guilds.basic.Guild;
import net.sadcenter.guilds.basic.Member;
import net.sadcenter.guilds.utils.GuildUtil;
import net.sadcenter.guilds.utils.MemberUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author sadcenter on 04.11.2020
 * @project LastCore
 */

public class GuildOwnerCommand extends Command {

    public GuildOwnerCommand() {
        super("lider");
    }

    @Override
    public void onCommand(CommandSender executor, CommandBuilder commandBuilder, String... args) throws CommandException {
        Player player = (Player) executor;
        String name = args[0];
        Guild g = GuildUtil.getFromPlayer(player.getName());
        if (g == null) {
            ChatHelper.sendMessage(player, "&cNie posiadasz gildii!");
            return;
        }
        Member o = MemberUtil.get(name);
        if (o == null) {
            ChatHelper.sendMessage(player, "&cTen gracz nie ma gildii!");
            return;
        }
        if (!o.getGuild().getTag().equals(g.getTag())) {
            ChatHelper.sendMessage(player, "&cTen gracz nie jest w twojej gildii!");
            return;
        }
        if (!g.getCreator().equalsIgnoreCase(player.getName())) {
            ChatHelper.sendMessage(player, "&CNie jestes zalozycielem!");
            return;
        }
        if (g.getCreator().equalsIgnoreCase(name)) {
            ChatHelper.sendMessage(player, "&cTen gracz jest juz liderem!");
            return;
        }
        g.setCreator(name);
        ChatHelper.sendMessage(Bukkit.getOnlinePlayers(), "&7Wlascicielem gildii [&3" + g.getTag() + "&7] zostal &3" + name);
    }
}
