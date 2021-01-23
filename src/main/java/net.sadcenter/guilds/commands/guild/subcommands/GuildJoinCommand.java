package net.sadcenter.guilds.commands.guild.subcommands;

import net.sadcenter.commons.command.Command;
import net.sadcenter.commons.command.CommandBuilder;
import net.sadcenter.commons.helper.ChatHelper;
import net.sadcenter.guilds.LastCore;
import net.sadcenter.guilds.basic.Guild;
import net.sadcenter.guilds.managers.GuildManager;
import net.sadcenter.guilds.utils.GuildUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GuildJoinCommand extends Command {

    private final GuildManager manager = LastCore.getInstance().getGuildManager();

    public GuildJoinCommand() {
        super("dolacz");
    }

    @Override
    public void onCommand(CommandSender executor, CommandBuilder commandBuilder, String... args) {
        Player player = (Player) executor;
        if (GuildUtil.getFromPlayer(player.getName()) != null) {
            ChatHelper.sendMessage(player, "&CPosiadasz juz gildie");
            return;
        }
        final Guild g = GuildUtil.get(args[0]);
        if (g == null) {
            ChatHelper.sendMessage(player, "&cTaka gildia nie istnieje!");
            return;
        }
        if (!g.getInvites().contains(player.getName())) {
            ChatHelper.sendMessage(player, "&cNie posiadasz zaproszenia do gildii");
            return;
        }
        g.getInvites().remove(player.getName());
        manager.addToGuild(player, GuildUtil.get(args[0]));
        Bukkit.broadcastMessage(ChatHelper.fixColor("&3" + player.getName() + " &7dolaczyl do gildii &7[&3" + g.getTag() + "&7] &3" + g.getName() + " "));
    }


}
