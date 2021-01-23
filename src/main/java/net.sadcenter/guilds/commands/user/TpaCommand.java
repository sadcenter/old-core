package net.sadcenter.guilds.commands.user;

import lombok.Getter;
import net.sadcenter.commons.command.Command;
import net.sadcenter.commons.command.CommandBuilder;
import net.sadcenter.commons.command.CommandException;
import net.sadcenter.commons.exceptions.impl.NotEnoughArgumentsException;
import net.sadcenter.commons.exceptions.impl.PlayerNullException;
import net.sadcenter.commons.helper.ChatHelper;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author sadcenter on 15.08.2020
 * @project LASTCORE
 */

public class TpaCommand extends Command {

    @Getter
    private final Map<String, String> requested = new ConcurrentHashMap<>();

    public TpaCommand() {
        super("tpa", true);
    }

    @Override
    public void onCommand(CommandSender executor, CommandBuilder commandBuilder, String... args) throws CommandException {
        commandBuilder
                .newArgumentsBuilder()
                .throwError(1, new NotEnoughArgumentsException("tpa [nick]"));

        Player player = (Player) executor;

        Player extract = commandBuilder
                .newPlayerBuilder()
                .getPlayer(1)
                .throwIf(new PlayerNullException(), cmdBuilder -> cmdBuilder.beforeBuild() == null)
                .build();
        requested.put(player.getName(), extract.getName());
        ChatHelper.sendMessage(player, "&aWyslales prosbe o teleportacje do " + extract.getName());
        ChatHelper.sendMessage(extract, "&aOtrzymales prosbe o teleportacje od " + player.getName());


    }
}
