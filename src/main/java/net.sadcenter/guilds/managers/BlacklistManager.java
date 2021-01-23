package net.sadcenter.guilds.managers;

import com.mongodb.DBCollection;
import lombok.RequiredArgsConstructor;
import net.sadcenter.guilds.LastCore;
import net.sadcenter.guilds.basic.Blacklist;
import net.sadcenter.guilds.utils.JsonStorage;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.net.InetAddress;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author sadcenter on 10.09.2020
 * @project LASTCORE
 */
public final class BlacklistManager {

    private final Map<UUID, Blacklist> blacklistMap = new ConcurrentHashMap<>();
    private final DBCollection mongoCollection = LastCore.getInstance().getMongoDatabase().getCollection("blacklists");

    @NotNull
    public Optional<Blacklist> getBlacklist(UUID uuid) {
        if (!blacklistMap.containsKey(uuid))
            return Optional.empty();
        else
            return Optional.of(blacklistMap.get(uuid));
    }

    @NotNull
    public Optional<Blacklist> getBlacklist(String nick) {
        return blacklistMap.values().stream().filter(blacklist -> blacklist.getNick().equals(nick)).findFirst();
    }

    @NotNull
    public Optional<Blacklist> getBlacklist(InetAddress socketAddress) {
        return blacklistMap.values().stream().filter(blacklist -> blacklist.getIp().equals(socketAddress)).findFirst();
    }

    public void registerBlacklist(Blacklist blacklist) {
        blacklistMap.put(blacklist.getUuid(), blacklist);
        Player player = Bukkit.getPlayer(blacklist.getUuid());
        mongoCollection.insert(blacklist.getDBObject(blacklist.getClass()));
        if (player != null)
            player.kickPlayer("przejebales sobie kolego");
    }

    public Map<UUID, Blacklist> getBlackLists() {
        return blacklistMap;
    }

    public void removeBlacklist(UUID uuid) {
        Blacklist blacklist = getBlacklist(uuid).get();
        this.mongoCollection.remove(blacklist.getDBObject(blacklist.getClass()));
        this.blacklistMap.remove(uuid);
    }

    public void removeBlacklist(String nick) {
        Blacklist blacklist = getBlacklist(nick).get();
        this.mongoCollection.remove(blacklist.getDBObject(blacklist.getClass()));
        this.blacklistMap.remove(blacklist.getUuid());
    }

    public void removeBlacklist(InetAddress address) {
        Blacklist blacklist = getBlacklist(address).get();
        this.mongoCollection.remove(blacklist.getDBObject(blacklist.getClass()));
        this.blacklistMap.remove(blacklist.getUuid());
    }

    public void loadBlacklists() {
        mongoCollection.find().forEach(document -> {
            Blacklist blacklist = JsonStorage.GSON.fromJson(document.toString(), Blacklist.class);
            blacklistMap.put(blacklist.getUuid(), blacklist);
        });
        LastCore.getInstance().getLogger().info("Loaded " + blacklistMap.size() + " blacklists!");
    }
}
