package net.sadcenter.guilds.listeners;

import lombok.RequiredArgsConstructor;
import net.sadcenter.commons.helper.ChatHelper;
import net.sadcenter.commons.helper.ItemBuilder;
import net.sadcenter.commons.helper.ItemHelper;
import net.sadcenter.guilds.basic.User;
import net.sadcenter.guilds.managers.CaseManager;
import net.sadcenter.guilds.storage.Settings;
import net.sadcenter.guilds.utils.PolishNamesUtil;
import net.sadcenter.guilds.utils.UserUtil;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.permissions.PermissionAttachmentInfo;

/**
 * @author sadcenter on 15.09.2020
 * @project LASTCORE
 */
@RequiredArgsConstructor
public class CaseListener implements Listener {

    @EventHandler
    public void onBlockPlace(final BlockPlaceEvent event) {
        Player p = event.getPlayer();


        if (p.getItemInHand() == null)
            return;

        if (p.getItemInHand().getType() == Material.CHEST && p.getItemInHand().getEnchantments().containsKey(Enchantment.THORNS)) {
            event.setCancelled(true);
            if (!Settings.isEnabledCase()) {
                ChatHelper.sendMessage(p, "&cSkrzynki sa aktualnie wylaczone!");
                return;
            }
            p.getInventory().removeItem(ItemBuilder.of(Material.CHEST).setName("&3&lMAGICZNA SKRZYNKA!").addEnchantment(Enchantment.THORNS, 10).build());
            ItemStack itemStack = CaseManager.getRandomCaseItemStack();
            ItemHelper.giveOrDrop(p, itemStack, p.getLocation());
            for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                User user = UserUtil.get(onlinePlayer.getUniqueId());
                if (user.getChatOptions().isCaseMessages()) {
                    ChatHelper.sendMessage(onlinePlayer, "&3" + p.getName() + " &7wylosowal z case &3" + PolishNamesUtil.getPolishName(itemStack.getType(), itemStack.getDurability()) + (itemStack.getItemMeta() == null || itemStack.getItemMeta().getDisplayName() == null ? "" : " &8(" + itemStack.getItemMeta().getDisplayName() + "&8)") + " &7x&3" + itemStack.getAmount());
                }
            }
        } else if(p.getItemInHand().getType() == Material.MOSSY_COBBLESTONE && p.getItemInHand().getEnchantments().containsKey(Enchantment.THORNS)) {
            event.setCancelled(true);
            p.getInventory().removeItem(ItemBuilder.of(Material.MOSSY_COBBLESTONE   ).setName("&3&lCOBBLEX!").addEnchantment(Enchantment.THORNS, 10).build());
            ItemStack itemStack = CaseManager.getRandomCobblexItemStack();
            ItemHelper.giveOrDrop(p, itemStack, p.getLocation());
        }
    }

}
