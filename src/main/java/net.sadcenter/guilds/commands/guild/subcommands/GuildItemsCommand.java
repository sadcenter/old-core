package net.sadcenter.guilds.commands.guild.subcommands;

import net.sadcenter.commons.command.Command;
import net.sadcenter.commons.command.CommandBuilder;
import net.sadcenter.commons.command.CommandException;
import net.sadcenter.commons.helper.ChatHelper;
import net.sadcenter.commons.helper.ItemBuilder;
import net.sadcenter.guilds.storage.Settings;
import net.sadcenter.guilds.utils.ItemUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;

public class GuildItemsCommand extends Command {
    private final List<ItemStack> guildItems;

    public GuildItemsCommand(List<ItemStack> items) {
        super("itemy");
        this.guildItems = items;
    }

    @Override
    public void onCommand(CommandSender executor, CommandBuilder commandBuilder, String... args) throws CommandException {
        if (!Settings.isEnabledGuildItems())
            throw new CommandException("&cItemy na gildie aktualnie sa wylaczone");

        Player player = (Player) executor;
        player.openInventory(build(player));

    }


    private Inventory build(Player player) {
        Inventory inventory = Bukkit.createInventory(null, 27, ChatHelper.fixColor("&3&lITEMY NA GILDIE"));
        Inventory playerInventory = player.getInventory();
        guildItems.forEach(itemStackk -> {
            ItemStack itemStack = itemStackk.clone();
            int amount = (int) (player.hasPermission("scode.svip")
                    ?
                    itemStack.getAmount() * .50
                    :
                    player.hasPermission("scode.vip")
                            ?
                            itemStack.getAmount() * .25 :
                            itemStack.getAmount());
            int t = ItemUtil.getAmount(player, itemStack.getType(), itemStack.getDurability());
            inventory.addItem(ItemBuilder.of(itemStack)
                    .addLore(Arrays.asList(
                            " ",
                            " &8>> &7Posiadasz: &3" + t + "&7/&3" + amount,
                            " "))
                    .setAmount(amount)
                    .build());

        });
        return inventory;
    }


}
