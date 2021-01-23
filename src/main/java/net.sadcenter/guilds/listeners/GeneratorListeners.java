package net.sadcenter.guilds.listeners;

import lombok.RequiredArgsConstructor;
import net.sadcenter.commons.helper.ChatHelper;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.plugin.Plugin;

/**
 * @author sadcenter on 26.09.2020
 * @project LASTCORE
 */
@RequiredArgsConstructor
public class GeneratorListeners implements Listener {

    private final Plugin plugin;

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        Location loc = event.getBlock().getLocation();
        Block generator = event.getPlayer().getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY() - 1, loc.getBlockZ());
        if (generator.getType() == Material.ENDER_STONE) {
            plugin.getServer().getScheduler().runTaskLater(plugin, () -> event.getBlock().setType(Material.STONE), 40L);
        }
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        Location loc = event.getBlock().getLocation();
        Block generator = event.getPlayer().getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY() - 1, loc.getBlockZ());
        if (generator.getType() == Material.ENDER_STONE) {
            event.setCancelled(true);
            ChatHelper.sendMessage(event.getPlayer(), "&cNie mozesz polozyc tutaj bloku!");
        }
    }

    @EventHandler
    public void onPlacee(BlockPlaceEvent event) {
        Location loc = event.getBlock().getLocation();
        Block generator = event.getPlayer().getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY() + 1, loc.getBlockZ());
        if (event.getBlock().getType() == Material.ENDER_STONE) {
            generator.setType(Material.STONE);
        }
    }


}
