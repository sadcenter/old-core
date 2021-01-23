package net.sadcenter.guilds.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.jetbrains.annotations.NotNull;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ItemUtil {

    public static int getAmount(@NotNull Player player, Material material, int durability) {
        int amount = 0;
        for (ItemStack itemStack : player.getInventory()) {
            if (itemStack != null && itemStack.getType().equals(material) && itemStack.getDurability() == durability) {
                amount += itemStack.getAmount();
            }
        }
        return amount;
    }

    @NotNull
    public static ItemStack getSkull(String name) {
        ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) SkullType.PLAYER.ordinal());
        SkullMeta meta = (SkullMeta) Bukkit.getItemFactory().getItemMeta(Material.SKULL_ITEM);
        meta.setOwner(name);
        skull.setItemMeta(meta);
        return skull;
    }

}
