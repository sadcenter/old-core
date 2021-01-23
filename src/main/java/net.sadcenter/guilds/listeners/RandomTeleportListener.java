package net.sadcenter.guilds.listeners;

import lombok.RequiredArgsConstructor;
import net.sadcenter.commons.helper.ChatHelper;
import net.sadcenter.commons.helper.RandomHelper;
import net.sadcenter.guilds.basic.User;
import net.sadcenter.guilds.runnables.DepositRunnable;
import net.sadcenter.guilds.utils.LocationUtil;
import net.sadcenter.guilds.utils.UserUtil;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.Arrays;
import java.util.List;

/**
 * @author sadcenter on 18.08.2020
 * @project LASTCORE
 */
@RequiredArgsConstructor
public class RandomTeleportListener implements Listener {

    private final Plugin plugin;
    private final BukkitScheduler scheduler;

    @EventHandler
    public void randomTeleport(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (!event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            return;
        }
        Block block = event.getClickedBlock();
        final Block add = block.getLocation().add(1.0, 0.0, 0.0).getBlock();
        final Block add2 = block.getLocation().add(-1.0, 0.0, 0.0).getBlock();
        final Block add3 = block.getLocation().add(0.0, 0.0, 1.0).getBlock();
        final Block add4 = block.getLocation().add(0.0, 0.0, -1.0).getBlock();
        if (add.getType() == Material.SPONGE || add2.getType() == Material.SPONGE || add3.getType() == Material.SPONGE || add4.getType() == Material.SPONGE) {
            if (block.getLocation().distanceSquared(player.getLocation()) > 3) {
                event.setCancelled(true);
                ChatHelper.sendMessage(player, "&cMusisz byc blizej!");
                return;
            }
            int x = RandomHelper.nextInt(0, 2000);
            int z = RandomHelper.nextInt(0, 2000);
            Location location = new Location(Bukkit.getWorld("world"), x, Bukkit.getWorld("world").getHighestBlockYAt(x, z) + 15, z);
            if (block.getType() == Material.WOOD_BUTTON) {
                player.teleport(location);
            } else if (block.getType() == Material.STONE_BUTTON) {
                List<Player> list = LocationUtil.getPlayersInRadius(player.getLocation());
                if (list.isEmpty() || list.size() == 1) {
                    event.setCancelled(true);
                    ChatHelper.sendMessage(player, "&cNie mozesz tepnac sie sam!");
                    return;
                }
                for (Player p : Arrays.asList(list.get(0), list.get(1))) {
                    p.teleport(location);
                    p.getActivePotionEffects().forEach(potionEffect -> p.removePotionEffect(potionEffect.getType()));
                    User u = UserUtil.get(p.getUniqueId());
                    u.setGod(true);
                    DepositRunnable.checkDeposit(player);
                }
                scheduler.runTaskLater(plugin, () -> {

                    for (Player teleported : list) {
                        User u = UserUtil.get(teleported.getUniqueId());
                        u.setGod(false);
                    }
                }, 60L);
            }


        }
    }

}
