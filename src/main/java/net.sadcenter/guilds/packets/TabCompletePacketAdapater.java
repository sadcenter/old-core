package net.sadcenter.guilds.packets;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

/**
 * @author sadcenter on 10.10.2020
 * @project LASTCORE
 */

public final class TabCompletePacketAdapater extends PacketAdapter {

    public TabCompletePacketAdapater(@NotNull Plugin plugin) {
        super(plugin, PacketType.Play.Client.TAB_COMPLETE);
    }

    @Override
    public void onPacketReceiving(PacketEvent event) {
        Player p = event.getPlayer();
        if (!p.hasPermission("scode.tabcomplete")) {
            event.setCancelled(true);
        }
    }
}
