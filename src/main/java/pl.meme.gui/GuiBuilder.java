package pl.meme.gui;

import net.sadcenter.commons.helper.ChatHelper;
import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class GuiBuilder {
    private final String inventoryTitle;
    private final HashMap<Integer, GuiItem> inventoryItems = new HashMap<>();
    public GuiItemExecutor click;
    private InventoryType inventoryType;
    private int inventorySize;

    public GuiBuilder(String inventoryTitle, int rows) {
        this.inventorySize = rows * 9;
        this.inventoryTitle = ChatHelper.fixColor(inventoryTitle);
    }

    public GuiBuilder(String inventoryTitle, InventoryType type) {
        this.inventoryType = type;
        this.inventoryTitle = ChatHelper.fixColor(inventoryTitle);
    }

    public Inventory build() {
        Inventory inventory;

        if (inventoryType != null)
            inventory = Bukkit.createInventory(new GuiHolder(this), inventoryType, inventoryTitle);
        else inventory = Bukkit.createInventory(new GuiHolder(this), inventorySize, inventoryTitle);

        for (Map.Entry<Integer, GuiItem> item : inventoryItems.entrySet()) {
            inventory.setItem(item.getKey(), item.getValue().getItemStack());
        }

        return inventory;
    }

    protected GuiItem getGuiItem(int rawSlot) {
        return inventoryItems.get(rawSlot);
    }

    protected void setItem(int rawLocation, @NotNull GuiItem item) {
        inventoryItems.put(rawLocation, item);
    }

    protected void setItem(int y, int x, @NotNull GuiItem item) {
        inventoryItems.put(y * 9 + x, item);
    }

    protected void addItem(@NotNull GuiItem item) {
        inventoryItems.put(inventoryItems.size(), item);
    }
}
