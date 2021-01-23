package net.sadcenter.guilds.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.sadcenter.commons.helper.ChatHelper;
import net.sadcenter.guilds.LastCore;
import org.apache.commons.lang3.math.NumberUtils;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ParserUtils {


    public static @NotNull
    ItemStack parseItemStack(@NotNull String string) {

        final ItemStack is = new ItemStack(Material.DIAMOND);
        final ItemMeta im = is.getItemMeta();

        final String[] args = string.split(" ");
        for (String arg : args) {
            final String[] splitArg = arg.split(":");
            final String key = splitArg[0];
            final String value = splitArg[1];
            if (key.equalsIgnoreCase("material")) {
                final Material mat = Material.matchMaterial(value);
                if (mat == null) continue;
                is.setType(mat);
            } else if (key.equalsIgnoreCase("amount")) {
                if (!NumberUtils.isNumber(value)) continue;
                is.setAmount(Integer.parseInt(value));
            } else if (key.equalsIgnoreCase("name")) {
                final String name = value.replace("_", " ");
                im.setDisplayName(ChatHelper.fixColor(name));
            } else if (key.equalsIgnoreCase("lore")) {
                final List<String> lore = new LinkedList<>();
                final String[] splitLore = value.split("@nl");
                for (String s : splitLore) {
                    lore.add(ChatHelper.fixColor(s.replace("_", " ")));
                }
                im.setLore(lore);
            } else if (key.equalsIgnoreCase("data") || key.equalsIgnoreCase("durability")) {
                if (!NumberUtils.isNumber(value)) continue;
                final short data = (short) Integer.parseInt(value);
                is.setDurability(data);
            } else if (key.equalsIgnoreCase("enchants")) {
                final String[] enchantmentArray = value.split("@nl");
                for (String s : enchantmentArray) {
                    final String[] enchantmentSplit = s.split(";");
                    if (enchantmentSplit.length < 1) continue;
                    final Enchantment ench;
                    try {
                        ench = Enchantment.getByName(enchantmentSplit[0]);
                    } catch (Exception ex) {
                        LastCore.getInstance().getLogger().warning("Enchant: " + enchantmentSplit[0] + " nie istnieje!");
                        continue;
                    }
                    if (!NumberUtils.isNumber(enchantmentSplit[1])) continue;
                    final int level = Integer.parseInt(enchantmentSplit[1]);
                    if (ench != null)
                        im.addEnchant(ench, level, true);
                }
            }
        }
        is.setItemMeta(im);
        return is;
    }
}