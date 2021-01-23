package net.sadcenter.guilds.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.InvocationTargetException;

/**
 * @author sadcenter on 26.09.2020
 * @project LASTCORE
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class PingUtil {

    @NotNull
    public static String getServerVersion() {
        return Bukkit.getServer().getClass().getPackage().getName().substring(23);
    }

    private static int ping(@NotNull Player p) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, NoSuchFieldException {
        Object entityPlayer = p.getClass().getMethod("getHandle").invoke(p);
        return (int) entityPlayer.getClass().getField("ping").get(entityPlayer);
    }

    public static int getPing(Player player) {
        try {
            return ping(player);
        } catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException | NoSuchFieldException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @NotNull
    public static String formatPing(Player player) {
        int ping = 0;
        try {
            ping = ping(player);
        } catch (InvocationTargetException | NoSuchMethodException | NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        if (ping < 70) {
            return "&a" + ping;
        }
        if (ping < 150 && ping > 70) {
            return "&3" + ping;
        }
        if (ping < 200 && ping > 150) {
            return "&4" + ping;
        }
        return "unknown";
    }
}
