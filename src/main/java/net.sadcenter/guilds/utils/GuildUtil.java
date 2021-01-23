package net.sadcenter.guilds.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.sadcenter.guilds.LastCore;
import net.sadcenter.guilds.basic.Guild;
import net.sadcenter.guilds.basic.Member;
import net.sadcenter.guilds.managers.GuildManager;
import org.apache.commons.lang3.Validate;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author sadcenter on 30.08.2020
 * @project LASTCORE
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class GuildUtil {

    private static final GuildManager manager = LastCore.getInstance().getGuildManager();

    public static Guild get(String tag) {
        return manager
                .getGuilds()
                .get(tag);
    }

    public static Guild getEquals(String tag) {
        return manager
                .getGuilds()
                .values()
                .stream()
                .filter(guild -> guild.getTag().equalsIgnoreCase(tag))
                .findFirst()
                .orElse(null);
    }


    public static Guild get(@NotNull Location location) {
        return manager
                .getGuilds()
                .values()
                .stream()
                .filter(guild -> guild.getRegion().isInCuboid(location))
                .findFirst()
                .orElse(null);
    }

    public static @Nullable
    Guild getFromPlayer(String name) {
        Validate.notBlank(name, "Name cant be blank");
        Member o = MemberUtil.get(name);
        return o == null ? null : o.getGuild();
    }

    public static @Nullable
    Guild getFromPlayer(@NotNull UUID uuid) {
        Member o = MemberUtil.get(uuid);
        return o == null ? null : o.getGuild();
    }


    public static boolean canCreateSpawn(@NotNull Location loc, int distance) {
        Location spawn = Bukkit.getWorld("world").getSpawnLocation();
        return !(Math.abs(loc.getBlockX() - spawn.getBlockX()) < distance && Math.abs(loc.getBlockZ() - spawn.getBlockZ()) < distance);
    }

    public static boolean canCreateGuild(Location loc, int distance, int y) {
        return getFromDistances(loc, distance, y).isEmpty();
    }

    public static void deleteRoom(final Guild g) {
        final Location c = g.getRegion().getLocation().clone();
        c.setY(30.0);
        c.getBlock().setType(Material.AIR);
        c.setY(29.0);
        c.getBlock().setType(Material.AIR);
    }

    public static void createRoomGuild(Guild g) {
        Location c = g.getRegion().getLocation().clone();
        c.setY(25.0);
        for (final Location loc : SpaceUtil.getSquare(c, 4, 5)) {
            loc.getBlock().setType(Material.AIR);
        }
        for (final Location loc : SpaceUtil.getSquare(c, 3, 0)) {
            loc.getBlock().setType(Material.NETHERRACK);
        }
        c.setY(26.0);
        for (final Location loc : SpaceUtil.getWalls(c, 2, 3)) {
            loc.getBlock().setType(Material.STAINED_GLASS);
            loc.getBlock().setData(g.getGlass().getData());
        }

        c.setY(27.0);
        c.getBlock().setType(Material.PUMPKIN);
        c.setY(30.0);
        for (final Location loc : SpaceUtil.getSquare(c, 3, 0)) {
            loc.getBlock().setType(Material.NETHERRACK);
        }
    }

    private static @NotNull List<Guild> getFromDistances(Location loc, int distance, int y) {
        List<Guild> players = new ArrayList<>();
        for (Guild g : manager.getGuilds().values()) {
            Location location = new Location(Bukkit.getWorld("world"), g.getRegion().getX(), y, g.getRegion().getZ());
            if (location.distanceSquared(loc) <= distance * distance) {
                players.add(g);
            }
        }
        return players;
    }

}
