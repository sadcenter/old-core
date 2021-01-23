package net.sadcenter.guilds.storage;


import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.bukkit.inventory.ItemStack;

import java.util.List;

/**
 * @author sadcenter on 18.10.2020
 * @project LASTCORE
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Settings {

    private static List<ItemStack> guildItems;
    private static boolean enabledGuilds;
    private static boolean enabledDiamondsItems;
    private static boolean enabledEnchanting;
    private static boolean enabledTnt;
    private static boolean enabledCase;
    private static boolean enabledGuildItems;
    private static boolean enabledPvp;
    private static boolean enabledGuildRegeneration;

    public static List<ItemStack> getGuildItems() {
        return guildItems;
    }

    public static void setGuildItems(List<ItemStack> guildItems) {
        Settings.guildItems = guildItems;
    }

    public static boolean isEnabledGuildItems() {
        return enabledGuildItems;
    }

    public static void setEnabledGuildItems(boolean enabledGuildItems) {
        Settings.enabledGuildItems = enabledGuildItems;
    }

    public static boolean isEnabledGuildRegeneration() {
        return enabledGuildRegeneration;
    }

    public static void setEnabledGuildRegeneration(boolean enabledGuildRegeneration) {
        Settings.enabledGuildRegeneration = enabledGuildRegeneration;
    }

    public static boolean isEnabledGuilds() {
        return enabledGuilds;
    }

    public static void setEnabledGuilds(boolean enabledGuilds) {
        Settings.enabledGuilds = enabledGuilds;
    }

    public static boolean isEnabledDiamondsItems() {
        return enabledDiamondsItems;
    }

    public static void setEnabledDiamondsItems(boolean enabledDiamondsItems) {
        Settings.enabledDiamondsItems = enabledDiamondsItems;
    }

    public static boolean isEnabledEnchanting() {
        return enabledEnchanting;
    }

    public static void setEnabledEnchanting(boolean enabledEnchanting) {
        Settings.enabledEnchanting = enabledEnchanting;
    }

    public static boolean isEnabledTnt() {
        return enabledTnt;
    }

    public static void setEnabledTnt(boolean enabledTnt) {
        Settings.enabledTnt = enabledTnt;
    }

    public static boolean isEnabledCase() {
        return enabledCase;
    }

    public static void setEnabledCase(boolean enabledCase) {
        Settings.enabledCase = enabledCase;
    }

    public static boolean isEnabledPvp() {
        return enabledPvp;
    }

    public static void setEnabledPvp(boolean enabledPvp) {
        Settings.enabledPvp = enabledPvp;
    }
}
