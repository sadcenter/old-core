package net.sadcenter.guilds.basic;

import lombok.Getter;
import lombok.Setter;
import net.sadcenter.commons.helper.ChatHelper;
import net.sadcenter.commons.helper.RandomHelper;
import net.sadcenter.guilds.LastCore;
import net.sadcenter.guilds.basic.fields.*;
import net.sadcenter.guilds.drop.turbodrops.TurboDrop;
import net.sadcenter.guilds.managers.GroupManager;
import net.sadcenter.guilds.utils.GuildUtil;
import net.sadcenter.mongo.helper.DontExport;
import net.sadcenter.mongo.helper.MongoHelper;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

@Getter
@Setter
public final class User implements MongoHelper, Comparable<User> {

    private final UUID id;
    private final String name;
    private final DropField drop;
    private final KitField kitField;
    private final DepositField deposit;
    private final Stats stats;
    private String group;
    private final SessionField firstConnection;
    private final SessionField longestConnection;
    private final SessionField lastConnection;
    private final Set<String> ignores;
    private final Map<UUID, Long> lastKills;
    private final List<PingNotification> pingNotifications;

    private IncognitoField incognito;
    private TurboDrop turboDrop;
    private Location home;
    private ChatOptionsField chatOptions;

    private boolean ignoreAll;
    private String replyPlayer;

    private int points;
    private int kills;
    private int deaths;
    private int assists;
    private int level;
    private int killstreak;
    private int recordKillstreak;
    @DontExport
    private boolean randomTeleport;
    @DontExport
    private boolean blockedHits;
    @DontExport
    private long slow;
    @DontExport
    private boolean god;
    @DontExport
    private int macro;
    @DontExport
    private boolean blockHits;


    public User(UUID id) {
        this.id = id;
        this.name = Bukkit.getPlayer(id).getName();
        this.points = 1000;
        this.chatOptions = new ChatOptionsField(true, true, true, true);
        this.kills = 0;
        this.deaths = 0;
        this.assists = 0;
        this.level = 1;
        this.replyPlayer = "null";
        this.home = null;
        this.macro = 0;
        this.ignores = new HashSet<>();
        this.pingNotifications = new ArrayList<>();
        this.drop = new DropField();
        this.slow = System.currentTimeMillis();
        long current = System.currentTimeMillis();
        this.group = GroupManager.getDeafultGroup().getName();
        this.kitField = new KitField(current, current, current, current);
        this.ignoreAll = false;
        this.stats = new Stats();
        this.turboDrop = null;
        this.deposit = new DepositField(0, 0, 0, 0, 0, 0, 0, 0);
        this.longestConnection = new SessionField();
        this.lastKills = new HashMap<>();
        this.firstConnection = new SessionField();
        this.incognito = null;
        this.lastConnection = new SessionField();
        this.god = false;
    }


    public @Nullable Guild getGuild() {
        return GuildUtil.getFromPlayer(name);
    }

    public void addPoints(final int points) {
        this.points += points;
        if (this.points < 1000) {
            removePoints(1);
            return;
        }
        if (this.points >= 2000) {
            level++;
            this.points = 1000;
            ChatHelper.sendTitle(Bukkit.getPlayer(getId()), "", "&aAwansowales do " + level + " LVL");
        }

    }

    public void blockHitsTo(int seconds) {
        this.blockedHits = true;
        Bukkit.getScheduler().runTaskLater(LastCore.getInstance(), () -> this.blockedHits = false, 20L * seconds);
    }

    public void addClick(int toAdd) {
        this.macro += toAdd;
    }

    public IncognitoField getOrCreateIncognito(String random) {
        if (incognito == null)
            incognito = new IncognitoField(Bukkit.getPlayer(id), random);
        return incognito;
    }

    public void addKills(final int kills) {
        this.kills += kills;
    }

    public void removePoints(final int points) {
        this.points -= points;
        if (this.points < 1000) {
            if (level == 1) {
                return;
            }
            level--;
            this.points = RandomHelper.nextInt(1500, 1700);
            ChatHelper.sendTitle(Bukkit.getPlayer(getId()), "", "&cTwoj lvl zostal zgredowany do " + level + " LVL");
        }
    }

    public Long getLastKill(UUID uuid) {
        return this.lastKills.get(uuid);
    }


    public void addDeaths(final int deaths) {
        this.deaths += deaths;
    }

    public void addKillstreak() {
        this.killstreak++;
    }

    public void addAssists(final int assists) {
        this.assists += assists;
    }

    @Override
    public int compareTo(@NotNull User o) {
        int compare = Integer.compare(points + (level * 2000), o.getPoints() + (o.getLevel() * 2000));
        if (compare == 0) {
            compare = name.compareTo(o.getName()); // porownywanie nazwy gracza, potrzebne bo inaczej sie wyjebie gdy te 2 wartosci porownywane wyzej beda takie same
        }
        return compare;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return name.equals(user.name);
    }

    @Nullable
    public Player getPlayer() {
        return Bukkit.getPlayer(id);
    }

}