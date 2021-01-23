package net.sadcenter.guilds.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.luckperms.api.model.group.Group;
import net.luckperms.api.model.user.User;
import net.sadcenter.guilds.api.LuckPermsAPI;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author sadcenter on 20.08.2020
 * @project LASTCORE
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class PermissionUtil {

    public static @Nullable User getUser(@NotNull Player player) {
        // assert that the player is online
        if (!player.isOnline()) {
            return null;
        }

        return LuckPermsAPI.getLuckPerms().getUserManager().getUser(player.getUniqueId());
    }

    public static @Nullable Group getGroup(@NotNull String group) {

        return LuckPermsAPI.getLuckPerms().getGroupManager().getGroup(group);
    }
}
