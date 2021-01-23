package net.sadcenter.guilds.listeners;


import lombok.RequiredArgsConstructor;
import net.sadcenter.commons.helper.ChatHelper;
import net.sadcenter.commons.helper.ItemHelper;
import net.sadcenter.commons.helper.RandomHelper;
import net.sadcenter.guilds.basic.Guild;
import net.sadcenter.guilds.basic.User;
import net.sadcenter.guilds.managers.AntyLogoutManager;
import net.sadcenter.guilds.utils.*;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
public class PlayerDeathListener implements Listener {

    private final AntyLogoutManager manager;
    private final Plugin plugin;

    @EventHandler
    public void onDeath(final PlayerDeathEvent event) {
        event.setDeathMessage(null);
        final Player killer = event.getEntity().getKiller();
        if (killer == null) {
            return;
        }
        final Player damager = event.getEntity();

        event.getDrops().clear();
        manager.getMap().invalidate(damager.getUniqueId());

        plugin.getServer().getScheduler().runTaskAsynchronously(plugin, () -> {
            World world = killer.getWorld();
            world.strikeLightningEffect(damager.getLocation());
        });
        plugin.getServer().getScheduler().runTaskLater(plugin, () -> {
            if (damager.isInsideVehicle()) {
                damager.leaveVehicle();
            }
            damager.spigot().respawn();
            ItemHelper.giveOrDrop(damager, new ItemStack(Material.STONE_PICKAXE), damager.getLocation());
            ItemHelper.giveOrDrop(damager, new ItemStack(Material.ENDER_CHEST), damager.getLocation());
            ItemHelper.giveOrDrop(damager, new ItemStack(Material.COOKED_BEEF, 64), damager.getLocation());
        }, 5L);


        final User userDamager = UserUtil.get(damager.getUniqueId());
        final User userKiller = UserUtil.get(killer.getUniqueId());
        killer.getInventory().addItem(ItemUtil.getSkull(damager.getName()));
        for (ItemStack drop : damager.getInventory().getContents()) {
            if (drop == null || drop.getType() == Material.AIR) continue;
            ItemHelper.giveOrDrop(killer, drop, killer.getLocation());
        }
        event.setKeepInventory(false);
        damager.getInventory().clear();

        Long last = userKiller.getLastKill(damager.getUniqueId());
        if (last != null) {
            if (last < System.currentTimeMillis()) {
                userKiller.getLastKills().remove(damager.getUniqueId());
            } else {
                ChatHelper.sendTitle(killer, "", "&cZabiles juz tego gracza &8(&3" + DateUtil.getDurationBreakdownShort(last - System.currentTimeMillis()) + "&8)");
                return;
            }
        }


        if (Objects.equals(damager.getAddress(), killer.getAddress())) {
            ChatHelper.sendTitle(killer, "", "&CTen gracz posiada takie same IP!");
            return;
        }


        int add = (int) (RandomHelper.nextInt(32, 48) + (userKiller.getPoints() - userDamager.getPoints()) * -0.10);
        if (add < 0)
            add = 1;

        final int remove = (add / 4) * userKiller.getLevel();
        userDamager.setKillstreak(0);
        userKiller.addKillstreak();
        if (userKiller.getKillstreak() > userKiller.getRecordKillstreak())
            userKiller.setRecordKillstreak(userKiller.getKillstreak());

        userKiller.getLastKills().put(damager.getUniqueId(), System.currentTimeMillis() + TimeUnit.HOURS.toMillis(2));
        ChatHelper.sendTitle(killer, "&3&l" + KillstreakUtil.getKillStreak(userKiller.getKillstreak()), "&3" + damager.getName() + " &7+&3" + add + " &8(&c" + damager.getLastDamage() + "dmg&8)");
        ChatHelper.sendTitle(damager, "&3&lSMIERC", "&3" + killer.getName() + " &7-&3" + remove + " &8(&c" + killer.getHealth() + "❤&8, &c" + killer.getLastDamage() + "dmg&8)");
        userKiller.addPoints(add);
        userKiller.addKills(1);
        userDamager.addDeaths(1);
        userDamager.removePoints(remove);
        int addGuild = add / 2;
        int removeGuild = remove / 2;
        final Guild g = GuildUtil.getFromPlayer(killer.getName());
        final Guild o = GuildUtil.getFromPlayer(damager.getName());
        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            User u = UserUtil.get(onlinePlayer.getUniqueId());
            if (u.getChatOptions().isDeathMessages())
                onlinePlayer.sendMessage(ChatHelper.fixColor((g == null ? "" : "&7[&3" + g.getTag() + " &a+&7" + addGuild + " &7] ") + "&3" + killer.getName() + " &8(&a+&7" + add + ", &7" + killer.getHealth() + "&c❤&7, &7" + damager.getLastDamage() + "dmg&8) &7zabil gracza" + (o == null ? " " : " &7[&3" + o.getTag() + " &c-&7" + removeGuild + "&7] ") + "&3" + damager.getName() + " &8(&c-&7" + remove + ", &7" + killer.getLastDamage() + "dmg&8)"));
        }
        if (g != null) {
            if (addGuild <= 0)
                addGuild = 1;
            g.addPoints(addGuild);
            g.addKills(1);
        }

        if (o != null) {
            if (removeGuild <= 0)
                removeGuild = 1;
            o.removePoints(removeGuild);
            o.addDeaths(1);
        }
    }
}
