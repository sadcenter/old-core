package net.sadcenter.guilds.utils;

import com.google.common.collect.Sets;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.sadcenter.commons.helper.ChatHelper;
import net.sadcenter.guilds.LastCore;
import net.sadcenter.guilds.basic.Guild;
import net.sadcenter.guilds.basic.fields.BlockField;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import java.util.Queue;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static net.sadcenter.guilds.utils.Worlds.DEAFULT_WORLD;

/**
 * @author sadcenter on 18.08.2020
 * @project LASTCORE
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class RegenerationUtil {


    public static void startRegeneration(@NotNull Guild guild, Queue<BlockField> queue) {

        final Set<Material> blacklist = Sets.newHashSet(Material.BEACON, Material.TNT);
        for (Player player : guild.getOnlinePlayers()) {
            ChatHelper.sendTitle(player, "", "&7Rozpoczeto regeneracje &3" + guild.getExplodedBlocks().size() + " &7blokow");
        }
        new BukkitRunnable() {
            private final World world = DEAFULT_WORLD;

            @Override
            public void run() {

                ChatHelper.sendAction(guild.getOnlinePlayers(), "&7Regeneracja gildii! Pozostalo: &3" + queue.size() + " &7z &3" + guild.getExplodedBlocks().size() + " &7~" + DateUtil.getDurationBreakdown(TimeUnit.SECONDS.toMillis(queue.size() / 20)));
                BlockField block = queue.poll();
                if (block != null && !blacklist.contains(block.getMat())) {
                    Block b = world.getBlockAt(block.getLocation());
                    b.setType(block.getMat());
                    b.setData(block.getData());
                }
                if (queue.isEmpty()) {
                    for (Player player : guild.getOnlinePlayers()) {
                        ChatHelper.sendTitle(player, "", "&7Zakonczono regeneracje &3" + guild.getExplodedBlocks().size() + " &7blokow");
                    }
                    guild.setRegenerating(false);
                    guild.getExplodedBlocks().clear();
                    cancel();
                }


            }
        }.runTaskTimer(LastCore.getInstance(), 1L, 1L);
    }
}
