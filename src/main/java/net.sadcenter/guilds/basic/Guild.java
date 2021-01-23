package net.sadcenter.guilds.basic;

import lombok.Getter;
import lombok.Setter;
import net.sadcenter.guilds.basic.fields.BlockField;
import net.sadcenter.guilds.basic.fields.ChestProtection;
import net.sadcenter.guilds.drop.turbodrops.TurboDrop;
import net.sadcenter.guilds.utils.Worlds;
import net.sadcenter.mongo.helper.DontExport;
import net.sadcenter.mongo.helper.MongoHelper;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Getter
@Setter
public final class Guild implements MongoHelper, Comparable<Guild> {

    private final String tag;
    private final String name;
    private final Region region;
    private final Set<String> invites;
    private final Map<String, Member> members;
    private final Set<ChestProtection> chests;
    private final Set<String> alliances;
    private TurboDrop turboDrop;
    private List<BlockField> explodedBlocks;
    private String creator;
    private String leader;
    private int assists;
    private int points;
    private int kills;
    private int deaths;
    private int life;
    private int hearts;
    @DontExport
    private boolean pvp;
    @DontExport
    private boolean isRegenerating;
    private int depositBlocks;
    private Location home;
    private long expire;
    private long protection;
    @DontExport
    private long blockingPlace;
    private GuildGlass glass;

    public Guild(final @NotNull Player player, final String name, final String tag) {
        this.tag = tag;
        this.name = name;
        this.region = new Region(player.getLocation().getBlockX(), player.getLocation().getBlockZ(), 15);
        this.creator = player.getName();
        this.protection = System.currentTimeMillis() + TimeUnit.DAYS.toMillis(1);
        this.assists = 0;
        this.points = 1000;
        this.alliances = new HashSet<>();
        this.home = Worlds.DEAFULT_WORLD.getHighestBlockAt(region.getLocation()).getLocation();
        this.leader = "null";
        this.invites = new HashSet<>();
        this.members = new ConcurrentHashMap<>();
        this.turboDrop = null;
        this.chests = new HashSet<>();
        this.explodedBlocks = new ArrayList<>();
        this.depositBlocks = 0;
        this.kills = 0;
        this.isRegenerating = false;
        this.deaths = 0;
        this.life = 3;
        this.hearts = 500;
        this.expire = System.currentTimeMillis() + TimeUnit.DAYS.toMillis(2);
        this.glass = GuildGlass.WHITE_GLASS;
    }

    public void addDeposit(int index) {
        this.depositBlocks += index;
    }

    public void removeDeposit(int index) {
        this.depositBlocks -= index;
    }

    public void removePoints(final int points) {
        this.points -= points;
    }


    public void addSize(int size) {
        this.getRegion().addSize(size);
    }


    public void addMember(final @NotNull Member member) {
        members.put(member.getName(), member);
    }


    public boolean isMember(final String name) {
        return this.members.containsKey(name);
    }


    public void prolongGuild() {
        this.expire += TimeUnit.DAYS.toMillis(1);
    }


    @NotNull
    public Set<Player> getOnlinePlayers() {
        return members.values().stream().map(member -> Bukkit.getPlayer(member.getUuid())).filter(Objects::nonNull).collect(Collectors.toSet());
    }


    public void addKills(final int kills) {
        this.kills += kills;
    }

    public void addDeaths(final int deaths) {
        this.deaths += deaths;
    }

    public void addPoints(final int points) {
        this.points += points;
    }

    public void addAssists(final int assists) {
        this.assists += assists;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Guild guild = (Guild) o;
        return tag.equals(guild.getTag());
    }

    @Override
    public int hashCode() {
        return Objects.hash(region, invites, members, alliances, turboDrop, explodedBlocks, creator, leader, assists, points, kills, deaths, life, hearts, pvp, isRegenerating, depositBlocks, home, expire, protection, blockingPlace, glass);
    }

    @Override
    public int compareTo(@NotNull Guild o) {
        int compare = Integer.compare(points, o.getPoints());
        if (compare == 0) {
            compare = tag.compareTo(o.getTag()); // porownywanie nazwy gracza, potrzebne bo inaczej sie wyjebie gdy te 2 wartosci porownywane wyzej beda takie same
        }
        return compare;
    }
}
