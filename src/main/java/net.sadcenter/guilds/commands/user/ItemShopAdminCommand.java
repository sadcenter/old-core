package net.sadcenter.guilds.commands.user;

import net.sadcenter.commons.command.Command;
import net.sadcenter.commons.command.CommandBuilder;
import net.sadcenter.commons.helper.ChatHelper;
import net.sadcenter.guilds.LastCore;
import net.sadcenter.guilds.basic.ItemShop;
import net.sadcenter.guilds.basic.fields.ShopProductField;
import net.sadcenter.guilds.config.impl.storage.MainConfigurationStorage;
import net.sadcenter.guilds.managers.ItemShopManager;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import java.util.HashSet;

/**
 * @author sadcenter on 16.08.2020
 * @project LASTCORE
 */

public class ItemShopAdminCommand extends Command {

    private final MainConfigurationStorage configurationStorage;
    private final ItemShopManager itemShopManager;

    public ItemShopAdminCommand() {
        super("itemshop", "core.cmd.itemshop", false);
        this.configurationStorage = LastCore.getInstance().getConfiguration().getStorage();
        this.itemShopManager = LastCore.getInstance().getItemShopManager();
    }

    @Override
    public void onCommand(CommandSender executor, CommandBuilder commandBuilder, String... args) {

        if (args.length != 2) {
            ChatHelper.sendMessage(executor, "&aWszystkie uslugi: &3{SERVICES}".replace("{SERVICES}", String.join("&7, &3", configurationStorage.getMapOfServices().keySet())));
            return;
        }

        String name = args[0];
        String item = args[1];

        for (ShopProductField value : this.configurationStorage.getMapOfServices().values()) {
            if (value.getName().equalsIgnoreCase(item)) {
                ItemShop product = new ItemShop(0, name, value.getCustomName(), value.getAmount(),
                        value.getCommand().replace("{NICK}", name), value.getMaterial(), new HashSet<>(value.getCustomLores()), System.currentTimeMillis());
                itemShopManager.create(product);
                value.getMessages().forEach(s -> Bukkit.broadcastMessage(ChatHelper.fixColor(s).replace("{NICK}", name)));
            }
        }
    }

}
