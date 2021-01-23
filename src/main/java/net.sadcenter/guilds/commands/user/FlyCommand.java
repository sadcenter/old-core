package net.sadcenter.guilds.commands.user;

import net.sadcenter.commons.command.Command;
import net.sadcenter.commons.command.CommandBuilder;
import net.sadcenter.commons.command.CommandException;
import net.sadcenter.commons.exceptions.impl.PlayerNullException;
import net.sadcenter.commons.helper.ChatHelper;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author sadcenter on 16.08.2020
 * @project LASTCORE
 */
public class FlyCommand extends Command {
    public FlyCommand() {
        super("fly", "core.cmd.fly", false);
    }

    @Override
    public void onCommand(CommandSender executor, CommandBuilder commandBuilder, String... args) throws CommandException {
        Player to = commandBuilder
                .newPlayerBuilder()
                .getPlayer(1)
                .orElse((Player) executor)
                .throwIf(new PlayerNullException(), playerBuilder -> playerBuilder.beforeBuild() == null && args.length == 1)
                .build();

        to.setAllowFlight(!to.getAllowFlight());
        to.setFlying(to.getAllowFlight());
        ChatHelper.sendMessage(executor, "&7Fly zostal " + (to.isFlying() ? "&awlaczona" : "&cwylaczony") + (to.getName().equals(executor.getName()) ? "" : " &adla &3" + to.getName()));
    }
}
