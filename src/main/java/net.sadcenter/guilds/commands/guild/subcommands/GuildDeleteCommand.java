package net.sadcenter.guilds.commands.guild.subcommands;

import net.sadcenter.commons.command.Command;
import net.sadcenter.commons.command.CommandBuilder;
import net.sadcenter.commons.helper.ChatHelper;
import net.sadcenter.guilds.basic.Guild;
import net.sadcenter.guilds.managers.GuildManager;
import net.sadcenter.guilds.utils.GuildUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

public class GuildDeleteCommand extends Command {

    private final GuildManager guildManager;

    public GuildDeleteCommand(GuildManager guildManager) {
        super("usun");
        this.guildManager = guildManager;
    }

    @Override
    public void onCommand(CommandSender executor, CommandBuilder commandBuilder, String... args) {
        Player player = (Player) executor;
        Guild g = GuildUtil.getFromPlayer(player.getName());
        if (Objects.isNull(g)) {
            ChatHelper.sendMessage(player, "&cNie posiadasz gildii!");
            return;
        }
        if (!g.getCreator().equals(player.getName())) {
            ChatHelper.sendMessage(player, "&cNie posiadasz lidera!");
            return;
        }
        guildManager.deleteGuild(g);
        Bukkit.broadcastMessage(ChatHelper.fixColor("&3" + player.getName() + " &7usunal gildie &7[&3" + g.getTag() + "&7] &3" + g.getName() + " "));
    }


}
