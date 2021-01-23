package net.sadcenter.guilds.basic;


import lombok.Getter;
import lombok.Setter;
import net.sadcenter.guilds.utils.Worlds;
import org.bukkit.Location;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

@Getter
@Setter
public final class Region {

    private int x;
    private int z;
    private int size;

    public Region(final int x, final int z, final int size) {
        this.x = x;
        this.z = z;
        this.size = size;
    }

    public boolean isInCuboid(final @NotNull Location loc) {
        if (!loc.getWorld().equals(Worlds.DEAFULT_WORLD)) {
            return false;
        }
        final int distancex = Math.abs(loc.getBlockX() - this.x);
        final int distancez = Math.abs(loc.getBlockZ() - this.z);
        return distancex <= this.getSize() && distancez <= this.getSize();
    }

    public boolean isInCuboidByLoc(final @NotNull Location loc) {
        if (!loc.getWorld().equals(Worlds.DEAFULT_WORLD)) {
            return false;
        }
        final int distancex = Math.abs(loc.getBlockX() - this.getX());
        final int distancez = Math.abs(loc.getBlockZ() - this.getZ());
        return distancex - 1 <= this.getSize() && distancez - 1 <= this.getSize();
    }


    public boolean isInCentrum(final @NotNull Location loc, final int top, final int down, final int wall) {
        final Location c = this.getLocation().clone();
        return c.getBlockY() - down <= loc.getBlockY() && c.getBlockY() + top >= loc.getBlockY() && loc.getBlockX() <= c.getBlockX() + wall && loc.getBlockX() >= c.getBlockX() - wall && loc.getBlockZ() <= c.getBlockZ() + wall && loc.getBlockZ() >= c.getBlockZ() - wall;
    }

    @Contract(" -> new")
    public @NotNull Location getLocation() {
        return new Location(Worlds.DEAFULT_WORLD, this.getX(), 25.0, this.getZ());
    }

    public void addSize(final int size) {
        this.size += size;
    }
}
