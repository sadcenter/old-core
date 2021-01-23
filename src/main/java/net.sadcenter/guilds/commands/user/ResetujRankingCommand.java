package net.sadcenter.guilds.commands.user;

import net.sadcenter.commons.command.Command;
import net.sadcenter.commons.command.CommandBuilder;
import net.sadcenter.commons.helper.ChatHelper;
import net.sadcenter.guilds.utils.ItemUtil;
import net.sadcenter.guilds.utils.UserUtil;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ResetujRankingCommand extends Command {


    public ResetujRankingCommand() {
        super("resetujranking", true);
    }

    @Override
    public void onCommand(CommandSender executor, CommandBuilder commandBuilder, String... args) {
        Player p = (Player) executor;
        final int amount = ItemUtil.getAmount(p, Material.EMERALD, 0);
        if (amount < 64) {
            ChatHelper.sendMessage(p, "&cNie posiadasz 64 emeraldow!");
            return;
        }
        UserUtil.get(p.getUniqueId()).setPoints(1000);
        p.getInventory().removeItem(new ItemStack(Material.EMERALD, 64));
        ChatHelper.sendTitle(p, "", "&aZresetowales swoj ranking!");
    }
}
