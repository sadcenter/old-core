package net.sadcenter.guilds.commands.guild.subcommands;

import com.google.common.base.Joiner;
import com.google.common.collect.Sets;
import net.sadcenter.commons.command.Command;
import net.sadcenter.commons.command.CommandBuilder;
import net.sadcenter.commons.helper.ChatHelper;
import net.sadcenter.guilds.basic.Guild;
import net.sadcenter.guilds.basic.fields.ChestProtection;
import net.sadcenter.guilds.utils.GuildUtil;
import net.sadcenter.guilds.utils.LocationUtil;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Set;

/**
 * @author sadcenter on 30.08.2020
 * @project LASTCORE
 */


public class GuildSecureCommand extends Command {

    public GuildSecureCommand() {
        super("secure", new String[]{"zabezpiecz"}, true);
    }

    @Override
    public void onCommand(CommandSender executor, CommandBuilder commandBuilder, String... args) {
        Player player = (Player) executor;
        final Guild g = GuildUtil.getFromPlayer(player.getUniqueId());
        if (g == null) {
            ChatHelper.sendMessage(player, "&8>> &cNie posiadasz gildii!");
            return;
        }
        if (args.length == 0) {
            ChatHelper.sendMessage(player, "&8>> &3/g secure stworz");
            ChatHelper.sendMessage(player, "&8>> &3/g secure dodaj [nick]");
            ChatHelper.sendMessage(player, "&8>> &3/g secure wyrzuc [nick]");
            ChatHelper.sendMessage(player, "&8>> &3/g secure info");
            ChatHelper.sendMessage(player, "&8>> &3/g secure usun");
        } else if (args[0].equalsIgnoreCase("stworz")) {
            Block target = player.getTargetBlock((Set<Material>) null, 4);
            Guild var2 = GuildUtil.get(target.getLocation());
            if (var2 == null || !var2.getTag().equals(g.getTag())) {
                ChatHelper.sendMessage(player, "&cNie mozesz tutaj zabezpieczyc skrzynki");
                return;
            }
            if (!target.getType().equals(Material.CHEST)) {
                ChatHelper.sendMessage(player, "&cTen blok nie jest skrzynka");
                return;
            }
            ChestProtection protection = g.getChests().stream().filter(chestProtection -> LocationUtil.getLocationFromChest(target).equals(LocationUtil.getLocationFromChest(chestProtection.getChest().getBlock()))).findFirst().orElse(null);
            if (protection != null) {
                ChatHelper.sendMessage(player, "&7Ta skrzynka jest juz zabezpieczona");
                return;
            }
            g.getChests().add(new ChestProtection(LocationUtil.getLocationFromChest(target), player.getUniqueId(), Sets.newHashSet(player.getName()), g.getTag()));
            ChatHelper.sendMessage(player, "&cZabezpieczono skrzynke!");

        } else if (args[0].equalsIgnoreCase("dodaj")) {
            if (args.length != 2) {
                ChatHelper.sendMessage(player, "&7Kogo chcesz dodac?");
                return;
            }
            Block target = player.getTargetBlock((Set<Material>) null, 4);
            Guild var2 = GuildUtil.get(target.getLocation());
            if (var2 == null || !var2.getTag().equals(g.getTag())) {
                ChatHelper.sendMessage(player, "&cNie mozesz tutaj zabezpieczyc skrzynki");
                return;
            }
            if (!target.getType().equals(Material.CHEST)) {
                ChatHelper.sendMessage(player, "&cTen blok nie jest skrzynka");
                return;
            }
            ChestProtection protection = g.getChests().stream().filter(chestProtection -> LocationUtil.getLocationFromChest(target).equals(LocationUtil.getLocationFromChest(chestProtection.getChest().getBlock()))).findFirst().orElse(null);
            if (protection == null) {
                ChatHelper.sendMessage(player, "&cnull");
                return;
            }
            if (!protection.getOwner().equals(player.getUniqueId())) {
                ChatHelper.sendMessage(player, "&CNie jestes zalozycielem!");
                return;
            }
            if (protection.getAccess().contains(args[1])) {
                ChatHelper.sendMessage(player, "&cTen gracz posiada juz dostep!");
                return;
            }
            protection.getAccess().add(args[1]);
            ChatHelper.sendMessage(player, "&7Nadales dostep &3" + args[1]);

        } else if (args[0].equalsIgnoreCase("info")) {
            Block target = player.getTargetBlock((Set<Material>) null, 4);
            Guild var2 = GuildUtil.get(target.getLocation());
            if (var2 == null || !var2.getTag().equals(g.getTag())) {
                ChatHelper.sendMessage(player, "&cNie mozesz tutaj zabezpieczyc skrzynki");
                return;
            }
            if (!target.getType().equals(Material.CHEST)) {
                ChatHelper.sendMessage(player, "&cTen blok nie jest skrzynka");
                return;
            }
            ChestProtection protection = g.getChests().stream().filter(chestProtection -> LocationUtil.getLocationFromChest(target).equals(LocationUtil.getLocationFromChest(chestProtection.getChest().getBlock()))).findFirst().orElse(null);
            if (protection == null) {
                ChatHelper.sendMessage(player, "&cnull");
                return;
            }
            ChatHelper.sendMessage(player, " ");
            ChatHelper.sendMessage(player, "&8>> &7Owner: &3" + Bukkit.getOfflinePlayer(protection.getOwner()).getName());
            ChatHelper.sendMessage(player, "&8>> &7Dostep: ");
            ChatHelper.sendMessage(player, "\n &8     - &3" + Joiner.on("\n &8     - &3").join(protection.getAccess().toArray()));
        } else if (args[0].equalsIgnoreCase("wyrzuc")) {
            if (args.length != 2) {
                ChatHelper.sendMessage(player, "&7Kogo chcesz wyrzucic?");
                return;
            }
            Block target = player.getTargetBlock((Set<Material>) null, 4);
            Guild var2 = GuildUtil.get(target.getLocation());
            if (var2 == null || !var2.getTag().equals(g.getTag())) {
                ChatHelper.sendMessage(player, "&cNie mozesz tutaj zabezpieczyc skrzynki");
                return;
            }
            if (!target.getType().equals(Material.CHEST)) {
                ChatHelper.sendMessage(player, "&cTen blok nie jest skrzynka");
                return;
            }
            ChestProtection protection = g.getChests().stream().filter(chestProtection -> LocationUtil.getLocationFromChest(target).equals(LocationUtil.getLocationFromChest(chestProtection.getChest().getBlock()))).findFirst().orElse(null);
            if (protection == null) {
                ChatHelper.sendMessage(player, "&cnull");
                return;
            }
            if (!protection.getOwner().equals(player.getUniqueId())) {
                ChatHelper.sendMessage(player, "&CNie jestes zalozycielem!");
                return;
            }
            if (!protection.getAccess().contains(args[1])) {
                ChatHelper.sendMessage(player, "&cTen gracz nie posiada dostepu!");
                return;
            }
            protection.getAccess().remove(args[1]);
            ChatHelper.sendMessage(player, "&7Wyrzuciles gracza &3" + args[1]);


        } else if (args[0].equalsIgnoreCase("usun")) {
            Block target = player.getTargetBlock((Set<Material>) null, 4);
            Guild var2 = GuildUtil.get(target.getLocation());
            if (var2 == null || !var2.getTag().equals(g.getTag())) {
                ChatHelper.sendMessage(player, "&cNie mozesz tutaj zabezpieczyc skrzynki");
                return;
            }
            if (!target.getType().equals(Material.CHEST)) {
                ChatHelper.sendMessage(player, "&cTen blok nie jest skrzynka");
                return;
            }
            ChestProtection protection = g.getChests().stream().filter(chestProtection -> LocationUtil.getLocationFromChest(target).equals(LocationUtil.getLocationFromChest(chestProtection.getChest().getBlock()))).findFirst().orElse(null);
            if (protection == null) {
                ChatHelper.sendMessage(player, "&cnull");
                return;
            }
            if (!protection.getOwner().equals(player.getUniqueId())) {
                ChatHelper.sendMessage(player, "&CNie jestes zalozycielem!");
                return;
            }
            g.getChests().remove(protection);
            ChatHelper.sendMessage(player, "&cUsunieto ochrone!");
        }
    }
}
