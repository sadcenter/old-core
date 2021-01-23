package net.sadcenter.guilds.utils;

/**
 * @author sadcenter on 09.11.2020
 * @project LastCore-remake
 */

public final class MathUtils {

    public static double math(int var1, int var2) {
        return var1 * (((double) var2 / 100) + 1);
    }

    public static double math(double var1, int var2) {
        return var1 * (((double) var2 / 100) + 1);
    }
}
