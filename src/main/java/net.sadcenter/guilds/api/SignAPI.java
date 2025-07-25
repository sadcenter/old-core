package net.sadcenter.guilds.api;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.wrappers.BlockPosition;
import com.comphenix.protocol.wrappers.WrappedBlockData;
import com.comphenix.protocol.wrappers.WrappedChatComponent;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SignAPI {
    protected final Map<String, SignGUIListener> listeners;
    private final ProtocolManager protocolManager;
    private final PacketAdapter packetListener;
    private final Map<String, Vector> signLocations;

    public SignAPI(Plugin plugin) {
        protocolManager = ProtocolLibrary.getProtocolManager();
        listeners = new ConcurrentHashMap<>();
        signLocations = new ConcurrentHashMap<>();

        ProtocolLibrary.getProtocolManager().addPacketListener(
                packetListener = new PacketAdapter(plugin, PacketType.Play.Client.UPDATE_SIGN) {
                    @Override
                    public void onPacketReceiving(PacketEvent event) {
                        final Player player = event.getPlayer();
                        Vector v = signLocations.remove(player.getName());
                        BlockPosition bp = event.getPacket().getBlockPositionModifier().getValues().get(0);
                        final WrappedChatComponent[] chatarray = event.getPacket().getChatComponentArrays().getValues().get(0);
                        final String[] lines = {chatarray[0].getJson().replace("\"", ""), chatarray[1].getJson().replace("\"", ""), chatarray[2].getJson().replace("\"", ""), chatarray[3].getJson().replace("\"", "")};
                        final SignGUIListener response = listeners.remove(event.getPlayer().getName());

                        if (v == null) return;
                        if (bp.getX() != v.getBlockX()) return;
                        if (bp.getY() != v.getBlockY()) return;
                        if (bp.getZ() != v.getBlockZ()) return;
                        if (response != null) {
                            event.setCancelled(true);
                            plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, () -> response.onSignDone(player, lines));
                        }
                    }
                }
        );
    }


    public void open(@NotNull Player player, String[] defaultText, SignGUIListener response) {
        List<PacketContainer> packets = new ArrayList<>();
        int x = player.getLocation().getBlockX();
        int y = 0;
        int z = player.getLocation().getBlockZ();
        BlockPosition bpos = new BlockPosition(x, y, z);
        PacketContainer packet133 = protocolManager.createPacket(PacketType.Play.Server.OPEN_SIGN_ENTITY);

        if (defaultText != null) {
            PacketContainer packet53 = protocolManager.createPacket(PacketType.Play.Server.BLOCK_CHANGE);
            PacketContainer packet130 = protocolManager.createPacket(PacketType.Play.Server.UPDATE_SIGN);
            WrappedBlockData iblock = WrappedBlockData.createData(Material.SIGN_POST);
            WrappedChatComponent[] cc = {WrappedChatComponent.fromText(defaultText[0]), WrappedChatComponent.fromText(defaultText[1]), WrappedChatComponent.fromText(defaultText[2]), WrappedChatComponent.fromText(defaultText[3])};

            packet53.getBlockPositionModifier().write(0, bpos);
            packet53.getBlockData().write(0, iblock);
            packet130.getBlockPositionModifier().write(0, bpos);
            packet130.getChatComponentArrays().write(0, cc);
            packets.add(packet53);
            packets.add(packet130);
        }


        packet133.getBlockPositionModifier().write(0, bpos);
        packets.add(packet133);
        if (defaultText != null) {
            PacketContainer packet53 = protocolManager.createPacket(PacketType.Play.Server.BLOCK_CHANGE);
            WrappedBlockData iblock = WrappedBlockData.createData(Material.BEDROCK);

            packet53.getBlockPositionModifier().write(0, bpos);
            packet53.getBlockData().write(0, iblock);
            packet53.getBlockData().write(0, iblock);
            packets.add(packet53);
        }

        try {
            for (PacketContainer packet : packets) {
                protocolManager.sendServerPacket(player, packet);
            }

            signLocations.put(player.getName(), new Vector(x, y, z));
            listeners.put(player.getName(), response);
        } catch (
                InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    public void destroy() {
        protocolManager.removePacketListener(packetListener);
        listeners.clear();
        signLocations.clear();
    }

    public interface SignGUIListener {
        void onSignDone(Player player, String[] lines);
    }
}