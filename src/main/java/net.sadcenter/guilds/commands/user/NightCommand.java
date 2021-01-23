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

public class NightCommand extends Command {

    public NightCommand() {
        super("night", "core.cmd.night", false);
    }

    @Override
    public void onCommand(CommandSender executor, CommandBuilder commandBuilder, String... args) {
        World world = Worlds.DEAFULT_WORLD;
        world.setTime(19000L);
        world.setThundering(false);
        world.setThunderDuration(0);
        ChatHelper.sendMessage(executor, "&aUstawiono noc!");
    }
}
