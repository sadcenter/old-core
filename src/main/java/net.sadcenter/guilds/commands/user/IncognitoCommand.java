package net.sadcenter.guilds.commands.user;

import net.sadcenter.commons.command.Command;
import net.sadcenter.commons.command.CommandBuilder;
import net.sadcenter.commons.helper.ChatHelper;
import net.sadcenter.guilds.basic.User;
import net.sadcenter.guilds.utils.UserUtil;
import org.apache.commons.lang3.RandomStringUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitScheduler;

/**
 * @author sadcenter on 21.08.2020
 * @project LASTCORE
 */

public class IncognitoCommand extends Command {

    private final Plugin plugin;
    private final BukkitScheduler scheduler;

    public IncognitoCommand(Plugin plugin) {
        super("incognito", true);
        this.plugin = plugin;
        this.scheduler = plugin.getServer().getScheduler();
    }

    @Override
    public void onCommand(CommandSender executor, CommandBuilder commandBuilder, String... args) {
        Player player = (Player) executor;
        User user = UserUtil.get(player.getUniqueId());
        if (user.getIncognito() == null) {
            ChatHelper.sendTitle(player, "", "&aWlaczyles incognito!");
            user.getOrCreateIncognito(RandomStringUtils.randomAlphanumeric(16));
            // packetManager.sendPacket(ChannelType.REGULAR, new SkinPacket(player.getUniqueId(), player.getName(), "ewogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJpZCIgOiAiOTYwZWU1NjJiM2QxNGVlM2JkM2Y0Njc4YTVmMzRkYWIiLAogICAgICAidHlwZSIgOiAiU0tJTiIsCiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzYxZmE4NzA4YjM1YTA2YmRjNWNlNDEyOWQ3YWJlZmExYzkzMGJkMDhlNDQ0MmUyZjFmMDU3NGM4OTJjYzYzOSIsCiAgICAgICJwcm9maWxlSWQiIDogIjdkYTJhYjNhOTNjYTQ4ZWU4MzA0OGFmYzNiODBlNjhlIiwKICAgICAgInRleHR1cmVJZCIgOiAiMzYxZmE4NzA4YjM1YTA2YmRjNWNlNDEyOWQ3YWJlZmExYzkzMGJkMDhlNDQ0MmUyZjFmMDU3NGM4OTJjYzYzOSIKICAgIH0KICB9LAogICJza2luIiA6IHsKICAgICJpZCIgOiAiOTYwZWU1NjJiM2QxNGVlM2JkM2Y0Njc4YTVmMzRkYWIiLAogICAgInR5cGUiIDogIlNLSU4iLAogICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS8zNjFmYTg3MDhiMzVhMDZiZGM1Y2U0MTI5ZDdhYmVmYTFjOTMwYmQwOGU0NDQyZTJmMWYwNTc0Yzg5MmNjNjM5IiwKICAgICJwcm9maWxlSWQiIDogIjdkYTJhYjNhOTNjYTQ4ZWU4MzA0OGFmYzNiODBlNjhlIiwKICAgICJ0ZXh0dXJlSWQiIDogIjM2MWZhODcwOGIzNWEwNmJkYzVjZTQxMjlkN2FiZWZhMWM5MzBiZDA4ZTQ0NDJlMmYxZjA1NzRjODkyY2M2MzkiCiAgfSwKICAiY2FwZSIgOiBudWxsCn0="));
        } else {
            // packetManager.sendPacket(ChannelType.REGULAR, new SkinPacket(player.getUniqueId(), player.getName(), null));
            ChatHelper.sendTitle(player, "", "&cWylaczyles incognito!");
            user.setIncognito(null);
        }
        reshowPlayer((Player) executor);

    }

    private void reshowPlayer(Player reshow) {
        for (final Player p : Bukkit.getOnlinePlayers()) {
            p.hidePlayer(reshow);
            scheduler.runTaskLater(plugin, () -> p.showPlayer(reshow), 3L);
        }
    }
}
