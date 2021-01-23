package net.sadcenter.commons.helper;


import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.bukkit.Material;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import javax.xml.bind.DatatypeConverter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ItemSerializer {
    public static String itemsToString(final ItemStack[] items) {
        try {
            final ByteArrayOutputStream bos = new ByteArrayOutputStream();
            final ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(serializeItemStack(items));
            oos.flush();
            return DatatypeConverter.printBase64Binary(bos.toByteArray());
        } catch (Exception e) {
            return "";
        }
    }

    @NotNull
    public static ItemStack[] stringToItems(final String s) {
        try {
            final ByteArrayInputStream bis = new ByteArrayInputStream(DatatypeConverter.parseBase64Binary(s));
            final ObjectInputStream ois = new ObjectInputStream(bis);
            return deserializeItemStack((Map<String, Object>[]) ois.readObject());
        } catch (Exception e) {
            return new ItemStack[]{new ItemStack(Material.AIR)};
        }
    }

    @NotNull
    private static Map<String, Object>[] serializeItemStack(@NotNull final ItemStack[] items) {
        final Map<String, Object>[] result = (Map<String, Object>[]) new Map[items.length];
        for (int i = 0; i < items.length; ++i) {
            final ItemStack is = items[i];
            if (is == null) {
                result[i] = new HashMap<>();
            } else {
                result[i] = is.serialize();
                if (is.hasItemMeta()) {
                    result[i].put("meta", is.getItemMeta().serialize());
                }
            }
        }
        return result;
    }

    @NotNull
    private static ItemStack[] deserializeItemStack(final Map<String, Object>[] map) {
        final ItemStack[] items = new ItemStack[map.length];
        for (int i = 0; i < items.length; ++i) {
            final Map<String, Object> s = map[i];
            if (s.size() == 0) {
                items[i] = null;
            } else {
                try {
                    if (s.containsKey("meta")) {
                        final Map<String, Object> im = new HashMap<String, Object>((Map<? extends String, ?>) s.remove("meta"));
                        im.put("==", "ItemMeta");
                        final ItemStack is = ItemStack.deserialize(s);
                        is.setItemMeta((ItemMeta) ConfigurationSerialization.deserializeObject(im));
                        items[i] = is;
                    } else {
                        items[i] = ItemStack.deserialize(s);
                    }
                } catch (Exception e) {
                    items[i] = null;
                }
            }
        }
        return items;
    }
}
