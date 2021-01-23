package net.sadcenter.guilds.commands.guild.subcommands;

import net.sadcenter.commons.command.Command;
import net.sadcenter.commons.command.CommandBuilder;
import net.sadcenter.commons.helper.ChatHelper;
import net.sadcenter.guilds.basic.Guild;
import net.sadcenter.guilds.utils.GuildUtil;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GuildPvPCommand extends Command {


    public GuildPvPCommand() {
        super("pvp");
    }

    @Override
    public void onCommand(CommandSender executor, CommandBuilder commandBuilder, String... args) {
        Player player = (Player) executor;
        final Guild g = GuildUtil.getFromPlayer(player.getName());
        if (g == null) {
            ChatHelper.sendMessage(player, "&8>> &cNie posiadasz gildii");
            return;
        }
        g.setPvp(!g.isPvp());
        g.getOnlinePlayers().forEach(player1 -> ChatHelper.sendTitle(player1, "&3&LPVP", "&3" + player.getName() + " " + (g.isPvp() ? "&awlaczyl" : "&cwylaczyl") + "&7 pvp w gildii"));
    }
}
