package net.sadcenter.commons.command;

import org.jetbrains.annotations.NotNull;


public interface CommandManager {

    void registerCommand(@NotNull Command command);

    void registerCommands(@NotNull Command... commands);

}