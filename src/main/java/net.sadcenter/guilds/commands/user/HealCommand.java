package net.sadcenter.guilds.commands.user;

import net.sadcenter.commons.command.Command;
import net.sadcenter.commons.command.CommandBuilder;
import net.sadcenter.commons.command.CommandException;
import net.sadcenter.commons.exceptions.impl.PlayerNullException;
import net.sadcenter.commons.helper.ChatHelper;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

/**
 * @author sadcenter on 17.08.2020
 * @project LASTCORE
 */

public class HealCommand extends Command {
    public HealCommand() {
        super("heal", "core.cmd.heal", false);
    }

    @Override
    public void onCommand(CommandSender executor, CommandBuilder commandBuilder, String... args) throws CommandException {
        Player toHeal = commandBuilder
                .newPlayerBuilder()
                .getPlayer(1)
                .orElse((Player) executor)
                .throwIf(new PlayerNullException(), playerBuilder -> playerBuilder.getArgs().length == 1 && playerBuilder.beforeBuild() == null)
                .build();

        toHeal.setHealth(20);
        toHeal.setFoodLevel(20);
        ChatHelper.sendMessage(toHeal, "&aZostales uleczony!");
        for (PotionEffect activePotionEffect : toHeal.getActivePotionEffects()) {
            toHeal.removePotionEffect(activePotionEffect.getType());
        }
    }
}
