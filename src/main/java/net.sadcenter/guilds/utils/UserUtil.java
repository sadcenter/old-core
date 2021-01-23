package net.sadcenter.guilds.utils;

import net.sadcenter.guilds.LastCore;
import net.sadcenter.guilds.basic.User;
import net.sadcenter.guilds.managers.UserManager;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

/**
 * @author sadcenter on 30.08.2020
 * @project LASTCORE
 */

public final class UserUtil {

    private final static UserManager manager = LastCore.getInstance().getUserManager();

    public static User get(UUID id) {
        return manager.get(id);
    }

    public static User get(String name) {
        return manager.get(name);
    }

    public static User createIfAbsent(@NotNull Player p) {
        return manager.createIfAbsent(p);
    }

}
