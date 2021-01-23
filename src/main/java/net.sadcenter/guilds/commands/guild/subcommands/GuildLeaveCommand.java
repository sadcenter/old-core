package net.sadcenter.guilds.commands.guild.subcommands;

import net.sadcenter.commons.command.Command;
import net.sadcenter.commons.command.CommandBuilder;
import net.sadcenter.commons.helper.ChatHelper;
import net.sadcenter.guilds.basic.Guild;
import net.sadcenter.guilds.managers.GuildManager;
import net.sadcenter.guilds.utils.GuildUtil;
import net.sadcenter.guilds.utils.MemberUtil;
import net.sadcenter.guilds.utils.Worlds;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GuildLeaveCommand extends Command {

    private final GuildManager guildManager;
    public GuildLeaveCommand(GuildManager guildManager) {
        super("opusc");
        this.guildManager = guildManager;
    }

    @Override
    public void onCommand(CommandSender executor, CommandBuilder commandBuilder, String... args) {
        Player player = (Player) executor;
        Guild g = GuildUtil.getFromPlayer(player.getName());
        if (g == null) {
            ChatHelper.sendMessage(player, "&cNie posiadasz gildii");
            return;
        }
        if (g.getLeader().equalsIgnoreCase(player.getName())) {
            ChatHelper.sendMessage(player, "&CJestes liderem gildii");
            return;
        }
        guildManager.leaveFromGuild(MemberUtil.get(player.getName()));
        Bukkit.broadcastMessage(ChatHelper.fixColor("&3" + player.getName() + " &7opuscil gildie &7[&3" + g.getTag() + "&7] &3" + g.getName() + " "));
        player.teleport(Worlds.DEAFULT_WORLD.getSpawnLocation());

    }
}
