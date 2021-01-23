package net.sadcenter.guilds.listeners;

import lombok.RequiredArgsConstructor;
import net.sadcenter.commons.helper.ChatHelper;
import net.sadcenter.guilds.basic.Guild;
import net.sadcenter.guilds.basic.Member;
import net.sadcenter.guilds.utils.GuildUtil;
import net.sadcenter.guilds.utils.MemberUtil;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFromToEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerBucketFillEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.HashSet;
import java.util.Set;

/**
 * @author sadcenter on 18.08.2020
 * @project LASTCORE
 */
@RequiredArgsConstructor
public class WaterListeners implements Listener {

    private final Set<Location> players = new HashSet<>();
    private final BukkitScheduler scheduler;
    private final Plugin plugin;

    @EventHandler
    public void onWater(PlayerBucketFillEvent event) {
        Guild g = GuildUtil.get(event.getBlockClicked().getLocation());
        Member o = MemberUtil.get(event.getPlayer().getName());
        boolean isMember = o != null && g.isMember(o.getName());
        if (g != null && (isMember && o.isWaterPerm())) {
            players.remove(event.getBlockClicked().getLocation().add(0, 1, 0));
        }
    }

    @EventHandler
    public void onWater(BlockFromToEvent blockFromToEvent) {
        int id = blockFromToEvent.getBlock().getTypeId();

        if ((id == 8 || id == 9) && !players.contains(blockFromToEvent.getBlock().getLocation())) {
            blockFromToEvent.setCancelled(true);
        }
    }

    @EventHandler
    public void onWater(PlayerBucketEmptyEvent event) {
        Player player = event.getPlayer();
        Guild g = GuildUtil.get(event.getBlockClicked().getLocation());
        if (g == null)
            return;

        Block block = event.getBlockClicked().getLocation().add(0, 1, 0).getBlock();
        final Member o = MemberUtil.get(event.getPlayer().getName());
        boolean isMember = o != null && g.isMember(o.getName());
        if (!isMember && block.getType() == Material.STATIONARY_LAVA) {
            event.setCancelled(true);
        }
        if (isMember && o.isWaterPerm()) {
            players.add(block.getLocation());
        }
        if (!isMember || !o.isWaterPerm()) {
            ChatHelper.sendMessage(event.getPlayer(), "&cNie mozesz tego zrobic");
            scheduler.runTaskLater(plugin, () -> {
                if (block.getType().equals(Material.AIR) || player.getGameMode().equals(GameMode.CREATIVE))
                    return;
                block.setType(Material.AIR);

            }, 60L);
        }
    }


}
