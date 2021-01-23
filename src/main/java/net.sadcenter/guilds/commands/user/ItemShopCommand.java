package net.sadcenter.guilds.commands.user;

import net.sadcenter.commons.command.Command;
import net.sadcenter.commons.command.CommandBuilder;
import net.sadcenter.commons.helper.ChatHelper;
import net.sadcenter.guilds.LastCore;
import net.sadcenter.guilds.basic.ItemShop;
import net.sadcenter.guilds.guis.ItemShopGui;
import net.sadcenter.guilds.managers.ItemShopManager;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Set;

/**
 * @author sadcenter on 16.08.2020
 * @project LASTCORE
 */

public class ItemShopCommand extends Command {
    private final ItemShopManager itemShopManager = LastCore.getInstance().getItemShopManager();

    public ItemShopCommand() {
        super("is", true);
    }

    @Override
    public void onCommand(CommandSender executor, CommandBuilder commandBuilder, String... args) {
        Player player = (Player) executor;
        Set<ItemShop> productSet = itemShopManager.getShopItems(player.getName());
        if (productSet.size() == 0) {
            ChatHelper.sendMessage(player, "&c0 itemow :(");
            return;
        }
        player.openInventory(new ItemShopGui(player, productSet).build());
    }
}
