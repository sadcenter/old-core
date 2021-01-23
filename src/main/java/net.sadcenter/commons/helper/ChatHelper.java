package net.sadcenter.commons.helper;


import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.sadcenter.guilds.LastCore;
import net.sadcenter.guilds.utils.ReflectionUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ChatHelper {

    private static final Map<UUID, List<String>> messages = new ConcurrentHashMap<>();
    private static final Plugin plugin = LastCore.getInstance();

    @NotNull
    public static String fixColor(@NotNull String text) {
        return ChatColor.translateAlternateColorCodes('&', text)
                .replace(">>", "»")
                .replace("<<", "«");
    }

    @NotNull
    public static String unfixColor(@NotNull String text) {
        return text
                .replace(String.valueOf(ChatColor.COLOR_CHAR), "");
    }

    @NotNull
    public static List<String> fixColor(@NotNull List<String> messages) {
        return messages.stream()
                .map(ChatHelper::fixColor)
                .collect(Collectors.toList());
    }


    public static void sendMessage(@NotNull Player player, String message) {
        player.sendMessage(fixColor(message));
    }

    public static void sendMessage(@NotNull CommandSender sender, String message) {
        sender.sendMessage(fixColor(message));
    }

    public static void sendMessage(@NotNull Collection<? extends Player> players, @NotNull String message) {
        players.forEach(p -> sendMessage(p, message));
    }


    private static void sendActionViaReflection(@NotNull Player player, @NotNull String message) {
        try {
            Class<?> packetPlayOutChat = ReflectionUtil.getNMSClass("PacketPlayOutChat");
            Constructor<?> packetConstructor = packetPlayOutChat.getConstructor(ReflectionUtil.getNMSClass("IChatBaseComponent"), byte.class);
            Class<?> ichat = ReflectionUtil.getNMSClass("IChatBaseComponent");
            Class<?> chatSerializer = ichat.getClasses()[0];
            Method csA = chatSerializer.getMethod("a", String.class);
            Object component = csA.invoke(chatSerializer, "{\"text\": \"" + fixColor(message) + "\"}");
            Object packet = packetConstructor.newInstance(component, (byte) 2);
            ReflectionUtil.sendPacket(player, packet);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void sendAction(@NotNull Collection<? extends Player> players, @NotNull String message) {
        players.forEach(p -> sendActionBar(p, message));
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        if (messages.get(event.getPlayer().getUniqueId()) != null)
            messages.get(event.getPlayer().getUniqueId()).clear();
    }

    public static void sendActionBar(@NotNull Player player, @NotNull String message) {
        List<String> msgs = messages.get(player.getUniqueId());
        if (msgs != null) {
            for (String msg : msgs) {
                if (msg.substring(0, 7).equalsIgnoreCase(message.substring(0, 7))) {
                    msgs.remove(message);
                }
            }
        }
        if (msgs == null) {
            List<String> strings = Lists.newArrayList(message);
            messages.put(player.getUniqueId(), strings);
            msgs = strings;
        } else {
            msgs.add(message);
        }
        sendActionViaReflection(player, Joiner.on(" &7&l:: ").join(msgs));
        Bukkit.getScheduler().runTaskLaterAsynchronously(plugin, () -> {
            messages.get(player.getUniqueId()).remove(message);
        }, 19L);
    }

    public static void sendTitle(@NotNull Player player, @NotNull String title, @NotNull String subTitle) {
        try {
            Object chatTitle = ReflectionUtil.getNMSClass("IChatBaseComponent").getDeclaredClasses()[0].getMethod("a", String.class)
                    .invoke(null, "{\"text\": \"" + fixColor(title) + "\"}");
            Constructor<?> titleConstructor = ReflectionUtil.getNMSClass("PacketPlayOutTitle").getConstructor(
                    ReflectionUtil.getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0], ReflectionUtil.getNMSClass("IChatBaseComponent"),
                    int.class, int.class, int.class);
            Object packet = titleConstructor.newInstance(
                    ReflectionUtil.getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0].getField("TITLE").get(null), chatTitle,
                    120, 30, 120);

            Object chatsTitle = ReflectionUtil.getNMSClass("IChatBaseComponent").getDeclaredClasses()[0].getMethod("a", String.class)
                    .invoke(null, "{\"text\": \"" + fixColor(subTitle) + "\"}");
            Constructor<?> timingTitleConstructor = ReflectionUtil.getNMSClass("PacketPlayOutTitle").getConstructor(
                    ReflectionUtil.getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0], ReflectionUtil.getNMSClass("IChatBaseComponent"),
                    int.class, int.class, int.class);
            Object timingPacket = timingTitleConstructor.newInstance(
                    ReflectionUtil.getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0].getField("SUBTITLE").get(null), chatsTitle,
                    120, 30, 120);

            ReflectionUtil.sendPacket(player, packet);
            ReflectionUtil.sendPacket(player, timingPacket);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void sendTitle(@NotNull Collection<? extends Player> players, @NotNull String title, @NotNull String subTitle) {
        players.forEach(p -> sendTitle(p, title, subTitle));
    }

}