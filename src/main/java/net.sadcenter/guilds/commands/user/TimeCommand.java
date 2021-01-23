package net.sadcenter.guilds.commands.user;

import com.comphenix.protocol.ProtocolLibrary;
import net.sadcenter.commons.command.Command;
import net.sadcenter.commons.command.CommandBuilder;
import net.sadcenter.commons.command.CommandException;
import net.sadcenter.commons.exceptions.impl.NotEnoughArgumentsException;
import net.sadcenter.commons.exceptions.impl.NotNumberException;
import net.sadcenter.commons.helper.ChatHelper;
import org.apache.commons.lang.math.NumberUtils;
import org.bukkit.WeatherType;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author sadcenter on 18.11.2020
 * @project LastCoree
 */

public class TimeCommand extends Command {

    public TimeCommand() {
        super("czas", true);
    }

    @Override
    public void onCommand(CommandSender executor, CommandBuilder commandBuilder, String... args) throws CommandException {
        commandBuilder
                .newArgumentsBuilder()
                .throwError(1, new NotEnoughArgumentsException("time [czas/day/night]"));

        Player player = (Player) executor;
        String arg = args[0];
        if(NumberUtils.isNumber(arg)) {
            player.setPlayerTime(Integer.parseInt(arg), true);
            ChatHelper.sendMessage(player, "&7Ustawiono czas na &3" + arg);
        } else {
            if(arg.equalsIgnoreCase("day")) {
                player.setPlayerTime(0, true);
            } else if(arg.equalsIgnoreCase("night")) {
                player.setPlayerTime(14000, true);
            } else {
                ChatHelper.sendMessage(player, "&cDAY/NIGHT");
                return;
            }
            ChatHelper.sendMessage(player, "&7Ustawiono czas na &3" + arg.toUpperCase());
        }
    }
}

