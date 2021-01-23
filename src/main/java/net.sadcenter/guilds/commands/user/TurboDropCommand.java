package net.sadcenter.guilds.commands.user;

import net.sadcenter.commons.command.Command;
import net.sadcenter.commons.command.CommandBuilder;
import net.sadcenter.commons.helper.ChatHelper;
import net.sadcenter.guilds.LastCore;
import net.sadcenter.guilds.basic.Guild;
import net.sadcenter.guilds.basic.User;
import net.sadcenter.guilds.drop.turbodrops.TurboDrop;
import net.sadcenter.guilds.managers.UserManager;
import net.sadcenter.guilds.utils.DateUtil;
import net.sadcenter.guilds.utils.GuildUtil;
import net.sadcenter.guilds.utils.UserUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

/**
 * @author sadcenter on 03.09.2020
 * @project LASTCORE
 */

public class TurboDropCommand extends Command {

    private final UserManager manager;

    public TurboDropCommand(UserManager manager) {
        super("turbodrop", "core.cmd.turbodrop", false);
        this.manager = manager;
    }

    @Override
    public void onCommand(CommandSender executor, CommandBuilder commandBuilder, String... args) {
        final String type = args[0];

        switch (type) {
            case "server": {
                if (args.length != 2) {
                    ChatHelper.sendMessage(executor, "&cZle uzycie!");
                    return;
                }
                long date = DateUtil.getTime(args[1]);
                TurboDrop turboDrop = new TurboDrop(System.currentTimeMillis() + date);
                manager.getUsers().values().forEach(user -> user.setTurboDrop(turboDrop));
                LastCore.getInstance().getConfiguration().getStorage().setTurboDropServer(turboDrop);
                ChatHelper.sendTitle(Bukkit.getOnlinePlayers(), "", "&7TurboDrop zostal aktywowany! (&3" + DateUtil.getDurationBreakdownShort(date) + "&7)");
                return;
            }
            case "user": {
                if (args.length != 3) {
                    ChatHelper.sendMessage(executor, "&cZle uzycie!");
                    return;
                }
                final String value = args[1];
                final long date = DateUtil.getTime(args[2]);
                final User u = UserUtil.get(value);
                if (u == null) {
                    ChatHelper.sendMessage(executor, "&cTaki gracz nie byl na serwerze");
                    return;
                }
                u.setTurboDrop(new TurboDrop(date + System.currentTimeMillis()));
                ChatHelper.sendMessage(executor, "&aAktywowales turbodrop dla " + u.getName() + " na " + args[2]);
                return;
            }

            case "guild": {
                if (args.length != 3) {
                    ChatHelper.sendMessage(executor, "&cZle uzycie!");
                    return;
                }
                final String value = args[1];
                final long date = DateUtil.getTime(args[2]);
                final Guild g = GuildUtil.get(value);
                if (g == null) {
                    ChatHelper.sendMessage(executor, "&cTaka gildia nie istnieje");
                    return;
                }
                g.setTurboDrop(new TurboDrop(date + System.currentTimeMillis()));
                ChatHelper.sendMessage(executor, "&aAktywowales turbodrop dla " + g.getTag() + " na " + args[2]);
            }
        }
    }
}
