package net.sadcenter.guilds.commands.guild.subcommands;


import net.sadcenter.commons.command.Command;
import net.sadcenter.commons.command.CommandBuilder;
import net.sadcenter.commons.helper.ChatHelper;
import net.sadcenter.guilds.basic.Guild;
import net.sadcenter.guilds.basic.Member;
import net.sadcenter.guilds.managers.GuildManager;
import net.sadcenter.guilds.utils.GuildUtil;
import net.sadcenter.guilds.utils.MemberUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author sadcenter on 27.08.2020
 * @project LASTCORE
 */
public class GuildKickCommand extends Command {

    private final GuildManager guildManager;

    public GuildKickCommand(GuildManager guildManager) {
        super("wyrzuc");
        this.guildManager = guildManager;
    }

    @Override
    public void onCommand(CommandSender executor, CommandBuilder commandBuilder, String... args) {
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
        Bukkit.broadcastMessage(ChatHelper.fixColor("&3" + player.getName() + " &7wyrzucil z gildii &7[&3" + g.getTag() + "&7] &3" + name + " "));
        guildManager.leaveFromGuild(MemberUtil.get(name));


    }
}
