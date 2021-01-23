package net.sadcenter.guilds.managers;

import net.sadcenter.commons.helper.ChatHelper;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * @author sadcenter on 19.08.2020
 * @project LASTCORE
 */

public final class VanishManager {

    private final Set<UUID> vanished = new HashSet<>();

    public void hidePlayer(@NotNull Player player) {
        ChatHelper.sendMessage(player, "&aWlaczyles vanisha!");
        ChatHelper.sendTitle(player, "", "&aWlaczyles vanisha!");
        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            onlinePlayer.hidePlayer(player);
            if (onlinePlayer.hasPermission("core.vanish")) {
                onlinePlayer.showPlayer(player);
            }
        }
    }

    public void showPlayer(Player player) {
        ChatHelper.sendMessage(player, "&cWylaczyles vanisha!");
        ChatHelper.sendTitle(player, "", "&cWylaczyles vanisha!");
        for (Player onlinePlayer : Bukkit.getOnlinePlayers())
            onlinePlayer.showPlayer(player);
    }

    public void changeStatus(@NotNull Player player) {
        player.getWorld().strikeLightningEffect(player.getLocation());
        if (!vanished.removeIf(uuid -> player.getUniqueId().equals(uuid))) {
            vanished.add(player.getUniqueId());
            hidePlayer(player);
        } else
            showPlayer(player);
    }

    public boolean contains(@NotNull Player player) {
        return vanished.contains(player.getUniqueId());
    }


}
