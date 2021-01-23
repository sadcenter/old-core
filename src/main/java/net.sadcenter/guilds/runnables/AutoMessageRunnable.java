package net.sadcenter.guilds.runnables;

import net.sadcenter.commons.helper.ChatHelper;
import net.sadcenter.guilds.utils.UserUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

/**
 * @author sadcenter on 06.09.2020
 * @project LASTCORE
 */

public final class AutoMessageRunnable implements Runnable {

    private final List<String> strings = Arrays.asList("&7Nasza strona &3&lLAST&F&LCORE&7.PL", "&7Koniecznie zobacz /pomoc");

    private int index = 0;

    @Override
    public void run() {
        if (index == strings.size()) {
            index = 0;
        }
        String message = ChatHelper.fixColor("&3&L&3&lLAST&F&LCORE&7.pl &8- " + this.strings.get(index));
        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            if (UserUtil.get(onlinePlayer.getUniqueId()).getChatOptions().isAutoMessages())
                ChatHelper.sendMessage(onlinePlayer, message);
        }
        index++;
    }
}
