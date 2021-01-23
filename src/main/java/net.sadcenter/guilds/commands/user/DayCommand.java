package net.sadcenter.guilds.commands.user;

import net.sadcenter.commons.command.Command;
import net.sadcenter.commons.command.CommandBuilder;
import net.sadcenter.commons.helper.ChatHelper;
import net.sadcenter.guilds.utils.Worlds;
import org.bukkit.World;
import org.bukkit.command.CommandSender;

/**
 * @author sadcenter on 20.08.2020
 * @project LASTCORE
 */

public class DayCommand extends Command {


    public DayCommand() {
        super("day", "core.cmd.day", false);
    }

    @Override
    public void onCommand(CommandSender executor, CommandBuilder commandBuilder, String... args) {
        World world = Worlds.DEAFULT_WORLD;
        world.setTime(0);
        world.setThundering(false);
        world.setThunderDuration(0);
        ChatHelper.sendMessage(executor, "&aUstawiono dzien!");
    }
}
