package net.sadcenter.guilds.events;

import lombok.AllArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.*;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

/**
 * @author sadcenter on 06.11.2020
 * @project LastCore
 */
@AllArgsConstructor
public final class PlayerDamageByPlayerEvent extends Event implements Cancellable, Listener {

    private final EntityDamageByEntityEvent event;

    public static HandlerList getHandlerList() {
        return new HandlerList();
    }

    @EventHandler
    public void onAttack(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player && event.getEntity() instanceof Player)
            Bukkit.getPluginManager().callEvent(new PlayerDamageByPlayerEvent(event));
    }

    public Player getPlayer() {
        return (Player) event.getEntity();
    }

    public Player getDamager() {
        return (Player) event.getDamager();
    }

    public double getDamage() {
        return event.getDamage();
    }

    public void setDamage(double damage) {
        event.setDamage(damage);
    }

    @Override
    public boolean isCancelled() {
        return event.isCancelled();
    }

    @Override
    public void setCancelled(boolean cancelled) {
        event.setCancelled(cancelled);
    }

    @Override
    public HandlerList getHandlers() {
        return new HandlerList();
    }
}
