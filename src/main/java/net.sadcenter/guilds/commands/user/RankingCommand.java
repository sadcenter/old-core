package net.sadcenter.guilds.commands.user;

import net.sadcenter.commons.command.Command;
import net.sadcenter.commons.command.CommandBuilder;
import net.sadcenter.commons.helper.ChatHelper;
import net.sadcenter.guilds.basic.User;
import net.sadcenter.guilds.utils.UserUtil;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RankingCommand extends Command {

    public RankingCommand() {
        super("gracz", new String[]{"statystyki", "ranking"}, false);
    }

    @Override
    public void onCommand(CommandSender executor, CommandBuilder commandBuilder, String... args) {
        User u;
        if (args.length == 0)
            u = UserUtil.get(((Player) executor).getUniqueId());
        else
            u = UserUtil.get(args[0]);

        if (u == null) {
            ChatHelper.sendMessage(executor, "&CTaki gracz nie istnieje w bazie danych!");
        } else {
            ChatHelper.sendMessage(executor, " ");
            ChatHelper.sendMessage(executor, " &aRanking " + u.getPoints());
            ChatHelper.sendMessage(executor, " &aZabojstwa " + u.getKills());
            ChatHelper.sendMessage(executor, " &aSmierci " + u.getDeaths());
            ChatHelper.sendMessage(executor, " &aAsysty " + u.getAssists());
            ChatHelper.sendMessage(executor, " &aLVL " + u.getLevel());
            //ChatHelper.sendMessage(player, "&8>> &7Miejsce w rankingu: &3" + sortUtil.getUser(u));
            ChatHelper.sendMessage(executor, " ");
        }
    }
}
