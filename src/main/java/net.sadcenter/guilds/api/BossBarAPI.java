package net.sadcenter.guilds.api;

/**
 * @author sadcenter on 10.09.2020
 * @project LASTCORE
 */

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.reflect.StructureModifier;
import com.comphenix.protocol.wrappers.WrappedDataWatcher;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class BossBarAPI {

    private static final Map<UUID, BossBar> bossBars = new ConcurrentHashMap<>();
    private static int INDEX;

    public static void sendBossBar(Player player, String text) {
      /*  INDEX++;
        BossBar bossBar = new BossBar(player, text, convertToHealth(1));
        spawn(bossBar, bossBar.location);
        bossBars.put(player.getUniqueId(), bossBar);

       */

    }

    public static void hideBossBar(Player player) {
     /*  BossBar bossBar = bossBars.get(player.getUniqueId());
        if (bossBar == null) return;

        despawn(bossBar);
        bossBars.remove(player.getUniqueId());


      */

    }


    private static int convertToHealth(float percent) {
        return (int) Math.floor(percent * 300.0F);
    }

    private static Location getDistantLocation(@NotNull Player player) {
        return player.getEyeLocation().add(player.getEyeLocation().getDirection().normalize().multiply(23));
    }

    private static void spawn(@NotNull BossBar bar, Location location) {
        PacketContainer spawnPacket = new PacketContainer(PacketType.Play.Server.SPAWN_ENTITY_LIVING);
        StructureModifier<Object> spawnPacketModifier = spawnPacket.getModifier();
        spawnPacketModifier.write(0, INDEX);
        spawnPacketModifier.write(1, 64);
        spawnPacketModifier.write(2, (location.getBlockX() - 30) * 32);
        spawnPacketModifier.write(3, (location.getBlockY() - 30) * 32);
        spawnPacketModifier.write(4, (location.getBlockZ() - 30) * 32);
        spawnPacket.getDataWatcherModifier().write(0, bar.getDataWatcher());

        try {
            ProtocolLibrary.getProtocolManager().sendServerPacket(bar.getPlayer(), spawnPacket, false);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private static void despawn(@NotNull BossBar bar) {
        PacketContainer spawnPacket = new PacketContainer(PacketType.Play.Server.ENTITY_DESTROY);
        spawnPacket.getIntegerArrays().write(0, new int[]{INDEX});

        try {
            ProtocolLibrary.getProtocolManager().sendServerPacket(bar.getPlayer(), spawnPacket, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @AllArgsConstructor
    @Getter
    @Setter
    private static class BossBar {

        private final Player player;
        private final String text;
        private final int health;
        private Location location;


        public BossBar(Player player, String text, int health) {
            this.player = player;
            this.text = text;
            this.health = health;
            this.location = player.getLocation();
        }


        @NotNull
        private WrappedDataWatcher getDataWatcher() {
            WrappedDataWatcher watcher = new WrappedDataWatcher();
            watcher.setObject(0, (byte) 32);
            watcher.setObject(2, this.text);
            watcher.setObject(6, (float) this.health, true); // Set health
            watcher.setObject(10, this.text);
            watcher.setObject(20, 881);
            return watcher;
        }
    }
}
