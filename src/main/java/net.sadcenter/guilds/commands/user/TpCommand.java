package net.sadcenter.guilds.commands.user;

import net.sadcenter.commons.command.Command;
import net.sadcenter.commons.command.CommandBuilder;
import net.sadcenter.commons.command.CommandException;
import net.sadcenter.commons.exceptions.impl.NotEnoughArgumentsException;
import net.sadcenter.commons.exceptions.impl.PlayerNullException;
import net.sadcenter.commons.helper.ChatHelper;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author sadcenter on 14.09.2020
 * @project LASTCORE
 */

public class TpCommand extends Command {

    public TpCommand() {
        super("tp", "core.cmd.tp", true);
    }

    @Override
    public void onCommand(CommandSender executor, CommandBuilder commandBuilder, String... args) throws CommandException {
        commandBuilder
                .newArgumentsBuilder()
                .throwError(1, new NotEnoughArgumentsException("tp [nick] lub tp [x] [y] [z]"));

        Player player = (Player) executor;

        if (args.length == 1) {
            Player to = commandBuilder
                    .newPlayerBuilder()
                    .getPlayer(1)
                    .throwIf(new PlayerNullException(), playerBuilder -> playerBuilder.beforeBuild() == null)
                    .build();
            ChatHelper.sendMessage(player, "&7Przteleportowano do &3" + to.getName());
            player.teleport(to.getLocation());
        } else if (args.length == 3) {
            int x = Integer.parseInt(args[0]);
            int y = Integer.parseInt(args[1]);
            int z = Integer.parseInt(args[2]);
            player.teleport(new Location(player.getWorld(), x, y, z));
            ChatHelper.sendMessage(player, "&7Przteleportowano do &3" + x + "&7:&3" + y + "&7:&3" + z);
        }
    }
}
