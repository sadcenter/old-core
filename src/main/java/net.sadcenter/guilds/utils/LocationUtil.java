package net.sadcenter.guilds.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Chest;
import org.bukkit.block.DoubleChest;
import org.bukkit.entity.Player;
import org.bukkit.inventory.DoubleChestInventory;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author sadcenter on 30.08.2020
 * @project LASTCORE
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class LocationUtil {

    public static Location getLocationFromChest(@NotNull Block block) {
        BlockState state = block.getState();
        if (state instanceof Chest) {
            Chest chest = (Chest) state;
            Inventory inventory = chest.getInventory();
            if (inventory instanceof DoubleChestInventory) {
                DoubleChest doubleChest = (DoubleChest) inventory.getHolder();
                return doubleChest.getLocation();
            }
            return chest.getLocation();
        }
        return null;
    }

    public static List<Player> getPlayersInRadius(Location location) {
        return Bukkit.getOnlinePlayers().stream().filter(player -> location.distanceSquared(player.getLocation()) < 3)
                .collect(Collectors.toList());
    }
}
