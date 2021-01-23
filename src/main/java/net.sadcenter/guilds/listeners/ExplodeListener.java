package net.sadcenter.guilds.listeners;

import net.sadcenter.commons.helper.ChatHelper;
import net.sadcenter.guilds.basic.Guild;
import net.sadcenter.guilds.basic.fields.BlockField;
import net.sadcenter.guilds.storage.Settings;
import net.sadcenter.guilds.utils.GuildUtil;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;

import java.util.concurrent.TimeUnit;

/**
 * @author sadcenter on 17.08.2020
 * @project LASTCORE
 */

public class ExplodeListener implements Listener {


    @EventHandler
    public void onExplode(EntityExplodeEvent event) {
        if (!Settings.isEnabledTnt()) {
            event.setCancelled(true);
            return;
        }
        Guild guild = GuildUtil.get(event.getLocation());
        if (guild == null) return;
        if (guild.getProtection() > System.currentTimeMillis()) {
            event.setCancelled(true);
            return;
        }
        for (Block block : event.blockList()) {
            BlockField blockField = new BlockField(block.getType(), block.getLocation(), block.getData());
            guild.getExplodedBlocks().add(blockField);
        }

        if (!event.isCancelled()) {
            ChatHelper.sendTitle(guild.getOnlinePlayers(), "&4tnt", "&cna twoim terenie wybuchlo " + event.blockList().size() + " blokow");
            guild.setBlockingPlace(System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(120));
        }
    }
}
