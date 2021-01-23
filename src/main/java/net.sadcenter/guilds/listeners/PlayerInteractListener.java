package net.sadcenter.guilds.listeners;

import lombok.RequiredArgsConstructor;
import net.sadcenter.commons.helper.ChatHelper;
import net.sadcenter.commons.helper.RandomHelper;
import net.sadcenter.guilds.basic.Guild;
import net.sadcenter.guilds.basic.User;
import net.sadcenter.guilds.config.impl.storage.AntyLogoutStorage;
import net.sadcenter.guilds.guis.guilds.GuildClickPanelGui;
import net.sadcenter.guilds.managers.AntyLogoutManager;
import net.sadcenter.guilds.storage.Settings;
import net.sadcenter.guilds.utils.GuildUtil;
import net.sadcenter.guilds.utils.UserUtil;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

/**
 * @author sadcenter on 15.10.2020
 * @project LASTCORE
 */
@RequiredArgsConstructor
public class PlayerInteractListener implements Listener {

    private final AntyLogoutManager manager;
    private final AntyLogoutStorage storage;

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        if (event.getAction() != Action.RIGHT_CLICK_BLOCK)
            return;

        Player player = event.getPlayer();
        if (event.getClickedBlock().getType() == Material.ENCHANTMENT_TABLE && !Settings.isEnabledEnchanting()) {
            ChatHelper.sendMessage(player, "&cEnchanty sa aktualnie wylaczone!");
            event.setCancelled(true);
            return;
        }

        if (manager.getMap().asMap().containsKey(player.getUniqueId()) && storage.getBlockedBlocks().contains(event.getClickedBlock().getType().name())) {
            event.setCancelled(true);
            player.sendMessage(ChatHelper.fixColor(storage.getBlockedMessage()).replace("{BLOCK}", event.getClickedBlock().getType().name()));
        }
    }


    @EventHandler
    public void onInteractt(PlayerInteractEvent event) {
        if (event.getAction() != Action.RIGHT_CLICK_BLOCK)
            return;

        Player player = event.getPlayer();
        Guild g = GuildUtil.get(event.getClickedBlock().getLocation());
        if (g != null && event.getClickedBlock().getType() == Material.PUMPKIN) {
            if (g.getCreator().equals(player.getName()) || g.getLeader().equals(player.getName())) {
                player.openInventory(new GuildClickPanelGui(player, g).build());
            }
        }
    }

    @EventHandler
    public void onInteract(PlayerInteractAtEntityEvent event) {
        if (event.getRightClicked() instanceof Player) {
            User user = UserUtil.get(event.getPlayer().getUniqueId());
            User clicked = UserUtil.get(event.getRightClicked().getUniqueId());

            int add = (int) (RandomHelper.nextInt(32, 64) + (user.getPoints() - clicked.getPoints()) * -0.10);
            if (add < 0)
                add = 1;

            final int remove = (add / 4) * user.getLevel();

            ChatHelper.sendMessage(event.getPlayer(), "&7Za zabicie tego gracza otrzymasz &a" + add + " &7punktow, za smierc od tego gracza stracisz &c" + remove + " &7punktow");
        }

    }

}
