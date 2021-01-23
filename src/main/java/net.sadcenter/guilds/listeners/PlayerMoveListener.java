package net.sadcenter.guilds.listeners;

import lombok.RequiredArgsConstructor;
import net.sadcenter.commons.helper.ChatHelper;
import net.sadcenter.guilds.basic.Guild;
import net.sadcenter.guilds.config.impl.storage.AntyLogoutStorage;
import net.sadcenter.guilds.managers.AntyLogoutManager;
import net.sadcenter.guilds.utils.GuildUtil;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffectType;

import java.util.Arrays;

@RequiredArgsConstructor
public class PlayerMoveListener implements Listener {

    //private final AntiXray antiXray = new AntiXray(new SpigotWorldConfig("world"));

    private final AntyLogoutManager antyLogoutManager;
    private final AntyLogoutStorage storage;

    private boolean isInLocation(final int minX, final int maxX, final int minZ, final int maxZ, final Location l) {
        final double[] dim = {minX, maxX};
        Arrays.sort(dim);
        if (l.getX() >= dim[1] || l.getX() <= dim[0]) {
            return false;
        }
        dim[0] = minZ;
        dim[1] = maxZ;
        Arrays.sort(dim);
        return l.getZ() < dim[1] && l.getZ() > dim[0];
    }

    @EventHandler
    public void onMove(final PlayerMoveEvent e) {
        if (e.isCancelled()) {
            return;
        }
        final Player player = e.getPlayer();
        if (antyLogoutManager.getMap().asMap().containsKey(player.getUniqueId())) {
            final Location to = e.getTo();
            final Location from = e.getFrom();
            if ((from.getBlockX() != to.getBlockX() || from.getBlockY() != to.getBlockY() || from.getBlockZ() != to.getBlockZ()) && isInLocation(storage.getMinX(), storage.getMaxX(), storage.getMinZ(), storage.getMaxZ(), to)) {
                e.setTo(from);
            }
        }


        final Location from = e.getFrom();
        final Location to = e.getTo();
        if (to.getBlockX() == from.getBlockX() && to.getBlockZ() == from.getBlockZ()) {
            return;
        }


        Guild guild = GuildUtil.get(to);
        if (guild != null) {
            if (guild.getRegion().isInCuboid(from)) {
                return;
            }
            boolean member = guild.isMember(player.getName());
            ChatHelper.sendTitle(player, "", "&7Wkroczyłeś na teren gildii &7[" + (member ? "&a" : "&c") + guild.getTag() + "&7]");
            if (!member)
                guild.getOnlinePlayers().forEach(player1 -> ChatHelper.sendMessage(player1, "&cIntruz na terenie gildii!"));
        }
        Guild g = GuildUtil.get(from);
        if (g != null && guild == null) {
            boolean member = g.isMember(player.getName());
            ChatHelper.sendTitle(player, "", "&7Opusciłeś teren gildii &7[" + (member ? "&a" : "&c") + g.getTag() + "&7]");
            if (!member)
                g.getOnlinePlayers().forEach(player1 -> ChatHelper.sendMessage(player1, "&cIntruz opuscił teren gildii!"));


        }
    }
}