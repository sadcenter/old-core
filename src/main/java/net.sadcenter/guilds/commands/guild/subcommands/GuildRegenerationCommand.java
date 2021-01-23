package net.sadcenter.guilds.commands.guild.subcommands;

import net.sadcenter.commons.command.Command;
import net.sadcenter.commons.command.CommandBuilder;
import net.sadcenter.commons.command.CommandException;
import net.sadcenter.commons.helper.ChatHelper;
import net.sadcenter.guilds.basic.Guild;
import net.sadcenter.guilds.basic.Member;
import net.sadcenter.guilds.basic.fields.BlockField;
import net.sadcenter.guilds.storage.Settings;
import net.sadcenter.guilds.utils.GuildUtil;
import net.sadcenter.guilds.utils.MemberUtil;
import net.sadcenter.guilds.utils.RegenerationUtil;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author sadcenter on 18.08.2020
 * @project LASTCORE
 */

public class GuildRegenerationCommand extends Command {
    public GuildRegenerationCommand() {
        super("regeneracja");
    }

    @Override
    public void onCommand(CommandSender executor, CommandBuilder commandBuilder, String... args) throws CommandException {
        if (!Settings.isEnabledGuildRegeneration())
            throw new CommandException("&cRegeneracja gildii jest aktualnie wylaczona!");

        Player player = (Player) executor;
        final Guild g = GuildUtil.getFromPlayer(player.getName());
        if (g == null) {
            ChatHelper.sendMessage(player, "&cNie posiadasz gildii");
            return;
        }
        Member member = MemberUtil.get(player.getUniqueId());
        if (!member.isRegenerationPerm()) {
            ChatHelper.sendMessage(player, "&cNie masz uprawnien do regeneracji!");
            return;
        }
        if (g.getExplodedBlocks().isEmpty()) {
            ChatHelper.sendMessage(player, "&cNie mozesz zregenerowac 0 blokow!");
            return;
        }
        if (g.isRegenerating()) {
            ChatHelper.sendMessage(player, "&cRegeneracja juz trwa!");
            return;
        }
        Queue<BlockField> queue = new LinkedBlockingQueue<>(g.getExplodedBlocks());
        int blocksSize = queue.size() * 32 / 100;

        if (g.getDepositBlocks() < blocksSize) {
            ChatHelper.sendMessage(player, "&7Musisz miec &3" + blocksSize + "&7 blokow zlota w depozycie gildii!");
            return;
        }
        g.removeDeposit(blocksSize);
        g.setRegenerating(true);
        RegenerationUtil.startRegeneration(g, queue);
    }
}
