package net.sadcenter.guilds.guis;

import net.sadcenter.commons.helper.ChatHelper;
import net.sadcenter.commons.helper.ItemBuilder;
import net.sadcenter.guilds.basic.fields.ChatOptionsField;
import net.sadcenter.guilds.utils.UserUtil;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import pl.meme.gui.GuiBuilder;
import pl.meme.gui.GuiItem;

import java.util.Collections;

/**
 * @author sadcenter on 13.09.2020
 * @project LASTCORE
 */

public final class ChatOptionsGui extends GuiBuilder {

    public ChatOptionsGui(@NotNull Player p) {
        super("OPCJE CHATU", InventoryType.CHEST);
        ChatOptionsField chatOptionsField = UserUtil.get(p.getUniqueId()).getChatOptions();
        setItem(10, new GuiItem.Builder(Material.PAPER)
                .withItemName("&7Wiadomosci z &3chatu")
                .withLore(ChatHelper.fixColor(Collections.singletonList("&7Status &3" + (chatOptionsField.isChatMessages() ? "on" : "&coff"))))
                .withClickExecutor(e -> {
                    chatOptionsField.setChatMessages(!chatOptionsField.isChatMessages());
                    ChatHelper.sendMessage(p, (chatOptionsField.isCaseMessages() ? "&3Wlaczyles" : "&3Wylaczyles") + " &7wiadomosci z czatu");
                    p.openInventory(new ChatOptionsGui(p).build());
                }).build());

        setItem(12, new GuiItem.Builder(Material.PAPER)
                .withItemName("&7Wiadomosci o&3 smierci")
                .withLore(ChatHelper.fixColor(Collections.singletonList("&7Status &3" + (chatOptionsField.isDeathMessages() ? "on" : "&coff"))))
                .withClickExecutor(e -> {
                    chatOptionsField.setDeathMessages(!chatOptionsField.isDeathMessages());
                    ChatHelper.sendMessage(p, (chatOptionsField.isDeathMessages() ? "&3Wlaczyles" : "&3Wylaczyles") + " &7wiadomosci o smierci");
                    p.openInventory(new ChatOptionsGui(p).build());
                }).build());

        setItem(14, new GuiItem.Builder(Material.PAPER)
                .withItemName("&7Wiadomosci o&3 dropu z casow")
                .withLore(ChatHelper.fixColor(Collections.singletonList("&7Status &3" + (chatOptionsField.isCaseMessages() ? "on" : "&coff"))))
                .withClickExecutor(e -> {
                    chatOptionsField.setCaseMessages(!chatOptionsField.isCaseMessages());
                    ChatHelper.sendMessage(p, (chatOptionsField.isCaseMessages() ? "&3Wlaczyles" : "&3Wylaczyles") + " &7wiadomosci o smierci");
                    p.openInventory(new ChatOptionsGui(p).build());
                }).build());

        setItem(16, new GuiItem.Builder(Material.PAPER)
                .withItemName("&3Automatyczne &7wiadomosci")
                .withLore(ChatHelper.fixColor(Collections.singletonList("&7Status &3" + (chatOptionsField.isAutoMessages() ? "on" : "&coff"))))
                .withClickExecutor(e -> {
                    chatOptionsField.setAutoMessages(!chatOptionsField.isAutoMessages());
                    ChatHelper.sendMessage(p, (chatOptionsField.isAutoMessages() ? "&3Wlaczyles" : "&3Wylaczyles") + " &7automatyczne &7wiadomosci");
                    p.openInventory(new ChatOptionsGui(p).build());
                }).build());


        Inventory inv = this.build();
        ItemStack of = ItemBuilder.of(Material.STAINED_GLASS_PANE, 1, 8).build();
        for (int i = 0; i < 27; i++) {
            ItemStack o = inv.getItem(i);
            if (o == null || o.getType() == Material.AIR) {
                setItem(i, new GuiItem.Builder(of).build());
            }
        }
    }
}
