package net.sadcenter.guilds.commands.user;

import net.sadcenter.commons.command.Command;
import net.sadcenter.commons.command.CommandBuilder;
import net.sadcenter.commons.command.CommandException;
import net.sadcenter.commons.exceptions.impl.NotEnoughArgumentsException;
import net.sadcenter.commons.exceptions.impl.PlayerNullException;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author sadcenter on 10.10.2020
 * @project LASTCORE
 */

public class TpHereCommand extends Command {

    public TpHereCommand() {
        super("tphere", "core.cmd.tphere", new String[]{"s"}, true);
    }

    @Override
    public void onCommand(CommandSender executor, CommandBuilder commandBuilder, String... args) throws CommandException {
        commandBuilder
                .newArgumentsBuilder()
                .throwError(1, new NotEnoughArgumentsException("tphere [nick]"));

        Player player = (Player) executor;

        Player toTeleport = commandBuilder
                .newPlayerBuilder()
                .getPlayer(1)
                .throwIf(new PlayerNullException(), playerBuilder -> playerBuilder.beforeBuild() == null)
                .build();

        toTeleport.teleport(player.getLocation());
    }
}
