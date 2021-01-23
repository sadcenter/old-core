package net.sadcenter.guilds.managers;

import lombok.RequiredArgsConstructor;
import net.sadcenter.commons.helper.ChatHelper;
import net.sadcenter.guilds.basic.Protection;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author sadcenter on 19.08.2020
 * @project LASTCORE
 */

public final class ProtectionManager {

    private final Map<UUID, Protection> protectionMap = new ConcurrentHashMap<>();

    public void create(@NotNull Player player) {
        ChatHelper.sendTitle(player, "&aOchrona zostala aktywowana!", "");
        protectionMap.put(player.getUniqueId(), new Protection(player.getUniqueId(), System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(120)));
    }

    public @NotNull Map<UUID, Protection> getProtections() {
        return protectionMap;
    }
}
