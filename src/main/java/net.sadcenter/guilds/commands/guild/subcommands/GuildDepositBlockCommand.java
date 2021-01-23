package net.sadcenter.guilds.commands.guild.subcommands;

import net.sadcenter.commons.command.Command;
import net.sadcenter.commons.command.CommandBuilder;
import net.sadcenter.commons.helper.ChatHelper;
import net.sadcenter.guilds.basic.Guild;
import net.sadcenter.guilds.basic.Member;
import net.sadcenter.guilds.guis.guilds.GuildDepositGui;
import net.sadcenter.guilds.utils.GuildUtil;
import net.sadcenter.guilds.utils.MemberUtil;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author sadcenter on 18.08.2020
 * @project LASTCORE
 */

public class GuildDepositBlockCommand extends Command {

    public GuildDepositBlockCommand() {
        super("schowek");
    }

    @Override
    public void onCommand(CommandSender executor, CommandBuilder commandBuilder, String... args) {
        Player player = (Player) executor;
        Guild g = GuildUtil.getFromPlayer(player.getName());
        if (g == null) {
            ChatHelper.sendMessage(player, "&cNie posiadasz gildii!");
            return;
        }
        Member member = MemberUtil.get(player.getUniqueId());
        if (!member.isDepositPerm()) {
            ChatHelper.sendMessage(player, "&cNie posiadasz uprawnien!");
            return;
        }
        player.openInventory(new GuildDepositGui(player, g).build());
    }
}
