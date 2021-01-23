package net.sadcenter.commons.helper;

import net.sadcenter.commons.command.BukkitCommand;
import net.sadcenter.commons.command.Command;
import net.sadcenter.commons.command.CommandManager;
import org.bukkit.command.CommandMap;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class CommandManagerImpl implements CommandManager {

    private final CommandMap commandMap;

    public CommandManagerImpl(@NotNull CommandMap map) {
        this.commandMap = map;
    }

    @Override
    public void registerCommand(@NotNull Command command) {
        this.commandMap.register(command.getName(), new BukkitCommand(command));
    }

    @Override
    public void registerCommands(@NotNull Command... commands) {
        Arrays.stream(commands).forEachOrdered(this::registerCommand);
    }
}