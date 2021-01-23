package net.sadcenter.guilds.listeners;

import net.sadcenter.commons.helper.ChatHelper;
import net.sadcenter.guilds.basic.Guild;
import net.sadcenter.guilds.basic.Member;
import net.sadcenter.guilds.storage.Settings;
import net.sadcenter.guilds.utils.GuildUtil;
import net.sadcenter.guilds.utils.MemberUtil;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import java.util.concurrent.TimeUnit;

public class BlockPlaceListener implements Listener {

    @EventHandler
    public void onPlace(final BlockPlaceEvent event) {
        Block blockPlaced = event.getBlockPlaced();
        Material type = blockPlaced.getType();
        Player player = event.getPlayer();
        if (type == Material.TNT && !Settings.isEnabledTnt()) {
            event.setCancelled(true);
            return;
        }
        final Guild g = GuildUtil.get(blockPlaced.getLocation());
        if (g == null)
            return;
        if (g.getRegion().isInCentrum(event.getBlock().getLocation(), 5, 1, 2)) {
            event.setCancelled(true);
            ChatHelper.sendMessage(player, "&cNie mozesz tutaj stawiac blokow!");
            return;
        }
        final Member o = MemberUtil.get(player.getUniqueId());
        boolean isMember = o != null && g.isMember(player.getName());
        if (!player.hasPermission("scode.helper") && (!isMember || !o.isPlacePerm()) && type != Material.FIRE) {
            event.setCancelled(true);
            ChatHelper.sendMessage(event.getPlayer(), "&cNie posiadasz uprawnien do budowania na cuboidzie [" + g.getTag() + "]");
            return;
        }
        if (!isMember && g.getBlockingPlace() > System.currentTimeMillis()) {
            event.setCancelled(true);
            ChatHelper.sendMessage(event.getPlayer(), "&cZablokowano postawianie blokow! Pozostalo: &3" + TimeUnit.MILLISECONDS.toSeconds(g.getBlockingPlace() - System.currentTimeMillis()));
            return;
        }
        if (type == Material.LAPIS_BLOCK && isMember && !o.isLapizPerm()) {
            event.setCancelled(true);
            ChatHelper.sendMessage(event.getPlayer(), "&cNie posiadasz uprawnien do lapizu");
            return;
        }

        if (type == Material.TNT && isMember && !o.isTntPerm()) {
            event.setCancelled(true);
            ChatHelper.sendMessage(event.getPlayer(), "&cNie posiadasz uprawnien do tnt");
        }
    }
}
