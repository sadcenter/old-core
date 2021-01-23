package net.sadcenter.guilds.commands.user;

import com.google.common.annotations.Beta;
import net.sadcenter.commons.command.Command;
import net.sadcenter.commons.command.CommandBuilder;
import net.sadcenter.commons.command.CommandException;
import net.sadcenter.commons.exceptions.impl.NotEnoughArgumentsException;
import net.sadcenter.commons.exceptions.impl.PlayerNullException;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author sadcenter on 26.09.2020
 * @project LASTCORE
 */

public class InvseeCommand extends Command {

    public InvseeCommand() {
        super("invsee");
    }

    @Override
    @Beta
    public void onCommand(CommandSender executor, CommandBuilder commandBuilder, String... args) throws CommandException {
        commandBuilder
                .newArgumentsBuilder()
                .throwError(1, new NotEnoughArgumentsException("invsee [gracz]"));

        ((Player)executor).openInventory(commandBuilder
                .newPlayerBuilder()
                .getPlayer(1)
                .throwIf(new PlayerNullException(), playerBuilder -> playerBuilder.beforeBuild() == null)
                .build().getInventory());
    }

}
