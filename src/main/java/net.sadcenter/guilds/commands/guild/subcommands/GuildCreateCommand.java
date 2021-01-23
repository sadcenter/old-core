package net.sadcenter.guilds.commands.guild.subcommands;

import net.sadcenter.commons.command.Command;
import net.sadcenter.commons.command.CommandBuilder;
import net.sadcenter.commons.command.CommandException;
import net.sadcenter.commons.helper.ChatHelper;
import net.sadcenter.guilds.LastCore;
import net.sadcenter.guilds.basic.Guild;
import net.sadcenter.guilds.basic.Member;
import net.sadcenter.guilds.storage.Settings;
import net.sadcenter.guilds.utils.GuildUtil;
import net.sadcenter.guilds.utils.ItemUtil;
import net.sadcenter.guilds.utils.MemberUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class GuildCreateCommand extends Command {

    private final List<ItemStack> itemStacks;

    public GuildCreateCommand(List<ItemStack> itemStacks) {
        super("zaloz");
        this.itemStacks = itemStacks;
    }

    @Override
    public void onCommand(CommandSender executor, CommandBuilder builder, String... args) throws CommandException {
        if (!Settings.isEnabledGuilds())
            throw new CommandException("&cGildie sa aktualnie wylaczone!");

        Player player = (Player) executor;
        if (args.length != 2) {
            ChatHelper.sendMessage(player, "&7Zla ilosc argumentow!");
            return;
        }
        String tag = args[0];
        String name = args[1];
        if (tag.length() > 6) {
            ChatHelper.sendMessage(player, "&cMaksymalna ilosc znakow w tagu to 6!");
            return;
        }
        if (GuildUtil.getFromPlayer(player.getName()) != null) {
            ChatHelper.sendMessage(player, "&7Posiadasz juz gildie!");
            return;
        }
        if (GuildUtil.getEquals(tag) != null) {
            ChatHelper.sendMessage(player, "&CTaka gildia juz istnieje!");
            return;
        }
        final List<ItemStack> stacks = new ArrayList<>();
        AtomicBoolean have = new AtomicBoolean(true);
        itemStacks.forEach(is -> {
            if (!have.get())
                return;

            ItemStack itemStack = is.clone();
            itemStack.setAmount(1);
            int required = (int) (player.hasPermission("scode.svip")
                    ?
                    itemStack.getAmount() * .50
                    :
                    player.hasPermission("scode.vip")
                            ?
                            itemStack.getAmount() * .25 :
                            itemStack.getAmount());

            System.out.println(ItemUtil.getAmount(player, itemStack.getType(), itemStack.getDurability()));

            if (ItemUtil.getAmount(player, itemStack.getType(), itemStack.getDurability()) < required) {
                have.set(false);
            } else {
                ItemStack itemStackk = is.clone();
                itemStack.setAmount(required);
                stacks.add(itemStackk);
            }
        });
        if (!have.get()) {
            ChatHelper.sendMessage(player, "&cNie udalo ci sie stworzyc gildii poniewaz nie masz wszystkich itemow :(");
            return;
        } else
            stacks.forEach(player.getInventory()::removeItem);

        if (!GuildUtil.canCreateGuild(player.getLocation(), 90, player.getLocation().getBlockY())) {
            ChatHelper.sendMessage(player, "&7W poblizu jest inna gildia! poszukaj innego miejsca (90)!");
            return;
        }
        if (!GuildUtil.canCreateSpawn(player.getLocation(), 400)) {
            ChatHelper.sendMessage(player, "&7Oddal sie od spawna (od 400 kratek mozna zakladac gildie)");
            return;
        }
        final Guild g = LastCore.getInstance().getGuildManager().createGuild(player, name, tag);
        Member member = MemberUtil.get(player.getUniqueId());
        member.setSetBasePerm(true);
        member.setTntPerm(true);
        member.setLavaPerm(true);
        member.setDepositPerm(true);
        member.setRegenerationPerm(true);
        member.setEnlargePerm(true);
        member.setKickPerm(true);
        member.setLapizPerm(true);
        member.setPvpPerm(true);
        member.setInvitePerm(true);
        Bukkit.broadcastMessage(ChatHelper.fixColor("&8>> &3" + player.getName() + " &7zalozyl gildie &7[&3" + g.getTag() + "&7] &3" + g.getName() + " "));
        ChatHelper.sendTitle(player, "&3&lZALOZYLES GILDIE", "&7[&3" + g.getTag() + "&7] &3" + g.getName());
    }


}
