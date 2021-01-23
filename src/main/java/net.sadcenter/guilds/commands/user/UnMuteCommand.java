package net.sadcenter.guilds.commands.user;

import net.sadcenter.commons.command.Command;
import net.sadcenter.commons.command.CommandBuilder;
import net.sadcenter.commons.command.CommandException;
import net.sadcenter.commons.exceptions.impl.NotEnoughArgumentsException;
import net.sadcenter.commons.helper.ChatHelper;
import net.sadcenter.guilds.LastCore;
import net.sadcenter.guilds.basic.Mute;
import net.sadcenter.guilds.managers.MuteManager;
import org.bukkit.command.CommandSender;

/**
 * @author sadcenter on 17.08.2020
 * @project LASTCORE
 */

public class UnMuteCommand extends Command {

    private final MuteManager muteManager;

    public UnMuteCommand(MuteManager muteManager) {
        super("unmute", "core.cmd.mute", false);
        this.muteManager = muteManager;
    }

    @Override
    public void onCommand(CommandSender executor, CommandBuilder commandBuilder, String... args) throws CommandException {
        commandBuilder
                .newArgumentsBuilder()
                .throwError(1, new NotEnoughArgumentsException("unmute [nick]"));

        final Mute mute = muteManager.getMutes().values().stream().filter(muted -> muted.getName().equalsIgnoreCase(args[0])).findFirst().orElse(null); //MuteManager.getMute(args[0]);
        if (mute == null) {
            ChatHelper.sendMessage(executor, "&cTen gracz nie jest zmutowany");
            return;
        }
        LastCore.getInstance().getMuteManager().remove(mute);
    }
}
