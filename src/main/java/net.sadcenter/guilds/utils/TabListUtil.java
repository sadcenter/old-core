package net.sadcenter.guilds.utils;

import com.keenant.tabbed.Tabbed;
import com.keenant.tabbed.item.TextTabItem;
import com.keenant.tabbed.tablist.SimpleTabList;
import com.keenant.tabbed.util.Skins;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.sadcenter.commons.helper.ChatHelper;
import net.sadcenter.guilds.basic.Guild;
import net.sadcenter.guilds.basic.User;
import net.sadcenter.guilds.managers.TopManager;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.*;

/**
 * @author sadcenter on 26.09.2020
 * @project LASTCORE
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TabListUtil {

    //private final static Skin SKIN = Skins.getPlayer(UUID.fromString("49238b4a-9f12-4498-9d35-406114dcc088"));

    public static void init(@NotNull Player p, int online, Tabbed instance, TopManager topManager) {
        User u = UserUtil.get(p.getUniqueId());
        Guild g = GuildUtil.getFromPlayer(p.getUniqueId());
        Map<Integer, String> tabList = new HashMap<Integer, String>() {
            {
                put(0, " ");
                put(1, ChatHelper.fixColor(" &3&lTOPOWI GRACZE"));
                put(2, " ");
                int userTop = 0;
                for (int i = 3; i < 19; i++) {
                    userTop++;
                    final User sortedUser = topManager.getUser(userTop-1);
                    if (sortedUser == null) {
                        put(i, ChatHelper.fixColor("&7" + userTop + ". &3" + "BRAK"));
                    } else {
                        put(i, ChatHelper.fixColor("&7" + userTop + ". &3" + sortedUser.getName() + " &7[&7" + sortedUser.getPoints() + "&7]"));
                    }
                }
                put(19, " ");
                put(20, " ");
                put(21, ChatHelper.fixColor(" &3&lTOPOWE GILDIE"));
                put(22, " ");
                int topGuild = 0;
                for (int i = 23; i < 39; i++) {
                    topGuild++;
                    final Guild sortedGuild = topManager.getGuild(topGuild-1);
                    if (sortedGuild == null) {
                        put(i, ChatHelper.fixColor("&7" + topGuild + ". &3" + "BRAK"));
                    } else {
                        put(i, ChatHelper.fixColor("&7" + topGuild + ". &3" + sortedGuild.getTag() + " &7[&7" + sortedGuild.getPoints() + "&7]"));
                    }
                }
                put(39, " ");
                put(40, " ");
                put(41, ChatHelper.fixColor(" &3&lTWOJE STATYSTYKI"));
                put(42, " ");
                put(43, ChatHelper.fixColor("&7Punkty: &3" + u.getPoints()));
                put(44, ChatHelper.fixColor("&7Zabojstwa: &3" + u.getKills()));
                put(45, ChatHelper.fixColor("&7Dedy: &3" + u.getDeaths()));
                final int deaths = (u.getDeaths() == 0 ? 1 : u.getDeaths());
                put(46, ChatHelper.fixColor("&7K/D: &3" + u.getKills() / deaths));
                put(47, ChatHelper.fixColor("&7KS: &3" + u.getKillstreak()));
                put(48, ChatHelper.fixColor("&7Rekord KS: &3" + u.getRecordKillstreak()));
                put(49, " ");
                put(50, ChatHelper.fixColor(" &3&lSTATYSTYKI GILDII"));
                put(51, " ");
                put(52, ChatHelper.fixColor("&7Tag: &3" + (g == null ? "brak" : g.getTag())));
                put(53, ChatHelper.fixColor("&7Zabojstwa: &3" + (g == null ? "brak" : g.getKills())));
                put(54, ChatHelper.fixColor("&7Dedy: &3" + (g == null ? "brak" : g.getDeaths())));
                final int gDeaths = (g == null ? 0 : (g.getDeaths() == 0 ? 1 : g.getDeaths()));
                put(55, ChatHelper.fixColor("&7K/D: &3" + (g == null ? "brak" : u.getKills() / gDeaths)));
                put(56, ChatHelper.fixColor("&7Lider: &3" + (g == null ? "brak" : g.getCreator())));
                put(57, ChatHelper.fixColor("&7PvP: &3" + (g == null ? "brak" : (g.isPvp() ? "&aon" : "&coff"))));
                put(58, ChatHelper.fixColor("&7Limit os: &325"));
                put(59, " ");
                put(60, " ");
                put(61, ChatHelper.fixColor(" &3&lINFORMACJE"));
                put(62, " ");
                put(63, ChatHelper.fixColor("&7Nick: &3" + p.getName()));
                put(64, ChatHelper.fixColor("&7Ping: " + PingUtil.formatPing(p)));
                put(65, ChatHelper.fixColor("&7Online: &3" + online));
                put(66, ChatHelper.fixColor(" "));
                put(67, ChatHelper.fixColor(" &3&lMEDIA"));
                put(68, ChatHelper.fixColor(" "));
                put(69, ChatHelper.fixColor("&7fanpage: &3fb.LastCore.pl"));
                put(70, ChatHelper.fixColor("&7discord: &3dc.LastCore.pl"));
                put(71, ChatHelper.fixColor("&7ts: &3ts.LastCore.pl"));
                put(72, ChatHelper.fixColor(" "));
                put(73, ChatHelper.fixColor(" &3&lKOMENDY:"));
                put(74, ChatHelper.fixColor("&7Najwazniejsze komendy"));
                put(75, ChatHelper.fixColor(" &3/pomoc"));
                put(76, ChatHelper.fixColor("&7Resetowanie rankingu"));
                put(77, ChatHelper.fixColor(" &3/resetujranking"));
                put(78, ChatHelper.fixColor("&7Zmiana ustawien czatu"));
                put(79, ChatHelper.fixColor(" &3/cc"));
                put(80, ChatHelper.fixColor(" "));
            }
        };
        instance.destroyTabList(p);
        SimpleTabList tab = instance.newSimpleTabList(p);

        tab.setHeader(ChatHelper.fixColor("&3&l&3&lLAST&f&lCORE&7&L.pl"));
        tab.setFooter(ChatHelper.fixColor("&7&lPolub nas na naszym facebooku! &3&lfb.com/&3&lLAST&f&lCORE&7pl \n &7&lDolacz do naszego discorda! &3&ldc.&3&lLAST&f&lCORE&7.pl"));
        tabList.forEach((integer, s) -> {
            TextTabItem text = new TextTabItem(s);
            text.setPing(1);
            text.setSkin(Skins.BLOCK_LOG);
            tab.set(integer, text);
        });
        tab.update();
    }

}
