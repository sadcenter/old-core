package net.sadcenter.guilds.commands.guild.subcommands;

import net.sadcenter.commons.command.Command;
import net.sadcenter.commons.command.CommandBuilder;
import net.sadcenter.commons.helper.ChatHelper;
import net.sadcenter.guilds.basic.Guild;
import net.sadcenter.guilds.utils.GuildUtil;
import net.sadcenter.guilds.utils.LocationUtil;
import net.sadcenter.guilds.utils.MemberUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class GuildInviteCommand extends Command {

    public GuildInviteCommand() {
        super("zapros");
    }

    @Override
    public void onCommand(CommandSender executor, CommandBuilder commandBuilder, String... args) {
        String name = args[0];
        Player player = (Player) executor;
        Guild g = GuildUtil.getFromPlayer(player.getName());
        if (g == null) {
            ChatHelper.sendMessage(player, "&cNie posiadasz gildii!");
            return;
        }
        if (!MemberUtil.get(player.getName()).isInvitePerm()) {
            ChatHelper.sendMessage(player, "&cNie posiadasz uprawnien do tego!");
            return;
        }
        if (name.equalsIgnoreCase("all")) {
            List<Player> invites = LocationUtil.getPlayersInRadius(player.getLocation());
            if (invites.isEmpty()) {
                ChatHelper.sendTitle(player, "", "&c0 graczy!");
                return;
            }
            invites.forEach(invited -> {
                String playerName = invited.getName();
                if (MemberUtil.get(playerName) != null) {
                    ChatHelper.sendMessage(player, "&cTen gracz juz posiada gildie! (" + playerName + ")");
                    return;
                }
                if (g.getMembers().size() >= 20) {
                    ChatHelper.sendMessage(player, "&cLimit czlonkow to 20 osob! (" + playerName + ")");
                    return;
                }
                g.getInvites().add(playerName);
                ChatHelper.sendMessage(player, "&7Wyslales zaproszenie do gracza &3" + invited.getName());
                ChatHelper.sendMessage(invited, "&7Otrzymales zaproszenie do gildii &7[&3" + g.getTag() + "&7]");
            });

        } else {
            if (MemberUtil.get(name) != null) {
                ChatHelper.sendMessage(player, "&cTen gracz juz posiada gildie!");
                return;
            }
            Player target = Bukkit.getPlayer(name);
            if (target == null) {
                ChatHelper.sendMessage(player, "&cTaki gracz nie jest online!");
                return;
            }
            if (g.getMembers().size() >= 20) {
                ChatHelper.sendMessage(player, "&cLimit czlonkow to 20 osob!");
                return;
            }
            if (g.getInvites().contains(target.getName())) {
                ChatHelper.sendMessage(player, "&7Cofnieto zaproszenie!");
                g.getInvites().remove(target.getName());
                return;
            }
            g.getInvites().add(target.getName());
            ChatHelper.sendMessage(player, "&7Wyslales zaproszenie do gracza &3" + target.getName());
            ChatHelper.sendMessage(target, "&7Otrzymales zaproszenie do gildii &7[&3" + g.getTag() + "&7]");
        }
    }


}
