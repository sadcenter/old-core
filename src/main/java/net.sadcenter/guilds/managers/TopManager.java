package net.sadcenter.guilds.managers;

import com.google.common.collect.Iterables;
import net.sadcenter.guilds.basic.Guild;
import net.sadcenter.guilds.basic.User;
import org.bukkit.Material;
import org.bukkit.Statistic;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.jetbrains.annotations.NotNull;

import java.util.*;

/**
 * @author sadcenter on 16.08.2020
 * @project LASTCORE
 */
public final class TopManager {

    private final Set<User> sortedUsers;
    private final Set<Guild> sortedGuilds;

    public TopManager(Collection<User> users, Collection<Guild> guilds) {
        sortedUsers = new TreeSet<>(Collections.reverseOrder());
        sortedGuilds = new TreeSet<>(Collections.reverseOrder());
        sort(users, guilds, true);
    }


    public void sort(@NotNull Collection<User> users, @NotNull Collection<Guild> guilds, boolean replace) {
        if (replace) {
            sortedUsers.clear();
            sortedUsers.addAll(users);

            sortedGuilds.clear();
            sortedGuilds.addAll(guilds);
        }
    }

  

    @NotNull
    public Set<User> sortedUsers() {
        return sortedUsers;
    }

    @NotNull
    public Set<Guild> sortedGuilds() {
        return sortedGuilds;
    }

    public int positionOfGuild(@NotNull Guild guild) {
        return Iterables.indexOf(sortedGuilds, guild::equals);
    }

    public int positionOfUser(@NotNull User user) {
        return Iterables.indexOf(sortedUsers, user::equals);
    }

    public User getUser(int index) {
        return Iterables.get(sortedUsers, index, null);
    }

    public Guild getGuild(int index) {
        return Iterables.get(sortedGuilds, index, null);
    }

    @NotNull
    public ArrayList<Player> sortedStat(@NotNull Statistic statistic) {
        return new ArrayList<>();
    }
}
