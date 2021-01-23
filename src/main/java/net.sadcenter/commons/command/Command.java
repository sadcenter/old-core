package net.sadcenter.commons.command;

import lombok.Getter;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
public abstract class Command {

    private final String name;
    private final String permission;
    private final List<String> aliases;
    private final boolean onlyPlayerCommand;
    public String usage;
    public String description;
    public boolean playerCommand;

    public Command(String name) {
        this.name = name;
        this.usage = "";
        this.permission = "";
        this.aliases = new ArrayList<>();
        this.description = "";
        this.onlyPlayerCommand = true;
    }

    public Command(String name, boolean onlyPlayerCommand) {
        this.name = name;
        this.usage = "";
        this.permission = "";
        this.aliases = new ArrayList<>();
        this.description = "";
        this.onlyPlayerCommand = onlyPlayerCommand;
    }

    public Command(String name, String permission, boolean onlyPlayerCommand) {
        this.name = name;
        this.usage = "";
        this.permission = permission;
        this.aliases = new ArrayList<>();
        this.description = "";
        this.onlyPlayerCommand = onlyPlayerCommand;
    }

    public Command(String name, String[] allias) {
        this.name = name;
        this.usage = "";
        this.permission = "";
        this.aliases = Arrays.asList(allias);
        this.description = "";
        this.onlyPlayerCommand = false;
    }

    public Command(String name, List<String> allias) {
        this.name = name;
        this.usage = "";
        this.permission = "";
        this.aliases = allias;
        this.description = "";
        this.onlyPlayerCommand = false;
    }

    public Command(String name, String permission, String[] allias) {
        this.name = name;
        this.usage = "";
        this.permission = permission;
        this.aliases = Arrays.asList(allias);
        this.description = "";
        this.onlyPlayerCommand = false;
    }

    public Command(String name, String permission, String[] allias, boolean onlyPlayerCommand) {
        this.name = name;
        this.usage = "";
        this.permission = permission;
        this.aliases = Arrays.asList(allias);
        this.description = "";
        this.onlyPlayerCommand = onlyPlayerCommand;
    }

    public Command(String name, List<String> alliases, boolean onlyPlayerCommand) {
        this.name = name;
        this.usage = "";
        this.permission = "";
        this.aliases = alliases;
        this.description = "";
        this.onlyPlayerCommand = onlyPlayerCommand;
    }

    public Command(String name, String[] alliases, boolean onlyPlayerCommand) {
        this.name = name;
        this.usage = "";
        this.permission = "";
        this.aliases = Arrays.asList(alliases);
        this.description = "";
        this.onlyPlayerCommand = onlyPlayerCommand;
    }

    public abstract void onCommand(CommandSender executor, CommandBuilder commandBuilder, String... args) throws CommandException;
}