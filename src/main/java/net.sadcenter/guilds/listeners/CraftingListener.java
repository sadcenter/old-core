package net.sadcenter.guilds.listeners;

import com.google.common.collect.Sets;
import net.sadcenter.commons.helper.ChatHelper;
import net.sadcenter.guilds.storage.Settings;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;

import java.util.Set;

/**
 * @author sadcenter on 01.09.2020
 * @project LASTCORE
 */

public class CraftingListener implements Listener {

    private final Set<Material> blocked = Sets.newHashSet(Material.DIAMOND_CHESTPLATE, Material.DIAMOND_HELMET, Material.DIAMOND_LEGGINGS, Material.DIAMOND_BOOTS, Material.DIAMOND_SWORD);

    @EventHandler
    public void onCraft(CraftItemEvent event) {
        if (event.getCurrentItem() == null) {
            return;
        }

        if (Settings.isEnabledDiamondsItems()) {
            return;
        }

        if (blocked.stream().anyMatch(material -> event.getCurrentItem().getType().equals(material))) {
            event.setCancelled(true);
            event.setResult(Event.Result.DENY);
            event.setCurrentItem(null);
            ChatHelper.sendMessage((Player) event.getWhoClicked(), "&cZablokowano crafting!");
        }
    }
}

