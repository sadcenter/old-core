package net.sadcenter.guilds.packets;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;


/**
 * @author sadcenter on 14.10.2020
 * @project LASTCORE
 */

public final class FakeEnchantPacketAdapter extends PacketAdapter {
    public FakeEnchantPacketAdapter(@NotNull Plugin plugin) {
        super(plugin, PacketType.Play.Server.ENTITY_METADATA);
    }

    @Override
    public void onPacketSending(PacketEvent event) {

    }
}
