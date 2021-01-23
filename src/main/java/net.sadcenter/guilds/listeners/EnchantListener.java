package net.sadcenter.guilds.listeners;

import lombok.RequiredArgsConstructor;
import net.sadcenter.commons.helper.ChatHelper;
import net.sadcenter.commons.helper.ItemBuilder;
import net.sadcenter.guilds.storage.Settings;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author sadcenter on 20.08.2020
 * @project LASTCORE
 */
@RequiredArgsConstructor
public class EnchantListener implements Listener {


    private final Plugin plugin;
    private final Map<Enchantment, Integer> enchantsLimit = new ConcurrentHashMap<Enchantment, Integer>() {
        {
            put(Enchantment.ARROW_KNOCKBACK, 0);
            put(Enchantment.KNOCKBACK, 1);
            put(Enchantment.ARROW_INFINITE, 0);
            put(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
            if(!Settings.isEnabledDiamondsItems()) {
                put(Enchantment.DAMAGE_ALL, 3);
                put(Enchantment.FIRE_ASPECT, 0);
            } else {
                put(Enchantment.FIRE_ASPECT, 1);
            }
        }
    };

    @EventHandler
    public void onEnchant(EnchantItemEvent event) {
        ItemStack item = event.getItem();
        for (Map.Entry<Enchantment, Integer> enchantmentIntegerEntry : enchantsLimit.entrySet()) {
            Enchantment enchantment = enchantmentIntegerEntry.getKey();
            int maxLevel = enchantmentIntegerEntry.getValue();
            if (event.getEnchantsToAdd().containsKey(enchantment) && event.getEnchantsToAdd().get(enchantment) > maxLevel) {
                ChatHelper.sendMessage(event.getEnchanter(), "&cZamieniono enchant &3" + enchantment.getName() + "&c na level &3" + maxLevel);
                plugin.getServer().getScheduler().runTaskLaterAsynchronously(plugin, () -> {

                    event.getEnchantsToAdd().keySet().forEach(item::removeEnchantment);
                    if (maxLevel != 0) {
                        event.getEnchantsToAdd().keySet().forEach(i -> item.addEnchantment(i, maxLevel));
                    }
                }, 3L);
            }
        }
    }

    @EventHandler
    public void onOpenEnchant(InventoryOpenEvent event) {
        Inventory inventory = event.getInventory();
        if (inventory.getType() == InventoryType.ENCHANTING)
            inventory.setItem(1, ItemBuilder.of(Material.getMaterial(351)).setDurability(4).setAmount(64).build());

    }

    @EventHandler
    public void onEnchant(InventoryClickEvent event) {
        if (event.getInventory().getType() != InventoryType.ANVIL)
            return;

        ItemStack itemStack = event.getCurrentItem();
        for (Map.Entry<Enchantment, Integer> enchantmentIntegerEntry : enchantsLimit.entrySet()) {
            int enchantlimit = enchantmentIntegerEntry.getValue();
            Enchantment enchantment = enchantmentIntegerEntry.getKey();
            if (itemStack.getEnchantments().containsKey(enchantment) && itemStack.getEnchantments().get(enchantment) > enchantlimit) {
                ChatHelper.sendMessage((Player) event.getWhoClicked(), "&cZamieniono enchant &3" + enchantment.getName() + "&c na level &3" + enchantlimit);
                itemStack.removeEnchantment(enchantment);
                if (enchantlimit != 0) {
                    itemStack.addEnchantment(enchantment, enchantlimit);
                }
            }
        }
    }
}