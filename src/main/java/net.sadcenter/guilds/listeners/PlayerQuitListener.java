package net.sadcenter.guilds.listeners;

import lombok.RequiredArgsConstructor;
import net.sadcenter.guilds.basic.User;
import net.sadcenter.guilds.basic.fields.SessionField;
import net.sadcenter.guilds.managers.AntyLogoutManager;
import net.sadcenter.guilds.utils.UserUtil;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * @author sadcenter on 19.08.2020
 * @project LASTCORE
 */
@RequiredArgsConstructor
public class PlayerQuitListener implements Listener {

    private final AntyLogoutManager manager;

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        event.setQuitMessage(null);
        OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(event.getPlayer().getUniqueId());
        if (manager.getMap().asMap().containsKey(offlinePlayer.getUniqueId())) {
            offlinePlayer.getPlayer().setHealth(0.0);
            return;
        }

        User u = UserUtil.get(event.getPlayer().getUniqueId());
        if (u.getFirstConnection().getQuit() == 0L) {
            u.getFirstConnection().setQuit(System.currentTimeMillis());
        }
        u.getLastConnection().setQuit(System.currentTimeMillis());
        SessionField connection = u.getLongestConnection();
        SessionField lastConnection = u.getLastConnection();
        if ((connection.getQuit() - connection.getDate()) < (lastConnection.getQuit() - lastConnection.getDate())) {
            connection.setDate(lastConnection.getDate());
            connection.setQuit(System.currentTimeMillis());
        }
    }
}
