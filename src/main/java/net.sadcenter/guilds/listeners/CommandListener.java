package net.sadcenter.guilds.listeners;

import lombok.RequiredArgsConstructor;
import net.sadcenter.commons.helper.ChatHelper;
import net.sadcenter.guilds.config.impl.storage.AntyLogoutStorage;
import net.sadcenter.guilds.config.impl.storage.MainConfigurationStorage;
import net.sadcenter.guilds.managers.AntyLogoutManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

/**
 * @author sadcenter on 14.09.2020
 * @project LASTCORE
 */

@RequiredArgsConstructor
public class CommandListener implements Listener {

    private final MainConfigurationStorage configurationStorage;
    private final AntyLogoutManager manager;
    private final AntyLogoutStorage storage;

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent event) {
        if (event.isCancelled())
            return;

        String command = event.getMessage().toLowerCase().replace("/", "").split(" ")[0];
        Player player = event.getPlayer();
        if (!player.hasPermission("scode.antylogout") && (manager.getMap().asMap().containsKey(player.getUniqueId()) && !storage.getAllowCommands().contains(command))) {
            event.setCancelled(true);
            player.sendMessage(ChatHelper.fixColor(storage.getAntyLogoutMessage().replace("{COMMAND}", command)));
        }

        if (command.contains(":")) {
            ChatHelper.sendMessage(event.getPlayer(), "&3Paczka plikow zostala stworzona przez &3sadcenter &8(&3http://sadcenter.xyz&8)&3 na zamowienie dla &3&l&3&lLAST&F&LCORE&7.PL");
            event.setCancelled(true);
        }
        configurationStorage.getCommand(command).ifPresent(customCommand -> {
            event.setCancelled(true);
            for (String message : customCommand.getMessages()) {
                ChatHelper.sendMessage(event.getPlayer(), message);
            }
        });
    }
}
