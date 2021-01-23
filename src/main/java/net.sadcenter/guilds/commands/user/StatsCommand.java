package net.sadcenter.guilds.commands.user;

import net.sadcenter.commons.command.Command;
import net.sadcenter.commons.command.CommandBuilder;
import net.sadcenter.commons.helper.ChatHelper;
import net.sadcenter.guilds.utils.DateUtil;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.util.List;

/**
 * @author sadcenter on 28.08.2020
 * @project LASTCORE
 */

public class StatsCommand extends Command {

    public StatsCommand() {
        super("stats", "core.cmd.stats", false);
    }

    @Override
    public void onCommand(CommandSender executor, CommandBuilder commandBuilder, String... args) {
        Player player = (Player) executor;
        ChatHelper.sendMessage(player, "&8 &7Online serwer: &b" + DateUtil.getDurationBreakdown((System.currentTimeMillis() - ManagementFactory.getRuntimeMXBean().getStartTime())));
        ChatHelper.sendMessage(player, "&8 &7Max RAM: &b" + Runtime.getRuntime().maxMemory() / 1024L / 1024L + "MB");
        ChatHelper.sendMessage(player, "&8 &7Total RAM: &b" + Runtime.getRuntime().totalMemory() / 1024L / 1024L + "MB");
        ChatHelper.sendMessage(player, "&8 &7Free RAM: &b" + Runtime.getRuntime().freeMemory() / 1024L / 1024L + "MB");
        ChatHelper.sendMessage(player, "");
        ChatHelper.sendMessage(player, "&8 &7Worlds:");
        final List<World> worlds = Bukkit.getWorlds();
        for (final World w : worlds) {
            int tileEntities = 0;
            Chunk[] loadedChunks;
            for (int length = (loadedChunks = w.getLoadedChunks()).length, i = 0; i < length; ++i) {
                final Chunk chunk = loadedChunks[i];
                tileEntities += chunk.getTileEntities().length;
            }
            ChatHelper.sendMessage(player, "&8  &7" + w.getName() + ": &b" + w.getLoadedChunks().length + " chunks, " + w.getEntities().size() + " entities, " + tileEntities + " tilies");
        }
        ChatHelper.sendMessage(player, "");
        ChatHelper.sendMessage(player, "&8 &7Watki:");
        final ThreadMXBean tmxb = ManagementFactory.getThreadMXBean();
        long full = 0L;
        for (final Thread t : Thread.getAllStackTraces().keySet()) {
            full += tmxb.getThreadCpuTime(t.getId());
        }
        for (final Thread t : Thread.getAllStackTraces().keySet()) {
            if (tmxb.getThreadCpuTime(t.getId()) > 0L) {
                final long l = tmxb.getThreadCpuTime(t.getId()) * 100L / full;
                if (l <= 0.0) {
                    continue;
                }
                ChatHelper.sendMessage(player, "&8  &7" + t.getName() + ": &b" + l + "%");
            }
        }
    }
}
