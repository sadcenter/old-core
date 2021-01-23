package net.sadcenter.guilds.commands.guild.subcommands;

import net.sadcenter.commons.command.Command;
import net.sadcenter.commons.command.CommandBuilder;
import net.sadcenter.commons.helper.ChatHelper;
import net.sadcenter.guilds.basic.Guild;
import net.sadcenter.guilds.utils.GuildUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class GuildProlongCommand extends Command {

    public GuildProlongCommand() {
        super("przedluz");
    }

    @Override
    public void onCommand(CommandSender executor, CommandBuilder commandBuilder, String... args) {
        Player player = (Player) executor;
        Guild g = GuildUtil.getFromPlayer(player.getName());
        if (g == null) {
            ChatHelper.sendMessage(player, "&8>> &cNie posiadasz gildii");
            return;
        }
        if (!g.getCreator().equalsIgnoreCase(player.getName())) {
            ChatHelper.sendMessage(player, "&8>> &cNie jestes liderem!");
            return;
        }
        if (g.getExpire() - TimeUnit.DAYS.toMillis(1) > System.currentTimeMillis()) {
            ChatHelper.sendMessage(player, "&8>> &cPrzedluzyles na maks!");
            return;
        }
        g.setExpire(g.getExpire() + TimeUnit.DAYS.toMillis(1));
        Bukkit.broadcastMessage(ChatHelper.fixColor("&8>> &7Gracz &3" + player.getName() + " &7przedluzyl gildie &7[&3" + g.getTag() + "&7] &7do &3" + new Date(g.getExpire())));
    }
}
