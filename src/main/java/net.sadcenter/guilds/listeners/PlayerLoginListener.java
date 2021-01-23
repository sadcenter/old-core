package net.sadcenter.guilds.listeners;

import lombok.AllArgsConstructor;
import net.sadcenter.commons.helper.ChatHelper;
import net.sadcenter.guilds.basic.Blacklist;
import net.sadcenter.guilds.managers.BlacklistManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

/**
 * @author sadcenter on 14.10.2020
 * @project LASTCORE
 */

@AllArgsConstructor
public class PlayerLoginListener implements Listener {

    private final BlacklistManager blacklistManager;

    @EventHandler
    public void onLogin(PlayerLoginEvent event) {
        @NotNull Optional<Blacklist> blacklistFromUUID = blacklistManager.getBlacklist(event.getPlayer().getUniqueId());
        if (blacklistFromUUID.isPresent()) {
            event.disallow(PlayerLoginEvent.Result.KICK_BANNED, ChatHelper.fixColor(blacklistFromUUID.get().getReason()));
            return;
        }
        @NotNull Optional<Blacklist> blacklistFromIP = blacklistManager.getBlacklist(event.getAddress());
        blacklistFromIP.ifPresent(blacklist -> event.disallow(PlayerLoginEvent.Result.KICK_BANNED, ChatHelper.fixColor(blacklist.getReason())));
    }
}
