package net.sadcenter.guilds.commands.guild.subcommands;

import net.sadcenter.commons.command.Command;
import net.sadcenter.commons.command.CommandBuilder;
import net.sadcenter.commons.helper.ChatHelper;
import net.sadcenter.guilds.basic.Guild;
import net.sadcenter.guilds.basic.Member;
import net.sadcenter.guilds.utils.GuildUtil;
import net.sadcenter.guilds.utils.MemberUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GuildEnlargeCommand extends Command {

    public GuildEnlargeCommand() {
        super("powieksz");
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
        if (!member.isEnlargePerm()) {
            ChatHelper.sendMessage(player, "&cNie posiadasz uprawnien do powiekszania");
            return;
        }
        if (g.getRegion().getSize() >= 81) {
            ChatHelper.sendMessage(player, "&cPosiadasz maks rozmiar terenu!");
            return;
        }
        g.addSize(11);
        final int size = g.getRegion().getSize();
        Bukkit.broadcastMessage(ChatHelper.fixColor("&3" + player.getName() + " &7powiekszyl teren gidlii &3" + g.getTag() + " &7do &3" + size + "&7x&3" + size + " &8(&3+11&8)"));
    }


}
