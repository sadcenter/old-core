package net.sadcenter.guilds.listeners;

import lombok.AllArgsConstructor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.Arrays;

/**
 * @author sadcenter on 20.08.2020
 * @project LASTCORE
 */
@AllArgsConstructor
public class PlayerConsumeListener implements Listener {

    private final Plugin plugin;
    private final BukkitScheduler scheduler;

    @EventHandler
    public void onConsume(PlayerItemConsumeEvent event) {
        ItemStack itemStack = event.getItem();
        Player p = event.getPlayer();
        p.updateInventory();
        if (!itemStack.getType().equals(Material.GOLDEN_APPLE))
            return;
        scheduler.runTaskLater(plugin, () -> {


            if (itemStack.getDurability() == 1) {
                p.removePotionEffect(PotionEffectType.REGENERATION);
                p.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
                p.removePotionEffect(PotionEffectType.FIRE_RESISTANCE);
                p.addPotionEffects(
                        Arrays.asList(
                                new PotionEffect(PotionEffectType.REGENERATION, 20 * 20, 6),
                                new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 20 * 160, 0),
                                new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 20 * 160, 1)
                        )
                );
            }
        }, 1L);
    }
}
