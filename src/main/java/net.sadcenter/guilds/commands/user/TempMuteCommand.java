package net.sadcenter.guilds.commands.user;

import net.sadcenter.commons.command.Command;
import net.sadcenter.commons.command.CommandBuilder;
import net.sadcenter.commons.command.CommandException;
import net.sadcenter.commons.exceptions.impl.NotEnoughArgumentsException;
import net.sadcenter.commons.helper.ChatHelper;
import net.sadcenter.guilds.LastCore;
import net.sadcenter.guilds.basic.Mute;
import net.sadcenter.guilds.managers.MuteManager;
import net.sadcenter.guilds.utils.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.command.CommandSender;

/**
 * @author sadcenter on 17.08.2020
 * @project LASTCORE
 */

public class TempMuteCommand extends Command {
    private final MuteManager muteManager = LastCore.getInstance().getMuteManager();

    public TempMuteCommand() {
        super("tempmute", "core.cmd.mute", false);
    }

    @Override
    public void onCommand(CommandSender executor, CommandBuilder commandBuilder, String... args) throws CommandException {
        commandBuilder
                .newArgumentsBuilder()
                .throwError(2, new NotEnoughArgumentsException("tempmute [nick] [czas] [reason]"));

        final String name = args[0];
        //if (MuteManager.getMute(name) != null) {
        if (null != null) {
            ChatHelper.sendMessage(executor, "&cTaki gracz juz jest zmutowany!");
            return;
        }
        final long date = DateUtil.getTime(args[1]);
        final String reason = args.length == 2 ? "Zlamanie regulaminu" : StringUtils.join(args, " ", 3, args.length);
        final Mute mute = new Mute(executor.getName(), name, date, System.currentTimeMillis(), reason);
        muteManager.create(mute);

    }
}
