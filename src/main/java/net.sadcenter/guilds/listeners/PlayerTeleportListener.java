package net.sadcenter.guilds.listeners;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

/**
 * @author sadcenter on 04.11.2020
 * @project LastCore
 */

public class PlayerTeleportListener implements Listener {

    @EventHandler
    public void onTeleport(EntityDamageByEntityEvent event) {
        if (event.getEntityType() == EntityType.PLAYER && event.getDamager().getType() == EntityType.ENDER_PEARL)
            event.setDamage(0.0);
    }
}
