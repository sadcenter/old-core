package net.sadcenter.guilds.commands.user;

import net.sadcenter.commons.command.Command;
import net.sadcenter.commons.command.CommandBuilder;
import net.sadcenter.commons.command.CommandException;
import net.sadcenter.commons.exceptions.impl.NotEnoughArgumentsException;
import net.sadcenter.commons.exceptions.impl.NotNumberException;
import net.sadcenter.commons.exceptions.impl.PlayerNullException;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author sadcenter on 09.10.2020
 * @project LASTCORE
 */

public class TpposCommand extends Command {


    public TpposCommand() {
        super("tppos", "scode.core.tppos", true);
    }

    @Override
    public void onCommand(CommandSender executor, CommandBuilder commandBuilder, String... args) throws CommandException {
        commandBuilder
                .newArgumentsBuilder()
                .throwIfNotNumber(new NotNumberException(1), 1)
                .throwIfNotNumber(new NotNumberException(2), 2)
                .throwIfNotNumber(new NotNumberException(3), 3)
                .throwError(3, new NotEnoughArgumentsException("tppos [x] [y] [z]"));

        Player player = (Player) executor;

        commandBuilder
                .newPlayerBuilder()
                .getPlayer(4)
                .orElse(player)
                .throwIf(new PlayerNullException(), cmdBuilder -> cmdBuilder.getArgs().length == 4 && cmdBuilder.beforeBuild() == null)
                .build()
                .teleport(new Location(player.getWorld(), Double.parseDouble(args[0]), Double.parseDouble(args[1]), Double.parseDouble(args[2])));

    }
}
