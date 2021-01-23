package net.sadcenter.guilds.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sadcenter on 01.11.2020
 * @project LastCore
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class SpaceUtil {

    //tak, to z kguild

    public static List<Location> getSquare(final Location center, final int radius) {
        final List<Location> locs = new ArrayList<>();
        final int cX = center.getBlockX();
        final int cZ = center.getBlockZ();
        final int minX = Math.min(cX + radius, cX - radius);
        final int maxX = Math.max(cX + radius, cX - radius);
        final int minZ = Math.min(cZ + radius, cZ - radius);
        final int maxZ = Math.max(cZ + radius, cZ - radius);
        for (int x = minX; x <= maxX; ++x) {
            for (int z = minZ; z <= maxZ; ++z) {
                locs.add(new Location(center.getWorld(), x, center.getBlockY(), z));
            }
        }
        locs.add(center);
        return locs;
    }

    public static List<Location> getCorners(final Location center, final int radius) {
        final List<Location> locs = new ArrayList<>();
        final int cX = center.getBlockX();
        final int cZ = center.getBlockZ();
        final int minX = Math.min(cX + radius, cX - radius);
        final int maxX = Math.max(cX + radius, cX - radius);
        final int minZ = Math.min(cZ + radius, cZ - radius);
        final int maxZ = Math.max(cZ + radius, cZ - radius);
        locs.add(new Location(center.getWorld(), minX, center.getBlockY(), minZ));
        locs.add(new Location(center.getWorld(), maxX, center.getBlockY(), minZ));
        locs.add(new Location(center.getWorld(), minX, center.getBlockY(), maxZ));
        locs.add(new Location(center.getWorld(), maxX, center.getBlockY(), maxZ));
        return locs;
    }

    public static List<Location> getWalls(final Location center, final int radius) {
        final List<Location> locs = getSquare(center, radius);
        locs.removeAll(getSquare(center, radius - 1));
        return locs;
    }

    public static List<Location> getSquare(final Location center, final int radius, final int height) {
        final List<Location> locs = getSquare(center, radius);
        for (int i = 1; i <= height; ++i) {
            locs.addAll(getSquare(new Location(center.getWorld(), center.getBlockX(), center.getBlockY() + i, center.getBlockZ()), radius));
        }
        return locs;
    }

    public static List<Location> getCorners(final Location center, final int radius, final int height) {
        final List<Location> locs = getCorners(center, radius);
        for (int i = 1; i <= height; ++i) {
            locs.addAll(getCorners(new Location(center.getWorld(), center.getBlockX(), center.getBlockY() + i, center.getBlockZ()), radius));
        }
        return locs;
    }

    public static List<Location> getWallsOnGround(final Location center, final int radius) {
        final List<Location> locs = new ArrayList<>();
        for (final Location l : getWalls(center, radius)) {
            locs.add(l.getWorld().getHighestBlockAt(l).getLocation().add(0.0, 1.0, 0.0));
        }
        return locs;
    }

    public static List<Location> getWallsOnGround(final Location center, final int radius, final int height) {
        final List<Location> locs = new ArrayList<>();
        for (final Location l : getWalls(center, radius)) {
            for (int i = 0; i < height; ++i) {
                locs.add(l.getWorld().getHighestBlockAt(l).getLocation().add(0.0, 1 + i, 0.0));
            }
        }
        return locs;
    }

    public static List<Location> getWalls(final Location center, final int radius, final int height) {
        final List<Location> locs = getWalls(center, radius);
        for (int i = 1; i <= height; ++i) {
            locs.addAll(getWalls(new Location(center.getWorld(), center.getBlockX(), center.getBlockY() + i, center.getBlockZ()), radius));
        }
        return locs;
    }

    public static List<Location> sphere(final Location loc, final int radius, final int height, final boolean hollow, final boolean sphere, final int plusY) {
        final List<Location> circleblocks = new ArrayList<>();
        final int cx = loc.getBlockX();
        final int cy = loc.getBlockY();
        final int cz = loc.getBlockZ();
        for (int x = cx - radius; x <= cx + radius; ++x) {
            for (int z = cz - radius; z <= cz + radius; ++z) {
                for (int y = sphere ? (cy - radius) : cy; y < (sphere ? (cy + radius) : (cy + height)); ++y) {
                    final double dist = (cx - x) * (cx - x) + (cz - z) * (cz - z) + (sphere ? ((cy - y) * (cy - y)) : 0);
                    if (dist < radius * radius && (!hollow || dist >= (radius - 1) * (radius - 1))) {
                        final Location l = new Location(loc.getWorld(), x, y + plusY, z);
                        circleblocks.add(l);
                    }
                }
            }
        }
        return circleblocks;
    }

}
