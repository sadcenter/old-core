package net.sadcenter.guilds.drop.listeners;

import lombok.RequiredArgsConstructor;
import net.sadcenter.commons.helper.ChatHelper;
import net.sadcenter.commons.helper.ItemHelper;
import net.sadcenter.commons.helper.RandomHelper;
import net.sadcenter.guilds.basic.Guild;
import net.sadcenter.guilds.basic.User;
import net.sadcenter.guilds.drop.DropData;
import net.sadcenter.guilds.drop.DropStorage;
import net.sadcenter.guilds.utils.GuildUtil;
import net.sadcenter.guilds.utils.MathUtils;
import net.sadcenter.guilds.utils.PolishNamesUtil;
import net.sadcenter.guilds.utils.UserUtil;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Set;

/**
 * @author sadcenter on 03.09.2020
 * @project LASTCORE
 */

@RequiredArgsConstructor
public class BlockBreakListener implements Listener {

    private final Set<DropData> dropdatas;
    private final DropStorage dropStorage;


    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        final Player p = event.getPlayer();
        final Location location = event.getBlock().getLocation();
        final User user = UserUtil.get(p.getUniqueId());
      /*  if (dropStorage.getCancelled().contains(event.getBlock().getType())) {
            ChatHelper.sendMessage(p, "&cTa ruda jest zablokowana");
            event.setCancelled(true);
            return;
        }

       */
        if (event.getBlock().getType() != Material.STONE)
            return;

        if (event.isCancelled())
            return;

        if (p.getGameMode() == GameMode.CREATIVE) {
            return;
        }

        event.getBlock().getDrops().clear();
        event.getBlock().breakNaturally(new ItemStack(Material.AIR));

        if (user.getDrop().isEnable(Material.COBBLESTONE)) {
            ItemHelper.giveOrDrop(p, new ItemStack(Material.COBBLESTONE), location);
        }

        Guild g = GuildUtil.getFromPlayer(p.getUniqueId());
        for (DropData dropdata : dropdatas) {
            if (!user.getDrop().isEnable(dropdata.getMaterial())) {
                return;
            }
            if (location.getBlockY() > dropdata.getMaximalY() || location.getBlockY() < dropdata.getMinimalY()) {
                return;
            }

            double multi = dropdata.getChance();
            if (p.hasPermission("core.svip")) {
                multi += MathUtils.math(multi, 35);
            } else if (p.hasPermission("core.vip")) {
                multi += MathUtils.math(multi, 25);
            }

            multi += 0.05 * p.getItemInHand().getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS);
            if (user.getTurboDrop() != null && user.getTurboDrop().getExpire() > System.currentTimeMillis()) {
                multi *= dropStorage.getTurbodropMultiplier();
            }

            //if(LastCore.getInstance().getConfiguration().getStorage())

            if ((g != null && g.getTurboDrop() != null) && g.getTurboDrop().getExpire() > System.currentTimeMillis()) {
                multi *= dropStorage.getTurbodropMultiplier();
            }
            //System.out.println(dropdata.getMaterial() + " xd " + multi);
            if(multi <= 0) multi = dropdata.getChance();
            if (RandomHelper.hasChance(multi)) {
                p.giveExp(dropdata.getExp());
                if (user.getDrop().isNotifications()) {
                    ChatHelper.sendMessage(p, "&7Wydropiles &3" + PolishNamesUtil.getPolishName(dropdata.getMaterial(), (short) 0));
                }
                ItemHelper.giveOrDrop(p, new ItemStack(dropdata.getMaterial(), RandomHelper.nextInt(dropdata.getMaxAmount(), dropdata.getMaxAmount())), location);
            }
        }
    }
}
