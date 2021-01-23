package net.sadcenter.guilds.runnables;

import com.keenant.tabbed.Tabbed;
import lombok.RequiredArgsConstructor;
import net.sadcenter.guilds.managers.GuildManager;
import net.sadcenter.guilds.managers.TopManager;
import net.sadcenter.guilds.managers.UserManager;
import net.sadcenter.guilds.utils.TabListUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 * @author sadcenter on 01.09.2020
 * @project LASTCORE
 */

@RequiredArgsConstructor
public final class SortRunnable implements Runnable {

    private final Tabbed tabbed;
    private final TopManager topManager;
    private final UserManager userManager;
    private final GuildManager guildManager;

    @Override
    public void run() {
        final int online = Bukkit.getOnlinePlayers().size();
        topManager.sort(userManager.getUsers().values(), guildManager.getGuilds().values(), true);
        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            tabbed.destroyTabList(onlinePlayer);
            TabListUtil.init(
                    onlinePlayer,
                    online,
                    tabbed,
                    topManager);
        }
    }
}
