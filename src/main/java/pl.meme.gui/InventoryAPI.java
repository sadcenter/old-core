package pl.meme.gui;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.plugin.Plugin;

public class InventoryAPI implements Listener {
    public InventoryAPI(Plugin plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (e.getClickedInventory() != null && e.getClickedInventory().getHolder() instanceof GuiHolder)
            ((GuiHolder) e.getClickedInventory().getHolder()).handleClick(e);
    }
}
