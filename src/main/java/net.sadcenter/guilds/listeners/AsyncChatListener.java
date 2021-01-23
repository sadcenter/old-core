package net.sadcenter.guilds.listeners;

import lombok.RequiredArgsConstructor;
import net.sadcenter.commons.helper.ChatHelper;
import net.sadcenter.guilds.LastCore;
import net.sadcenter.guilds.basic.Guild;
import net.sadcenter.guilds.basic.Mute;
import net.sadcenter.guilds.basic.User;
import net.sadcenter.guilds.managers.ChatManager;
import net.sadcenter.guilds.managers.MuteManager;
import net.sadcenter.guilds.utils.DateUtil;
import net.sadcenter.guilds.utils.GuildUtil;
import net.sadcenter.guilds.utils.PermissionUtil;
import net.sadcenter.guilds.utils.UserUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.TimeUnit;

/**
 * @author sadcenter on 17.08.2020
 * @project LASTCORE
 */
@RequiredArgsConstructor
public class AsyncChatListener implements Listener {

    private final MuteManager muteManager;

    @EventHandler
    public void onChat(@NotNull AsyncPlayerChatEvent event) {
        Player p = event.getPlayer();
        Mute mute = muteManager.getMutes().get(p.getName());
        if (mute != null && mute.getExpire() != 0L && mute.getExpire() < System.currentTimeMillis())
            muteManager.remove(mute);


        if (mute != null) {
            event.setCancelled(true);
            ChatHelper.sendMessage(event.getPlayer(), "&cZostales wyciszony przez &7" + mute.getAdmin() + " &cza &8'&3" + mute.getReason() + "&8'," +
                    " &cmute wygasa: &c" + (mute.getExpire() == 0L ? "nigdy" : DateUtil.getDate(mute.getExpire()) + " &8(&c" + DateUtil.getDurationBreakdown(mute.getExpire()) + "&8)"));
            return;
        }
        User eventUser = UserUtil.get(p.getUniqueId());
        if (eventUser.getSlow() > System.currentTimeMillis() && !p.hasPermission("scode.bypassChat")) {
            event.setCancelled(true);
            ChatHelper.sendMessage(p, "&cNastepna wiadomosc mozesz wyslac za &3" + TimeUnit.MILLISECONDS.toSeconds(eventUser.getSlow() - System.currentTimeMillis()));
            return;
        }
        eventUser.setSlow(System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(ChatManager.SLOW));

        Guild guild = GuildUtil.getFromPlayer(p.getName());
        net.luckperms.api.model.user.User u = PermissionUtil.getUser(event.getPlayer());
        String prefix = PermissionUtil.getGroup(u.getPrimaryGroup()).getCachedData().getMetaData().getPrefix();

        boolean has = p.hasPermission("core.helper");
        event.setCancelled(true);
        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            User user = UserUtil.get(onlinePlayer.getUniqueId());
            boolean test = user.getChatOptions().isChatMessages();
            boolean inSameGuild = guild != null && guild.isMember(onlinePlayer.getName());
            if (has)
                test = true;

            if (test)
                onlinePlayer.sendMessage(ChatHelper.fixColor((guild == null ? "" : "&8[" + (inSameGuild ? "&a" : "&c") + guild.getTag() + "&8r] ") + "&7" + prefix + p.getName() + "&8: " + (p.isOp() ? "&6" : "&f") + event.getMessage()));
        }
    }
}
