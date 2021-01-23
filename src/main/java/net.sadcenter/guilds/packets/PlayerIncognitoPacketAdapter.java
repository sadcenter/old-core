package net.sadcenter.guilds.packets;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.wrappers.EnumWrappers;
import com.comphenix.protocol.wrappers.PlayerInfoData;
import com.comphenix.protocol.wrappers.WrappedChatComponent;
import net.sadcenter.guilds.LastCore;
import net.sadcenter.guilds.basic.User;
import net.sadcenter.guilds.managers.UserManager;
import net.sadcenter.guilds.utils.NameTagUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.ArrayList;
import java.util.List;

public final class PlayerIncognitoPacketAdapter extends PacketAdapter {

    private final Plugin plugin;
    private final BukkitScheduler scheduler;
    private final UserManager userManager;

    public PlayerIncognitoPacketAdapter(LastCore plugin) {
        super(plugin, PacketType.Play.Server.PLAYER_INFO);
        this.plugin = plugin;
        this.scheduler = plugin.getServer().getScheduler();
        this.userManager = plugin.getUserManager();
    }

    @Override
    public void onPacketSending(PacketEvent event) {
        PacketContainer packet = event.getPacket();
        if (packet.getPlayerInfoAction().read(0) != EnumWrappers.PlayerInfoAction.ADD_PLAYER) {
            return;
        }
        Player p = event.getPlayer();
        User u = userManager.get(p.getUniqueId());
        if (u == null)
            userManager.createIfAbsent(p);

        List<PlayerInfoData> newData = new ArrayList<>();
        for (PlayerInfoData playerInfoData : packet.getPlayerInfoDataLists().read(0)) {
            Player player = Bukkit.getPlayer(playerInfoData.getProfile().getUUID());
            if (player == null) {
                continue;
            }
            User incognitoPlayer = userManager.get(player.getUniqueId());
            if (incognitoPlayer == null) {
                continue;
            }
            if (incognitoPlayer.getIncognito() == null) {
                newData.add(playerInfoData);
            } else {
                newData.add(new PlayerInfoData(incognitoPlayer.getIncognito().getGameProfile(), playerInfoData.getLatency(), EnumWrappers.NativeGameMode.NOT_SET, WrappedChatComponent.fromText(incognitoPlayer.getIncognito().getIncognito())));
            }
            Bukkit.getOnlinePlayers().forEach(NameTagUtil::init);
        }
        packet.getPlayerInfoDataLists().write(0, newData);
    }
}
