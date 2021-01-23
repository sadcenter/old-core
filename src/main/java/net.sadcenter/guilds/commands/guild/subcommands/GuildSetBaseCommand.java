package net.sadcenter.guilds.commands.guild.subcommands;

import net.sadcenter.commons.command.Command;
import net.sadcenter.commons.command.CommandBuilder;
import net.sadcenter.commons.helper.ChatHelper;
import net.sadcenter.guilds.basic.Guild;
import net.sadcenter.guilds.basic.Member;
import net.sadcenter.guilds.utils.GuildUtil;
import net.sadcenter.guilds.utils.MemberUtil;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GuildSetBaseCommand extends Command {
    public GuildSetBaseCommand() {
        super("ustawbaze");
    }

    @Override
    public void onCommand(CommandSender executor, CommandBuilder commandBuilder, String... args) {
        Player player = (Player) executor;
        final Guild g = GuildUtil.getFromPlayer(player.getName());
        if (g == null) {
            ChatHelper.sendMessage(player, "&cNie posiadasz gildii");
            return;
        }
        Member member = MemberUtil.get(player.getUniqueId());
        if (!member.isSetBasePerm()) {
            ChatHelper.sendMessage(player, "&cNie posiadasz uprawnien");
            return;
        }
        if (!g.getCreator().equalsIgnoreCase(player.getName())) {
            ChatHelper.sendMessage(player, "&cNie jestes zalozycielem ani zastepca!");
            return;
        }
        ChatHelper.sendTitle(player, "", "&7Ustawiles dom gildii!");
        g.setHome(player.getLocation());

    }
}
