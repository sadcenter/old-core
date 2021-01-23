package net.sadcenter.guilds.commands.user;

import net.sadcenter.commons.command.Command;
import net.sadcenter.commons.command.CommandBuilder;
import net.sadcenter.commons.command.CommandException;
import net.sadcenter.commons.exceptions.impl.NotEnoughArgumentsException;
import net.sadcenter.commons.exceptions.impl.PlayerNullException;
import net.sadcenter.commons.helper.ChatHelper;
import net.sadcenter.guilds.LastCore;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Map;

/**
 * @author sadcenter on 15.08.2020
 * @project LASTCORE
 */

public class TpacceptCommand extends Command {
    public TpacceptCommand() {
        super("tpaccept", true);
    }

    @Override
    public void onCommand(CommandSender executor, CommandBuilder commandBuilder, String... args) throws CommandException {
        commandBuilder
                .newArgumentsBuilder()
                .throwError(1, new NotEnoughArgumentsException("tpaccept [nick/all]"));
        Player player = (Player) executor;
        final Map<String, String> requested = LastCore.getInstance().getTpaCommand().getRequested();
        if (args[0].equalsIgnoreCase("all")) {
            ChatHelper.sendMessage(executor, "&7Zakceptowales wszystkich!");
            requested.entrySet().stream().filter(stringStringEntry -> stringStringEntry.getValue().equals(player.getName())).forEachOrdered(p -> {
                final Player o = Bukkit.getPlayerExact(p.getKey());
                if (o == null) {
                    ChatHelper.sendMessage(player, "&cGracz " + p.getKey() + "jest offline");
                    return;
                }
                ChatHelper.sendMessage(player, "&7Zaakceptowales teleportacje od &3" + o.getName());
                ChatHelper.sendMessage(o, "&7Gracz &3" + player.getName() + "&7 zaakceptowal twoja teleportacje!");
                LastCore.getInstance().getTeleportManager().add(o, player.getLocation());
                LastCore.getInstance().getTpaCommand().getRequested().remove(o.getName());
            });
        } else {
            Player exact = commandBuilder
                    .newPlayerBuilder()
                    .getPlayer(1)
                    .throwIf(new PlayerNullException(), cmdBuilder -> cmdBuilder.beforeBuild() == null)
                    .build();

            if (requested.containsKey(exact.getName()) && requested.get(exact.getName()).equalsIgnoreCase(player.getName())) {
                ChatHelper.sendMessage(player, "&7Zaakceptowales teleportacje od &3" + exact.getName());
                ChatHelper.sendMessage(exact, "&7Gracz &3" + player.getName() + "&7 zaakceptowal twoja teleportacje!");
                LastCore.getInstance().getTeleportManager().add(exact, player.getLocation());
                LastCore.getInstance().getTpaCommand().getRequested().remove(exact.getName());
            } else
                ChatHelper.sendMessage(player, "&7Ten gracz nie wyslal do ciebie teleportacji!");

        }
    }
}
