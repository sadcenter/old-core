package net.sadcenter.guilds.commands.guild.subcommands;

import net.sadcenter.commons.command.Command;
import net.sadcenter.commons.command.CommandBuilder;
import net.sadcenter.commons.helper.ChatHelper;
import net.sadcenter.guilds.basic.Guild;
import net.sadcenter.guilds.teleport.TeleportAPI;
import net.sadcenter.guilds.utils.GuildUtil;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GuildBaseCommand extends Command {

    private final TeleportAPI teleport;

    public GuildBaseCommand(TeleportAPI teleport) {
        super("baza");
        this.teleport = teleport;
    }

    @Override
    public void onCommand(CommandSender executor, CommandBuilder builder, String... args) {
        Player player = (Player) executor;
        final Guild g = GuildUtil.getFromPlayer(player.getName());
        if (g == null) {
            ChatHelper.sendMessage(player, "&cNie posiadasz gildii");
            return;
        }
        teleport.add(player, g.getHome());
    }

}

