package net.sadcenter.guilds.commands.user;

import net.sadcenter.commons.command.Command;
import net.sadcenter.commons.command.CommandBuilder;
import org.bukkit.command.CommandSender;

/**
 * @author sadcenter on 17.08.2020
 * @project LASTCORE
 */

public class KnockBackCommand extends Command {
    public KnockBackCommand() {
        super("knockback", "core.cmd.knock", false);
    }

    @Override
    public void onCommand(CommandSender executor, CommandBuilder commandBuilder, String... args) {

    }
}
