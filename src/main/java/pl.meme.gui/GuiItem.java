package pl.meme.gui;

import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import pl.meme.gui.item.ItemBuilder;

public class GuiItem {
    private final GuiItemExecutor executor;
    private final ItemStack itemStack;

    private GuiItem(GuiItemExecutor executor, ItemStack itemStack) {
        this.executor = executor;
        this.itemStack = itemStack;
    }

    public GuiItemExecutor getExecutor() {
        return executor;
    }

    public void execute(InventoryClickEvent e) {
        this.executor.execute(e);
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    public static class Builder extends ItemBuilder<Builder> {
        private GuiItemExecutor executor;

        public Builder(Material material) {
            super(material);
        }

        public Builder(ItemStack item) {
            super(item);
        }

        @Override
        protected Builder getThis() {
            return this;
        }

        public Builder withClickExecutor(GuiItemExecutor e) {
            this.executor = e;
            return this;
        }

        public GuiItem build() {
            return new GuiItem(executor, super.buildItem());
        }
    }
}
