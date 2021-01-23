package net.sadcenter.guilds.listeners;

import com.keenant.tabbed.Tabbed;
import lombok.RequiredArgsConstructor;
import net.sadcenter.commons.helper.RandomHelper;
import net.sadcenter.guilds.basic.User;
import net.sadcenter.guilds.config.impl.MainConfiguration;
import net.sadcenter.guilds.managers.TopManager;
import net.sadcenter.guilds.managers.VanishManager;
import net.sadcenter.guilds.utils.TabListUtil;
import net.sadcenter.guilds.utils.UserUtil;
import net.sadcenter.guilds.utils.Worlds;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

@RequiredArgsConstructor
public class PlayerJoinListener implements Listener {

    private final VanishManager vanishManager;
    private final Tabbed tabbed;
    private final TopManager topManager;
    private final MainConfiguration configuration;

    @EventHandler
    public void onJoin(final PlayerJoinEvent event) {
        Player player = event.getPlayer();
        event.setJoinMessage(null);
        User u = UserUtil.get(player.getUniqueId());
        if(u == null) {
            u = UserUtil.createIfAbsent(player);
            int x = RandomHelper.nextInt(0, 2000);
            int z = RandomHelper.nextInt(0, 2000);
            player.teleport(new Location(Worlds.DEAFULT_WORLD, x, Worlds.DEAFULT_WORLD.getHighestBlockYAt(x, z)+10, z));
        }
        u.setTurboDrop(configuration.getStorage().getTurboDropServer());
        if (vanishManager.contains(player))
            vanishManager.hidePlayer(event.getPlayer());

        TabListUtil.init(player, Bukkit.getOnlinePlayers().size(), tabbed, topManager);
        u.getLastConnection().setDate(System.currentTimeMillis());
        if (u.getFirstConnection().getDate() == 0L)
            u.getFirstConnection().setDate(System.currentTimeMillis());


    }

  /*  @EventHandler
    public void onConnect(AsyncPlayerPreLoginEvent event) {
        BlacklistManagerImpl.getBlacklist(event.getUniqueId()).ifPresent(blacklist -> {
            event.disallow(AsyncPlayerPreLoginEvent.Result.KICK_BANNED, ChatHelper.fixColor("&cblacklista &7" + blacklist.getReason()));
        });
    }

    @EventHandler
    public void onConnection(AsyncPlayerPreLoginEvent event) {
        BlacklistManagerImpl.getBlacklist(event.getAddress()).ifPresent(blacklist -> {
            event.disallow(AsyncPlayerPreLoginEvent.Result.KICK_BANNED, ChatHelper.fixColor("&cblacklista &7" + blacklist.getReason()));
        });
    }

   */


}

