package net.sadcenter.guilds.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.sadcenter.commons.helper.ChatHelper;
import net.sadcenter.guilds.basic.Guild;
import net.sadcenter.guilds.basic.User;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * @author sadcenter on 21.08.2020
 * @project LASTCORE
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class NameTagUtil {


    private static String rank(Player p) {
        String rank = Objects.requireNonNull(PermissionUtil.getUser(p)).getPrimaryGroup().toUpperCase();
        if (rank.equalsIgnoreCase("vip")) {
            rank = "&3" + rank;
        } else if (rank.equalsIgnoreCase("svip")) {
            rank = "&3" + rank;
        } else if (rank.equalsIgnoreCase("helper")) {
            rank = "&b&l" + rank;
        } else if (rank.equalsIgnoreCase("admin")) {
            rank = "&c&l" + rank;
        } else if (rank.equalsIgnoreCase("h@")) {
            rank = "&4&l" + rank;
        } else if (rank.equalsIgnoreCase("root")) {
            rank = "&4&l" + rank;
        }
        return rank;
    }

    public static void init(@NotNull Player p) {
        Scoreboard sb = Bukkit.getScoreboardManager().getNewScoreboard();
        User u = UserUtil.get(p.getUniqueId());
        Objective objective = sb.getObjective("points");
        p.setScoreboard(sb);
        if (objective == null || !objective.getName().equals("points")) {
            objective = sb.registerNewObjective("points", "dummy");
            objective.setDisplaySlot(DisplaySlot.BELOW_NAME);
            objective.setDisplayName("rankingu, " + u.getLevel() + "lvl");
        }

        for (Player player : Bukkit.getOnlinePlayers()) {
            User user = UserUtil.get(player.getUniqueId());

            if (user == null) {
                continue;
            }
            objective.getScore(user.getName()).setScore(user.getPoints());
            Guild g = GuildUtil.getFromPlayer(p.getUniqueId());


            Team t = sb.getTeam(player.getName());
            if (t == null) {
                t = sb.registerNewTeam(player.getName());
            }


            Guild playerGuild = GuildUtil.getFromPlayer(player.getUniqueId());
            net.luckperms.api.model.user.User lUser = PermissionUtil.getUser(player);
            String prefix = PermissionUtil.getGroup(lUser.getPrimaryGroup()).getCachedData().getMetaData().getSuffix();

            if (playerGuild != null) {
                if (g == null) {
                    t.setPrefix(ChatHelper.fixColor("&c[" + playerGuild.getTag() + "] "));
                } else if (g.getAlliances().contains(playerGuild.getTag())) {
                    t.setPrefix(ChatHelper.fixColor("&3[" + playerGuild.getTag() + "] "));
                } else if (g.getTag().equalsIgnoreCase(playerGuild.getTag())) {
                    t.setPrefix(ChatHelper.fixColor("&a[" + playerGuild.getTag() + "] "));
                } else {
                    t.setPrefix(ChatHelper.fixColor("&c[" + playerGuild.getTag() + "] "));
                }
            }
            //(led == null ? "" : led)
            t.setSuffix(ChatHelper.fixColor(" " + prefix));


        p.getScoreboard().getTeam(player.getName()).addEntry(player.getName());
        if (user.getIncognito() != null)
            p.getScoreboard().getTeam(player.getName()).addEntry(user.getIncognito().getIncognito());
    }
    // t.addPlayer(p);
}

}


    /*public static void init(@NotNull Player p) {
        Scoreboard sb = Bukkit.getScoreboardManager().getNewScoreboard();
        User u = UserUtil.get(p.getUniqueId());
        Objective objective = sb.getObjective("points");

        if (objective == null || ! objective.getName().equals("points")) {
            objective = sb.registerNewObjective("points", "dummy");
            objective.setDisplaySlot(DisplaySlot.BELOW_NAME);
            objective.setDisplayName(ChatHelper.fixColor("&7rankingu"));
        }

        for (Player player : Bukkit.getOnlinePlayers()) {
            User user = UserUtil.get(player.getUniqueId());

            if (user == null) {
                continue;
            }

            objective.getScore(user.getName()).setScore(user.getPoints());
        }
        Guild g = GuildUtil.getFromPlayer(p.getUniqueId());


        for (Player a : Bukkit.getOnlinePlayers()) {
            User user = UserUtil.get(a.getUniqueId());
            String prefix = "&7[" + rank(a) + "&7]&7";
            String suffix = " &3" + user.getLevel() + "lvl";
            Team t = sb.getTeam(a.getName());
            if (t == null) {
                t = sb.registerNewTeam(a.getName());
            }
            AtomicReference<String> atomicReference = new AtomicReference<>(null);
                new Thread(() -> {
                    Guild o = GuildUtil.getFromPlayer(a.getUniqueId());
                    if (o == null)
                        return;
                    if (g == null) {
                        atomicReference.set("&C[" + o.getTag() + "] ");

                    } else if (g.getTag().equalsIgnoreCase(o.getTag())) {
                        atomicReference.set("&a[" + o.getTag() + "] ");
                    } else {
                        atomicReference.set("&C[" + o.getTag() + "] ");
                    }
                }).start();
            t.setPrefix(ChatHelper.fixColor(atomicReference.get() == null ? "" : atomicReference.get() + " "));
            //t.setSuffix(ChatHelper.fixColor(suffix));
        }

        p.setScoreboard(sb);
        for (Player online : Bukkit.getOnlinePlayers()) {
            User u1 = UserUtil.get(online.getUniqueId());
            Team sbbb = p.getScoreboard().getTeam(u1.getName());
            sbbb.addEntry(u.getIncognito().getIncognito());
            sbbb.addEntry(u.getName());
            Team sbb = p.getScoreboard().getTeam(u1.getName());
            sbb.addEntry(u1.getIncognito().getIncognito());
            sbb.addEntry(u1.getName());
        }
        // t.addPlayer(p);
    }

     */


