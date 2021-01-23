package net.sadcenter.guilds.commands.user;

import net.sadcenter.commons.command.Command;
import net.sadcenter.commons.command.CommandBuilder;
import net.sadcenter.commons.command.CommandException;
import net.sadcenter.commons.exceptions.impl.NotEnoughArgumentsException;
import net.sadcenter.commons.helper.ChatHelper;
import net.sadcenter.guilds.LastCore;
import net.sadcenter.guilds.basic.Warp;
import net.sadcenter.guilds.config.impl.MainConfiguration;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Optional;

/**
 * @author sadcenter on 15.09.2020
 * @project LASTCORE
 */

public class SetWarpCommand extends Command {

    private final MainConfiguration configuration;

    public SetWarpCommand(MainConfiguration configuration) {
        super("setwarp", "core.cmd.setwarp", true);
        this.configuration = configuration;
    }

    @Override
    public void onCommand(CommandSender executor, CommandBuilder commandBuilder, String... args) throws CommandException {
        commandBuilder
                .newArgumentsBuilder()
                .throwError(1, new NotEnoughArgumentsException("setwarp [nazwa]"));
        Player player = (Player) executor;
        Optional<Warp> warp = LastCore.getInstance().getConfiguration().getStorage().getWarp(args[0]);
        if (warp.isPresent()) {
            ChatHelper.sendMessage(player, "&cTaki warp juz istnieje");
            return;
        }
        Warp newWarp = new Warp(player.getLocation());
        configuration.getStorage().getWarps().put(args[0], newWarp);
        ChatHelper.sendMessage(player, "&AUstawiles nowy warp!");
    }
}
