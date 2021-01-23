package net.sadcenter.guilds.listeners;

import net.sadcenter.commons.helper.ChatHelper;
import net.sadcenter.guilds.basic.Guild;
import net.sadcenter.guilds.basic.Member;
import net.sadcenter.guilds.basic.fields.ChestProtection;
import net.sadcenter.guilds.utils.GuildUtil;
import net.sadcenter.guilds.utils.LocationUtil;
import net.sadcenter.guilds.utils.MemberUtil;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.HashSet;

public class InventoryOpenListener implements Listener {

    /*
    @EventHandler
    public void onOpen(final InventoryOpenEvent event) {
        if(event.getInventory().getType().equals(InventoryType.CHEST)) {
            final Guild g = GuildManager.get(event.getPlayer().getLocation());
            if(g == null)
                return;
            if(!MemberManager.get(event.getPlayer().getUniqueId()).isChestPerm()) {
                event.setCancelled(true);
                TitleUtil.sendTitle((Player)event.getPlayer(), "", "&8>> &cNie posiadasz do tego uprawnien!");
            }
        }
    }

     */
    @EventHandler
    public void onOpen(final PlayerInteractEvent event) {
        if (event.getAction().equals(Action.LEFT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_AIR))
            return;
        final Guild g = GuildUtil.get(event.getClickedBlock().getLocation());
        if (g == null)
            return;
        final Player p = event.getPlayer();
        final Member o = MemberUtil.get(event.getPlayer().getUniqueId());
        Block target = event.getPlayer().getTargetBlock((HashSet<Byte>) null, 4);

        if (target.getType() != Material.CHEST)
            return;

        if (target.getType().equals(Material.CHEST) && (o == null || !o.isChestPerm())) {
            event.setCancelled(true);
            p.closeInventory();
            return;
        }
        ChestProtection protection = g.getChests().stream().filter(chestProtection -> LocationUtil.getLocationFromChest(target).equals(LocationUtil.getLocationFromChest(chestProtection.getChest().getBlock()))).findFirst().orElse(null);
        if (protection != null && !protection.getAccess().contains(event.getPlayer().getName()) && g.isMember(event.getPlayer().getName())) {
            ChatHelper.sendMessage(event.getPlayer(), "&cNie masz uprawnien do tej skrzynki!");
            event.setCancelled(true);
        }
    }

}
