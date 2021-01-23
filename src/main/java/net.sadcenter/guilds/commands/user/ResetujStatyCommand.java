package net.sadcenter.guilds.commands.user;

import net.sadcenter.commons.command.Command;
import net.sadcenter.commons.command.CommandBuilder;
import net.sadcenter.commons.helper.ChatHelper;
import net.sadcenter.guilds.basic.User;
import net.sadcenter.guilds.utils.ItemUtil;
import net.sadcenter.guilds.utils.UserUtil;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ResetujStatyCommand extends Command {


    public ResetujStatyCommand() {
        super("resetujstatystyki", true);
    }

    @Override
    public void onCommand(CommandSender executor, CommandBuilder commandBuilder, String... args) {
        Player p = (Player) executor;
        int amount = ItemUtil.getAmount(p, Material.EMERALD, 0);
        if (amount < 32) {
            ChatHelper.sendMessage(p, "&cNie posiadasz 32 emeraldow!");
            return;
        }
        User u = UserUtil.get(p.getUniqueId());
        u.setAssists(0);
        u.setDeaths(0);
        u.setKills(0);
        p.getInventory().remove(new ItemStack(Material.EMERALD, 64));
        ChatHelper.sendTitle(p, "", "&aZresetowales swoje statystyki!");
    }
}
