package net.sadcenter.guilds.drop.listeners;

import net.sadcenter.commons.helper.ChatHelper;
import net.sadcenter.guilds.drop.DropGui;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

/**
 * @author sadcenter on 07.11.2020
 * @project LastCore
 */

public final class DropClick implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if (event.getClickedInventory() != null && event.getClickedInventory().getName().equals(ChatHelper.fixColor("&3&lDROP"))) {
            event.setCancelled(true);
            event.getWhoClicked().openInventory(new DropGui((Player) event.getWhoClicked()).build());
        }
    }


}
