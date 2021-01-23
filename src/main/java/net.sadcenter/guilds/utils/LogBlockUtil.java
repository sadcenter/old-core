package net.sadcenter.guilds.utils;

/**
 * @author sadcenter on 08.08.2020
 * @project LASTCORE
 */

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockState;
import org.bukkit.block.DoubleChest;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class LogBlockUtil {
    private static final BlockFace[] relativeBlockFaces = new BlockFace[]{
            BlockFace.EAST, BlockFace.WEST, BlockFace.NORTH, BlockFace.SOUTH, BlockFace.UP, BlockFace.DOWN
    };

    /**
     * Returns a list of block locations around the block that are of the type specified by the integer list parameter
     *
     * @param block The central block to get the blocks around
     * @param type  The type of blocks around the center block to return
     * @return List of block locations around the block that are of the type specified by the integer list parameter
     */
    @NotNull
    public static List<Location> getBlocksNearby(org.bukkit.block.Block block, Set<Material> type) {
        ArrayList<Location> blocks = new ArrayList<>();
        for (BlockFace blockFace : relativeBlockFaces) {
            if (type.contains(block.getRelative(blockFace).getType())) {
                blocks.add(block.getRelative(blockFace).getLocation());
            }
        }
        return blocks;
    }


    public static Material getInventoryHolderType(InventoryHolder holder) {
        if (holder instanceof DoubleChest) {
            return getInventoryHolderType(((DoubleChest) holder).getLeftSide());
        } else if (holder instanceof BlockState) {
            return ((BlockState) holder).getType();
        } else {
            return null;
        }
    }

    public static Location getInventoryHolderLocation(InventoryHolder holder) {
        if (holder instanceof DoubleChest) {
            return getInventoryHolderLocation(((DoubleChest) holder).getLeftSide());
        } else if (holder instanceof BlockState) {
            return ((BlockState) holder).getLocation();
        } else {
            return null;
        }
    }

    @NotNull
    public static ItemStack[] compareInventories(ItemStack[] items1, ItemStack[] items2) {
        final ArrayList<ItemStack> diff = new ArrayList<>();
        for (ItemStack current : items2) {
            diff.add(new ItemStack(current));
        }
        for (ItemStack previous : items1) {
            boolean found = false;
            for (ItemStack current : diff) {
                if (current.isSimilar(previous)) {
                    int newAmount = current.getAmount() - previous.getAmount();
                    if (newAmount == 0) {
                        diff.remove(current);
                    } else {
                        current.setAmount(newAmount);
                    }
                    found = true;
                    break;
                }
            }
            if (!found) {
                ItemStack subtracted = new ItemStack(previous);
                subtracted.setAmount(-subtracted.getAmount());
                diff.add(subtracted);
            }
        }
        return diff.toArray(new ItemStack[0]);
    }

    public static ItemStack[] compressInventory(ItemStack[] items) {
        final ArrayList<ItemStack> compressed = new ArrayList<>();
        for (final ItemStack item : items) {
            if (item != null) {
                boolean found = false;
                for (final ItemStack item2 : compressed) {
                    if (item2.isSimilar(item)) {
                        item2.setAmount(item2.getAmount() + item.getAmount());
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    compressed.add(item.clone());
                }
            }
        }
        return compressed.toArray(new ItemStack[0]);
    }

}