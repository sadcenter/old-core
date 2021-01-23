package net.sadcenter.guilds.commands.user;

import net.sadcenter.commons.command.Command;
import net.sadcenter.commons.command.CommandBuilder;
import net.sadcenter.commons.command.CommandException;
import net.sadcenter.commons.exceptions.impl.NotEnoughArgumentsException;
import net.sadcenter.commons.exceptions.impl.PlayerNullException;
import net.sadcenter.commons.helper.ChatHelper;
import net.sadcenter.commons.helper.GameModeHelper;
import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Optional;

/**
 * @author sadcenter on 15.08.2020
 * @project LASTCORE
 */
public class GameModeCommand extends Command {
    public GameModeCommand() {
        super("gm", "core.cmd.gm", new String[]{"gamemode"}, false);
    }

    @Override
    public void onCommand(CommandSender executor, CommandBuilder commandBuilder, String... args) throws CommandException {
        commandBuilder
                .newArgumentsBuilder()
                .throwError(1, new NotEnoughArgumentsException("gm [tryb] [nick]"));

        Optional<GameMode> gameMode = GameModeHelper.parseGameMode(args[0]);
        if (!gameMode.isPresent()) {
            ChatHelper.sendMessage(executor, "Taki gamemode nie istnieje...");
            return;
        }
        Player to = commandBuilder
                .newPlayerBuilder()
                .getPlayer(2)
                .orElse((Player) executor)
                .throwIf(new PlayerNullException(), cmdBuilder -> cmdBuilder.getArgs().length == 2 && cmdBuilder.beforeBuild() == null)
                .build();


        to.setGameMode(gameMode.get());
        ChatHelper.sendMessage(executor, "&7Ustawiles gamemode &3" + to.getGameMode().toString().toUpperCase() + (to.getName().equals(executor.getName()) ? "" : " &adla &3" + to.getName()));
    }
}
