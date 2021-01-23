package pl.meme.gui;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public class GuiHolder implements InventoryHolder {

    private final GuiBuilder builder;

    public GuiHolder(GuiBuilder builder) {
        this.builder = builder;
    }

    public void handleClick(InventoryClickEvent e) {
        e.setCancelled(true);

        GuiItem item = builder.getGuiItem(e.getRawSlot());

        if (item == null) return;

        if (item.getExecutor() != null)
            item.execute(e);

        if (builder.click != null)
            builder.click.execute(e);

    }

    @Override
    public Inventory getInventory() {
        return builder.build();
    }
}
