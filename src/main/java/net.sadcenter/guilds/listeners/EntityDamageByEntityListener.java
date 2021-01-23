package net.sadcenter.guilds.listeners;

import lombok.RequiredArgsConstructor;
import net.sadcenter.commons.helper.ChatHelper;
import net.sadcenter.guilds.LastCore;
import net.sadcenter.guilds.basic.AntyLogout;
import net.sadcenter.guilds.basic.Guild;
import net.sadcenter.guilds.basic.User;
import net.sadcenter.guilds.config.impl.storage.AntyLogoutStorage;
import net.sadcenter.guilds.managers.AntyLogoutManager;
import net.sadcenter.guilds.managers.ProtectionManager;
import net.sadcenter.guilds.storage.Settings;
import net.sadcenter.guilds.utils.GuildUtil;
import net.sadcenter.guilds.utils.UserUtil;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.ItemStack;

import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
public class EntityDamageByEntityListener implements Listener {

    private final AntyLogoutManager manager;
    private final AntyLogoutStorage storage;
    private final ProtectionManager protectionManager;

    @EventHandler
    public void onAttack(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player && event.getEntity() instanceof Player) {
            Player p = (Player) event.getDamager();
           /* try {
                ProtocolLibrary.getProtocolManager().sendServerPacket(p, PacketContainer.fromPacket(new PacketPlayOutCombatEvent(new CombatTracker((EntityLiving) event.getDamager()), PacketPlayOutCombatEvent.EnumCombatEventType.ENTER_COMBAT)));
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }

            */
            ItemStack itemStack = p.getItemInHand();
            p.setItemInHand(null);
            p.setItemInHand(itemStack);
            p.updateInventory();
        }
    }


    @EventHandler
    public void onDamage(final EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player) {
            final Player p = (Player) event.getEntity();
            final Player o = (Player) event.getDamager();

            User user = UserUtil.get(p.getUniqueId());
            user.addClick(1);
            if (user.getMacro() > 15) {
                event.setCancelled(true);
                event.setDamage(0);
                ChatHelper.sendMessage(o, "&cZablokowano hit!");
                return;
            }
            if (user.isBlockedHits()) {
                event.setCancelled(true);
                ChatHelper.sendMessage(o, "&cZablokowano hit!");
                return;
            }
            if (protectionManager.getProtections().containsKey(p.getUniqueId()) || protectionManager.getProtections().containsKey(o.getUniqueId())) {
                event.setCancelled(true);
                return;
            }
            final Guild g = GuildUtil.getFromPlayer(p.getName());

            if (g != null && g.isMember(o.getName()) && !g.isPvp()) {
                event.setCancelled(true);
                event.setDamage(0.0);
            }

        }
    }

    @EventHandler
    public void onDamage(EntityDamageEvent damageEvent) {
        if (damageEvent.getEntity() instanceof Player) {
            Player player = (Player) damageEvent.getEntity();
            if (UserUtil.get(player.getUniqueId()).isGod()) {
                damageEvent.setCancelled(true);
            }
        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onDamagee(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player && event.getEntity() instanceof Player) {
            Bukkit.getScheduler().runTaskLaterAsynchronously(LastCore.getInstance(), () -> {
                Player damager = (Player) event.getDamager();
                Player attacker = (Player) event.getEntity();

                if (event.isCancelled())
                    return;

                manager.create(new AntyLogout(damager.getUniqueId(), System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(storage.getSeconds())));
                manager.create(new AntyLogout(attacker.getUniqueId(), System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(storage.getSeconds())));
            }, 2L);
       /* if (event.getEntity() instanceof Player) {
            Player attacker = (Player) event.getEntity();

            if (event.isCancelled())
                return;

            antyLogoutManager.create(new AntyLogout(attacker.getUniqueId(), System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(seconds)));
        }

        */
        }
    }


    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {

        Entity damager = event.getDamager();
        if (damager instanceof Arrow) { // check if the damager is an arrow

            Arrow arrow = (Arrow) damager;
            final Entity entityHit = event.getEntity();
            if (entityHit instanceof Player && arrow.getShooter() instanceof Player) {
                Player playerHit = (Player) entityHit;
                Player val = (Player) arrow.getShooter();
                User user = UserUtil.get(playerHit.getUniqueId());
                val.sendMessage(ChatHelper.fixColor("&4" + (user.getIncognito() == null ? playerHit.getName() : user.getIncognito().getIncognito()) + " &cposiada " + (int) playerHit.getHealth() + "❤"));
                manager.create(new AntyLogout(val.getUniqueId(), System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(storage.getSeconds())));
                manager.create(new AntyLogout(playerHit.getUniqueId(), System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(storage.getSeconds())));

            }

        }
    }

    @EventHandler
    public void onPlayerHitFishingRodEventThingyName(final PlayerFishEvent event) {
        final Player player = event.getPlayer();
        if (event.getCaught() instanceof Player) {
            final Player caught = (Player) event.getCaught();
            if (player.getItemInHand().getType() == Material.FISHING_ROD) {
                User user = UserUtil.get(caught.getUniqueId());
                player.sendMessage(ChatHelper.fixColor("&4" + (user.getIncognito() == null ? caught.getName() : user.getIncognito().getIncognito()) + " &cposiada " + (int) caught.getHealth() + "❤"));

                manager.create(new AntyLogout(caught.getUniqueId(), System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(storage.getSeconds())));
                manager.create(new AntyLogout(player.getUniqueId(), System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(storage.getSeconds())));
            }
        }
    }

    @EventHandler
    public void onSnowball(EntityDamageByEntityEvent event) {
        Entity damager = event.getDamager();
        if ((damager instanceof Snowball || damager instanceof Egg) && event.getEntity() instanceof Player) {
            Projectile sn = (Projectile) damager;
            if (sn.getShooter() instanceof Player) {
                Player shooter = (Player) sn.getShooter();
                Player damaager = (Player) event.getEntity();

                if (event.isCancelled())
                    return;
                User user = UserUtil.get(damaager.getUniqueId());
                shooter.sendMessage(ChatHelper.fixColor("&4" + (user.getIncognito() == null ? damaager.getName() : user.getIncognito().getIncognito()) + " &cposiada " + (int) damaager.getHealth() + "❤"));

                manager.create(new AntyLogout(shooter.getUniqueId(), System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(storage.getSeconds())));
                manager.create(new AntyLogout(damaager.getUniqueId(), System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(storage.getSeconds())));
            }

        }
    }
}
