package net.sadcenter.guilds.teleport;


import net.sadcenter.commons.helper.ChatHelper;
import net.sadcenter.guilds.LastCore;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * @author sadcenter on 15.08.2020
 * @project LASTCORE
 */
public final class TeleportAPI implements Listener {

    private final Set<UUID> set = new HashSet<>();
    private final Plugin plugin;

    public TeleportAPI(@NotNull PluginManager pluginManager, Plugin plugin) {
        pluginManager.registerEvents(this, plugin);
        this.plugin = plugin;
    }

    public void add(@NotNull Player player, Location location) {
        int s = 10;
        if (player.hasPermission("core.admin"))
            s = 0;
        else if (player.hasPermission("core.teleport"))
            s = 7;

        executeTeleport(player, s, location);
    }

    @EventHandler
    public void onMove(@NotNull PlayerMoveEvent event) {
        Player player = event.getPlayer();
        Location from = event.getFrom();
        Location to = event.getTo();
        if (from.getBlockX() == to.getBlockX() && from.getBlockY() == to.getBlockY() && from.getBlockZ() == to.getBlockZ())
            return;

        if (!set.contains(player.getUniqueId())) {
            return;
        }
        set.remove(player.getUniqueId());
    }

    @EventHandler
    public void onOpen(@NotNull InventoryOpenEvent event) {
        Player player = (Player) event.getPlayer();

        if (!set.contains(player.getUniqueId()))
            return;

        player.closeInventory();
    }

    private void executeTeleport(final Player player, final int seconds, final Location location) {
        if (seconds == 0) {
            player.teleport(location);
            return;
        }
        ChatHelper.sendTitle(player, "", "&7Zostaniesz przeteleportowany za &3" + seconds);
        set.add(player.getUniqueId());
        new BukkitRunnable() {
            private int i = seconds;

            @Override
            public void run() {
                if (!set.contains(player.getUniqueId())) {
                    ChatHelper.sendTitle(player, "", "&7Anulowano teleportacje!");
                    cancel();
                    return;
                }
                i--;
                if (i <= 0) {
                    player.teleport(location);
                    set.remove(player.getUniqueId());
                    ChatHelper.sendTitle(player, "", "&7Przeteleportowano");
                    cancel();
                    return;
                }
                ChatHelper.sendTitle(player, "", "&7Zostaniesz przeteleportowany za &3" + i);
            }
        }.runTaskTimer(plugin, 20L, 20L);
    }

}
