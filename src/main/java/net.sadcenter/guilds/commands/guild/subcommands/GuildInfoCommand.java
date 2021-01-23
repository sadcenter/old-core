package net.sadcenter.guilds.commands.guild.subcommands;

import net.sadcenter.commons.command.Command;
import net.sadcenter.commons.command.CommandBuilder;
import net.sadcenter.commons.helper.ChatHelper;
import net.sadcenter.guilds.LastCore;
import net.sadcenter.guilds.basic.Guild;
import net.sadcenter.guilds.basic.Member;
import net.sadcenter.guilds.utils.GuildUtil;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Date;

public class GuildInfoCommand extends Command {

    public GuildInfoCommand() {
        super("info", false);
    }

    @Override
    public void onCommand(CommandSender executor, CommandBuilder commandBuilder, String... args) {
        Guild g;
        if (args.length == 0) {
            g = GuildUtil.getFromPlayer(executor.getName());
        } else {
            g = GuildUtil.getEquals(args[0]);
        }
        if (g == null) {
            ChatHelper.sendMessage(executor, "&CGildia nie istnieje!");
            return;
        }
        ChatHelper.sendMessage(executor, "&7------------- [&3" + g.getTag() + " &7-------------");
        ChatHelper.sendMessage(executor, " &7Nazwa: &3" + g.getName());
        ChatHelper.sendMessage(executor, " &7Lider: &3" + g.getCreator());
        ChatHelper.sendMessage(executor, " &7Zastepca: &3" + g.getLeader());
        ChatHelper.sendMessage(executor, " &7Czlonkowie: " + StringUtils.join(getMemberList(g.getMembers().values()), "&8, "));
        ChatHelper.sendMessage(executor, " &7Ochrona TNT: &3" + (g.getProtection() > System.currentTimeMillis() ? new Date(g.getProtection()) : "Brak"));
        ChatHelper.sendMessage(executor, " &7Punkty: &3" + g.getPoints());
        ChatHelper.sendMessage(executor, " &7Pozycja: &3" + LastCore.getInstance().getTopManager().positionOfGuild(g));
        ChatHelper.sendMessage(executor, " &7Zabojstwa: &3" + g.getKills());
        ChatHelper.sendMessage(executor, " &7Smierci: &3" + g.getDeaths());
        ChatHelper.sendMessage(executor, " &7Zycia: &3" + g.getLife());
        ChatHelper.sendMessage(executor, " &7Serca: &3" + g.getHearts());
        ChatHelper.sendMessage(executor, " &7Rozmiar gildii: &3" + g.getRegion().getSize() + "&7x&3" + g.getRegion().getSize());
        ChatHelper.sendMessage(executor, " ");
    }

    @NotNull
    private String[] getMemberList(final Collection<Member> members) {
        return members.stream().map(member -> Bukkit.getPlayer(member.getUuid()) != null ? "&a" + member.getName() : "&c" + member.getName()).toArray(String[]::new);
    }
}
