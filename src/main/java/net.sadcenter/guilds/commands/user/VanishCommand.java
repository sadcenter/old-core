package net.sadcenter.guilds.commands.user;

import net.sadcenter.commons.command.Command;
import net.sadcenter.commons.command.CommandBuilder;
import net.sadcenter.commons.command.CommandException;
import net.sadcenter.commons.exceptions.impl.PlayerNullException;
import net.sadcenter.guilds.LastCore;
import net.sadcenter.guilds.managers.VanishManager;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author sadcenter on 17.08.2020
 * @project LASTCORE
 */

public class VanishCommand extends Command {

    private final VanishManager vanishManager = LastCore.getInstance().getVanishManager();

    public VanishCommand() {
        super("v", "core.cmd.v", true);
    }

    @Override
    public void onCommand(CommandSender executor, CommandBuilder commandBuilder, String... args) throws CommandException {
        vanishManager.changeStatus(commandBuilder
                .newPlayerBuilder()
                .getPlayer(1)
                .throwIf(new PlayerNullException(), playerBuilder -> playerBuilder.beforeBuild() == null && args.length == 1)
                .orElse((Player) executor)
                .build());
    }
}
