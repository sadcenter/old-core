package net.sadcenter.guilds.runnables;

import lombok.RequiredArgsConstructor;
import net.sadcenter.commons.helper.ChatHelper;
import net.sadcenter.guilds.api.BossBarAPI;
import net.sadcenter.guilds.basic.Guild;
import net.sadcenter.guilds.basic.User;
import net.sadcenter.guilds.config.impl.storage.AntyLogoutStorage;
import net.sadcenter.guilds.managers.AntyLogoutManager;
import net.sadcenter.guilds.managers.ProtectionManager;
import net.sadcenter.guilds.managers.UserManager;
import net.sadcenter.guilds.managers.VanishManager;
import net.sadcenter.guilds.utils.DateUtil;
import net.sadcenter.guilds.utils.GuildUtil;
import net.sadcenter.guilds.utils.UserUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.concurrent.TimeUnit;

/**
 * @author sadcenter on 30.08.2020
 * @project LASTCORE
 */
@RequiredArgsConstructor
public final class MessagesRunnable implements Runnable {

    private final ProtectionManager protectionManager;
    private final VanishManager vanishManager;
    private final UserManager userManager;
    private final AntyLogoutManager antyLogoutManager;
    private final AntyLogoutStorage storage;

    @Override
    public void run() {
        for (User value : userManager.getUsers().values()) {
            value.setMacro(0);
        }

        antyLogoutManager.getMap().asMap().forEach((uuid, antyLogout) -> {
            Player player = Bukkit.getPlayer(uuid);

            if(player == null)
                return;

            ChatHelper.sendActionBar(player, storage.getAntyLogoutMessage().replace("{SECONDS}", String.valueOf(TimeUnit.MILLISECONDS.toSeconds(antyLogout.getExpire() - System.currentTimeMillis()))));

        });

        for (Player player : Bukkit.getOnlinePlayers()) {
            final Guild guild = GuildUtil.get(player.getLocation());
            final User user = UserUtil.get(player.getUniqueId());
            if (user.getTurboDrop() != null) {
                if (user.getTurboDrop().getExpire() < System.currentTimeMillis()) {
                    user.setTurboDrop(null);
                    ChatHelper.sendTitle(player, "&3TURBODROP", "&7TurboDrop wygasl!");
                } else
                    ChatHelper.sendActionBar(player, "&7Aktywny jest turbodrop, pozostalo: &3" + DateUtil.getDurationBreakdownShort(user.getTurboDrop().getExpire() - System.currentTimeMillis()));
            } else if (guild != null && guild.getTurboDrop() != null) {
                if (guild.getTurboDrop().getExpire() < System.currentTimeMillis()) {
                    guild.setTurboDrop(null);
                    ChatHelper.sendTitle(guild.getOnlinePlayers(), "&3TURBODROP", "&7TurboDrop wygasl!");
                } else
                    ChatHelper.sendActionBar(player, "&7Aktywny jest turbodrop, pozostalo: &3" + DateUtil.getDurationBreakdownShort(guild.getTurboDrop().getExpire() - System.currentTimeMillis()));
            }

            if (guild == null)
                BossBarAPI.hideBossBar(player);
            else {
                final boolean member = guild.isMember(player.getName());
                BossBarAPI.sendBossBar(player, ChatHelper.fixColor(member ? "&aJesteś na terenie swojej gildii &7[&a" + guild.getTag() + "&7]" : "&cJesteś na terenie wrogiej gildii &7[&c" + guild.getTag() + "&7]"));
            }


            if (vanishManager.contains(player)) {
                ChatHelper.sendActionBar(player, "&aJestes niewidoczny!");
            }


        }

        protectionManager.getProtections().forEach((uuid, protection) -> {
            Player player = Bukkit.getPlayer(uuid);
            if (protection.getExpire() < System.currentTimeMillis()) {
                protectionManager.getProtections().remove(uuid);
                if (player != null)
                    ChatHelper.sendTitle(player, "", "&7Twoja ochrona dobiegla konca!");
            }
            if (player != null)
                ChatHelper.sendActionBar(player, "&3OCHRONA &7" + DateUtil.getDurationBreakdownShort(protection.getExpire() - System.currentTimeMillis()));
        });
    }
}
