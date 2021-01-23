package net.sadcenter.commons.command.builders.impl;

import lombok.RequiredArgsConstructor;
import net.sadcenter.commons.command.CommandException;
import net.sadcenter.commons.command.builders.Builder;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.function.Predicate;

@RequiredArgsConstructor
public class PlayerBuilder implements Builder {

    private final String[] args;
    private CommandException error;
    private Predicate<PlayerBuilder> predicate;
    private Player builded = null;
    private Player ifNull = null;

    public PlayerBuilder getPlayer(int arg) {
        if (args.length >= arg)
            this.builded = Bukkit.getPlayer(args[arg - 1]);


        return this;
    }

    public PlayerBuilder throwIf(CommandException commandException, Predicate<PlayerBuilder> predicate) {
        this.error = commandException;
        this.predicate = predicate;
        return this;
    }

    public PlayerBuilder orElse(Player player) {
        this.ifNull = player;
        return this;
    }

    public Player build() throws CommandException {
        if (predicate.test(this)) {
            throw error;
        }
        if (builded == null)
            return ifNull;
        else
            return builded;
    }

    public Player beforeBuild() {
        return builded;
    }

    public String[] getArgs() {
        return args;
    }


}
