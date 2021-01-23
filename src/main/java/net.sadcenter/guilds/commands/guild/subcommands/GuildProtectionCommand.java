package net.sadcenter.guilds.commands.guild.subcommands;

import net.sadcenter.commons.command.Command;
import net.sadcenter.commons.command.CommandBuilder;
import net.sadcenter.commons.helper.ChatHelper;
import net.sadcenter.guilds.basic.Guild;
import net.sadcenter.guilds.utils.GuildUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GuildProtectionCommand extends Command {

    public GuildProtectionCommand() {
        super("ochrona");
    }

    @Override
    public void onCommand(CommandSender executor, CommandBuilder commandBuilder, String... args) {
        Player player = (Player) executor;
        Guild g = GuildUtil.getFromPlayer(player.getName());
        if (g == null) {
            ChatHelper.sendMessage(player, "&cNie posiadasz gildii!");
            return;
        }
        if (!g.getCreator().equalsIgnoreCase(player.getName())) {
            ChatHelper.sendMessage(player, "&cNie jestes liderem");
            return;
        }
        if (g.getProtection() < System.currentTimeMillis()) {
            ChatHelper.sendMessage(player, "&cTwoja gildia nie posiada ochrony!");
            return;
        }
        g.setProtection(System.currentTimeMillis());
        Bukkit.broadcastMessage(ChatHelper.fixColor("&3" + player.getName() + " &7wylaczyl ochrone dla gildii &7[&3" + g.getTag() + "&7]"));

    }
}
