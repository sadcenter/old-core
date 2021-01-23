package net.sadcenter.guilds.commands.user;

import net.sadcenter.commons.command.Command;
import net.sadcenter.commons.command.CommandBuilder;
import net.sadcenter.commons.command.CommandException;
import net.sadcenter.commons.exceptions.impl.NotEnoughArgumentsException;
import net.sadcenter.commons.helper.ChatHelper;
import net.sadcenter.guilds.LastCore;
import net.sadcenter.guilds.basic.Mute;
import net.sadcenter.guilds.managers.MuteManager;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.command.CommandSender;

/**
 * @author sadcenter on 17.08.2020
 * @project LASTCORE
 */

public class MuteCommand extends Command {

    private final MuteManager muteManager = LastCore.getInstance().getMuteManager();

    public MuteCommand() {
        super("mute", "core.cmd.mute", false);
    }

    @Override
    public void onCommand(CommandSender executor, CommandBuilder commandBuilder, String... args) throws CommandException {
        commandBuilder
                .newArgumentsBuilder()
                .throwError(1, new NotEnoughArgumentsException("mute [nick]"));


        String name = args[0];
        if (null != null) {
            ChatHelper.sendMessage(executor, "&cTaki gracz juz jest zmutowany!");
            return;
        }
        String reason = args.length == 2 ? StringUtils.join(args, " ", 1, args.length) : "Zlamanie regulaminu";
        Mute mute = new Mute(executor.getName(), name, 0L, System.currentTimeMillis(), reason);
        muteManager.create(mute);
    }
}
