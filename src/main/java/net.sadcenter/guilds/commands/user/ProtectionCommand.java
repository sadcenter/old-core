package net.sadcenter.guilds.commands.user;

import net.sadcenter.commons.command.Command;
import net.sadcenter.commons.command.CommandBuilder;
import net.sadcenter.commons.command.CommandException;
import net.sadcenter.commons.helper.ChatHelper;
import net.sadcenter.guilds.managers.ProtectionManager;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author sadcenter on 09.10.2020
 * @project LASTCORE
 */
public class ProtectionCommand extends Command {

    private final ProtectionManager protectionManager;

    public ProtectionCommand(ProtectionManager protectionManager) {
        super("ochrona", true);
        this.protectionManager = protectionManager;
    }

    @Override
    public void onCommand(CommandSender executor, CommandBuilder commandBuilder, String... args) throws CommandException {
        Player player = (Player) executor;
        if (protectionManager.getProtections().containsKey(player.getUniqueId())) {
            protectionManager.getProtections().get(player.getUniqueId()).setExpire(System.currentTimeMillis());
        } else
            ChatHelper.sendMessage(player, "&cNie posiadasz ochrony");

    }
}
