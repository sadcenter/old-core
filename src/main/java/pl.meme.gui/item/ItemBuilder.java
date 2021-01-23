package pl.meme.gui.item;

import net.sadcenter.commons.helper.ChatHelper;
import org.apache.commons.lang.Validate;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public abstract class ItemBuilder<T extends ItemBuilder<T>> {
    private final ItemMeta meta;

    private final ItemStack itemStack;

    public ItemBuilder(Material material) {
        this(new ItemStack(material, 1));
    }

    public ItemBuilder(ItemStack item) {
        this.itemStack = new ItemStack(item);
        this.meta = item.getItemMeta();
    }

    protected abstract T getThis();

    public T withItemName(String itemName) {
        meta.setDisplayName(ChatHelper.fixColor(itemName));
        return getThis();
    }

    public T withLore(List<String> lore) {
        meta.setLore(lore.stream().map(ChatHelper::fixColor).collect(Collectors.toList()));
        return getThis();
    }

    public T withLore(String... lore) {
        return withLore(Arrays.asList(lore));
    }

    public T withAmount(int amount) {
        itemStack.setAmount(amount);
        return getThis();
    }

    public T withItemData(short data) {
        itemStack.setDurability(data);
        return getThis();
    }

    public T withColor(DyeColor color) {
        if (itemStack.getType() == Material.INK_SACK)
            return withItemData(color.getDyeData());
        else
            return withItemData(color.getWoolData());
    }

    public T withEnchantment(boolean enabled) {
        if (!enabled) return getThis();

        meta.addEnchant(Enchantment.OXYGEN, 1, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        return getThis();
    }

    public T withEnchantment(Enchantment enchantment, int level) {
        itemStack.addUnsafeEnchantment(enchantment, level);
        return getThis();
    }

    public T withSkullOwner(String ownerName) {
        Validate.isTrue(meta instanceof SkullMeta, "Not an skull.");
        if (itemStack.getDurability() != 3) itemStack.setDurability((short) 3);

        ((SkullMeta) this.meta).setOwner(ownerName);
        return getThis();
    }

    public ItemStack buildItem() {
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    public T addLore(String s) {
        List<String> lore = meta.getLore();
        lore.add(s);

        return withLore(lore);
    }

    public T addLore(List<String> lore) {
        List<String> lor = meta.getLore();
        lor.addAll(lore);

        return withLore(lor);
    }
}
