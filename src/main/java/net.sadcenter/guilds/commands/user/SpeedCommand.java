package net.sadcenter.guilds.commands.user;

import net.sadcenter.commons.command.Command;
import net.sadcenter.commons.command.CommandBuilder;
import net.sadcenter.commons.command.CommandException;
import net.sadcenter.commons.exceptions.impl.NotEnoughArgumentsException;
import net.sadcenter.commons.exceptions.impl.NotNumberException;
import net.sadcenter.commons.helper.ChatHelper;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author sadcenter on 05.09.2020
 * @project LASTCORE
 */

public class SpeedCommand extends Command {

    public SpeedCommand() {
        super("speed", "core.cmd.speed", true);
    }

    @Override
    public void onCommand(CommandSender executor, CommandBuilder commandBuilder, String... args) throws CommandException {
        commandBuilder
                .newArgumentsBuilder()
                .throwError(1, new NotEnoughArgumentsException("fly [speed]"))
                .throwIfNotNumber(new NotNumberException(1), 1);

        Player player = (Player) executor;
        final float f = Float.parseFloat(args[0]);
        if (player.isFlying()) {
            if (f > 10f) {
                ChatHelper.sendMessage(player, "&cprzekroczyles limit 100");
                return;
            }
            player.setFlySpeed(getRealMoveSpeed(f, true));
            ChatHelper.sendMessage(player, "&aUstawiles szybkosc fly na " + args[0]);
        } else {
            if (f > 10f) {
                ChatHelper.sendMessage(player, "&cprzekroczyles limit 100");
                return;
            }
            player.setWalkSpeed(getRealMoveSpeed(f, false));
            ChatHelper.sendMessage(player, "&aUstawiles szybkosc walk na " + args[0]);
        }
    }

    private float getRealMoveSpeed(final float userSpeed, final boolean isFly) {
        final float defaultSpeed = isFly ? 0.1f : 0.2f;
        float maxSpeed = 1.0f;
        if (userSpeed < 1.0f) {
            return defaultSpeed * userSpeed;
        }
        final float ratio = (userSpeed - 1.0f) / 9.0f * (maxSpeed - defaultSpeed);
        return ratio + defaultSpeed;
    }
}
