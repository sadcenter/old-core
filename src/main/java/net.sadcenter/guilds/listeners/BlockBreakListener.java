package net.sadcenter.guilds.listeners;

import lombok.RequiredArgsConstructor;
import net.sadcenter.commons.helper.ChatHelper;
import net.sadcenter.commons.helper.ItemHelper;
import net.sadcenter.commons.helper.RandomHelper;
import net.sadcenter.guilds.basic.Guild;
import net.sadcenter.guilds.basic.Member;
import net.sadcenter.guilds.managers.GuildManager;
import net.sadcenter.guilds.utils.GuildUtil;
import net.sadcenter.guilds.utils.MemberUtil;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
public class BlockBreakListener implements Listener {

    private final GuildManager guildManager;

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        Block block = event.getBlock();
        Material type = event.getBlock().getType();
        Player player = event.getPlayer();
        final Guild g = GuildUtil.get(event.getBlock().getLocation());
        if (g == null) {
            return;

        }
        if (g.getRegion().isInCentrum(event.getBlock().getLocation(), 5, 1, 2)) {
            event.setCancelled(true);
            ChatHelper.sendMessage(player, "&cNie mozesz tutaj niszczyc!");
            if (block.getType() == Material.PUMPKIN) {
                if(g.getProtection() > System.currentTimeMillis()) {
                    ChatHelper.sendMessage(player, "&CTa gildia posiada ochrone!");
                    return;
                }
                Guild fromPlayer = GuildUtil.getFromPlayer(player.getUniqueId());
                if (fromPlayer == null) {
                    ChatHelper.sendMessage(player, "&cNie posiadasz gildii");
                    return;
                }
                if (!g.isMember(player.getName())) {
                    if (g.getHearts() == 0) {
                        g.setLife(g.getLife() - 1);
                        g.setHearts(500);
                        g.setProtection(System.currentTimeMillis() + TimeUnit.HOURS.toMillis(12));
                        if (g.getLife() == 0) {
                            ChatHelper.sendMessage(Bukkit.getOnlinePlayers(), "&7Gildia &3" + g.getTag() + " &7zostala podbita przez " + fromPlayer.getTag());
                            guildManager.deleteGuild(g);
                        }
                        return;
                    }
                    g.setHearts(g.getHearts() - 1);
                    ChatHelper.sendTitle(player, "&a" + g.getHearts() + "&7/&c500", "&7Pozostalo " + "&a" + g.getHearts() + "&7/&c500");
                    return;
                }
            }
        }
        final Member o = MemberUtil.get(event.getPlayer().getName());
        boolean isMember = o != null && g.isMember(o.getName());
        if (!player.hasPermission("scode.helper") && (!isMember || !o.isBreakPerm())) {
            event.setCancelled(true);
            event.setExpToDrop(0);
            ChatHelper.sendMessage(event.getPlayer(), "&cNie posiadasz uprawnien do niszczenia na cuboidzie [" + g.getTag() + "]");
            return;
        }

        if (type == Material.LAPIS_BLOCK && isMember && !o.isLapizPerm()) {
            event.setCancelled(true);
            event.setExpToDrop(0);
            ChatHelper.sendMessage(player, "&cNie posiadasz uprawnien do lapizu");
            return;
        }

        if (type == Material.TNT && isMember && !o.isTntPerm()) {
            event.setCancelled(true);
            event.setExpToDrop(0);
            ChatHelper.sendMessage(player, "&cNie posiadasz uprawnien do tnt");
        }

    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        if (event.getAction() == Action.LEFT_CLICK_BLOCK) {
            Block clickedBlock = event.getClickedBlock();
            Player player = event.getPlayer();
            final Guild g = GuildUtil.get(clickedBlock.getLocation());
            if (g == null) {
                return;
            }
            final Member o = MemberUtil.get(player.getName());
            boolean isMember = o != null && g.isMember(o.getName());
            if (!player.hasPermission("scode.helper") && (!isMember || !o.isBreakPerm())) {
                player.sendBlockChange(event.getClickedBlock().getLocation(), Material.BEDROCK, (byte) 10);
            }
        }
    }

}
