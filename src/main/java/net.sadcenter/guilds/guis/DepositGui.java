package net.sadcenter.guilds.guis;

import net.sadcenter.commons.helper.ChatHelper;
import net.sadcenter.commons.helper.ItemBuilder;
import net.sadcenter.commons.helper.ItemHelper;
import net.sadcenter.guilds.basic.User;
import net.sadcenter.guilds.basic.fields.DepositField;
import net.sadcenter.guilds.utils.ItemUtil;
import net.sadcenter.guilds.utils.UserUtil;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import pl.meme.gui.GuiBuilder;
import pl.meme.gui.GuiItem;

import java.util.Arrays;

/**
 * @author sadcenter on 06.09.2020
 * @project LASTCORE
 */

public final class DepositGui extends GuiBuilder {

    private final Player player;
    private final User u;

    public DepositGui(@NotNull final Player p) {
        super("&3&lDEPOZYT", InventoryType.CHEST);
        this.player = p;
        final DepositField depositField = (u = UserUtil.get(p.getUniqueId())).getDeposit();

        setItem(10, new GuiItem.Builder(Material.SNOW_BALL)
                .withItemName("&3&LSNIEZKI")
                .withLore(Arrays.asList("", " &7Posiadasz: &3" + depositField.getSnows()))
                .withClickExecutor(e -> snows()).build());

        setItem(11, new GuiItem.Builder(Material.ARROW)
                .withItemName("&3&LSTRZALY")
                .withLore(Arrays.asList("", " &7Posiadasz: &3" + depositField.getArrows()))
                .withClickExecutor(e -> arrows()).build());

        setItem(12, new GuiItem.Builder(Material.EGG)
                .withItemName("&3&LJAJKA")
                .withLore(Arrays.asList("", " &7Posiadasz: &3" + depositField.getEggs()))
                .withClickExecutor(e -> eggs()).build());

        setItem(14, new GuiItem.Builder(Material.GOLDEN_APPLE)
                .withItemName("&3&LKOXY")
                .withItemData((short) 1)
                .withLore(Arrays.asList("", " &7Posiadasz: &3" + depositField.getKoxs(), ""))
                .withClickExecutor(e -> gapple()).build());

        setItem(15, new GuiItem.Builder(Material.GOLDEN_APPLE)
                .withItemName("&3&LREFILE")
                .withLore(Arrays.asList("", " &7Posiadasz: &3" + depositField.getRefils(), ""))
                .withClickExecutor(e -> apple()).build());

        setItem(13, new GuiItem.Builder(Material.HOPPER)
                .withItemName("&3&LWYPLAC WSZYSTKO")
                .withClickExecutor(e -> {
                    gapple();
                    apple();
                    pearls();
                    arrows();
                    eggs();
                    snows();
                    e.getWhoClicked().closeInventory();
                }).build());

        setItem(16, new GuiItem.Builder(Material.ENDER_PEARL)
                .withItemName("&3&LPERLY")
                .withLore(Arrays.asList("", " &7Posiadasz: &3" + depositField.getPearls()))
                .withClickExecutor(e -> pearls()).build());

        final Inventory inv = this.build();
        final ItemStack of = ItemBuilder.of(Material.STAINED_GLASS_PANE, 1, 5).build();
        for (int i = 0; i < 27; i++) {
            final ItemStack o = inv.getItem(i);
            if (o == null || o.getType() == Material.AIR) {
                setItem(i, new GuiItem.Builder(of).build());
            }
        }

    }

    /*private void ice() {
        final DepositField depositField = u.getDeposit();
        final int i = ItemUtil.getAmount(player, Material.ICE, 0);
        if (depositField.getIce() <= 0) {
            ChatHelper.sendMessage(player, "&cNie posiadasz lodu w schowku!");
            return;
        }
        if (i >= 0) {
            ChatHelper.sendMessage(player, "&cOsiagnales juz limit lodu w eq");
            return;
        }
        final int ice = depositField.getIce();
        if (ice <= 0) {
            depositField.removeIce(ice);
            ItemHelper.giveOrDrop(player, ItemBuilder.of(Material.ICE, ice).build(), player.getLocation());
            ChatHelper.sendMessage(player, "&aPoprawnie wyplacono " + i + " lodu");
            player.openInventory(new DepositGui(player).build());
            return;
        }
        final int toDeposit = (i - 0) * -1;
        depositField.removeIce(toDeposit);
        ItemHelper.giveOrDrop(player, ItemBuilder.of(Material.ICE, toDeposit).build(), player.getLocation());
        ChatHelper.sendMessage(player, "&aPoprawnie wyplacono " + toDeposit + " lodu");
        player.openInventory(new DepositGui(player).build());
    }

     */

    private void arrows() {
        final DepositField depositField = u.getDeposit();
        final int i = ItemUtil.getAmount(player, Material.ARROW, 0);
        if (depositField.getArrows() <= 0) {
            ChatHelper.sendMessage(player, "&cNie posiadasz strzal w schowku!");
            return;
        }
        if (i >= 16) {
            ChatHelper.sendMessage(player, "&cOsiagnales juz limit strzal w eq");
            return;
        }
        final int arrows = depositField.getArrows();
        if (arrows <= 16) {
            depositField.removeArrows(arrows);
            ItemHelper.giveOrDrop(player, ItemBuilder.of(Material.ARROW, arrows).build(), player.getLocation());
            ChatHelper.sendMessage(player, "&aPoprawnie wyplacono " + i + " strzal");
            player.openInventory(new DepositGui(player).build());
            return;
        }
        final int toDeposit = (i - 16) * -1;
        depositField.removeArrows(toDeposit);
        ItemHelper.giveOrDrop(player, ItemBuilder.of(Material.ARROW, toDeposit).build(), player.getLocation());
        ChatHelper.sendMessage(player, "&aPoprawnie wyplacono " + toDeposit + " strzal");
        player.openInventory(new DepositGui(player).build());
    }

    private void snows() {
        final DepositField depositField = u.getDeposit();
        final int i = ItemUtil.getAmount(player, Material.SNOW_BALL, 0);
        if (depositField.getSnows() <= 0) {
            ChatHelper.sendMessage(player, "&cNie posiadasz sniezek w schowku!");
            return;
        }
        if (i >= 8) {
            ChatHelper.sendMessage(player, "&COsiagnales limit sniezek w eq!");
            return;
        }
        final int snows = depositField.getSnows();
        if (snows <= 8) {
            depositField.removeSnows(snows);
            ItemHelper.giveOrDrop(player, ItemBuilder.of(Material.SNOW_BALL, snows).build(), player.getLocation());
            ChatHelper.sendMessage(player, "&aPoprawnie wyplacono " + i + " sniezek");
            player.openInventory(new DepositGui(player).build());
            return;
        }
        final int toDeposit = (i - 8) * -1;
        depositField.removeSnows(toDeposit);
        ItemHelper.giveOrDrop(player, ItemBuilder.of(Material.SNOW_BALL, toDeposit).build(), player.getLocation());
        ChatHelper.sendMessage(player, "&aPoprawnie wyplacono " + toDeposit + " sniezek");
        player.openInventory(new DepositGui(player).build());
    }

    private void eggs() {
        final DepositField depositField = u.getDeposit();
        final int i = ItemUtil.getAmount(player, Material.EGG, 0);
        if (depositField.getEggs() <= 0) {
            ChatHelper.sendMessage(player, "&cNie posiadasz sniezek w schowku!");
            return;
        }
        if (i >= 16) {
            ChatHelper.sendMessage(player, "&COsiagnales limit jaj w eq!");
            return;
        }
        final int eggs = depositField.getEggs();
        if (eggs <= 16) {
            depositField.removeEggs(eggs);
            ItemHelper.giveOrDrop(player, ItemBuilder.of(Material.EGG, eggs).build(), player.getLocation());
            ChatHelper.sendMessage(player, "&aPoprawnie wyplacono " + i + " jaj");
            player.openInventory(new DepositGui(player).build());
            return;
        }
        final int toDeposit = (i - 16) * -1;
        depositField.removeSnows(toDeposit);
        ItemHelper.giveOrDrop(player, ItemBuilder.of(Material.EGG, toDeposit).build(), player.getLocation());
        ChatHelper.sendMessage(player, "&aPoprawnie wyplacono " + toDeposit + " jaj");
        player.openInventory(new DepositGui(player).build());
    }

    private void apple() {
        final DepositField depositField = u.getDeposit();
        final int i = ItemUtil.getAmount(player, Material.GOLDEN_APPLE, 0);
        if (depositField.getRefils() <= 0) {
            ChatHelper.sendMessage(player, "&cNie posiadasz refili w schowku!");
            return;
        }
        if (i >= 12) {
            ChatHelper.sendMessage(player, "&COsiagnales limit refili w eq!");
            return;
        }
        final int ref = depositField.getRefils();
        if (ref <= 12) {
            depositField.removeRefil(ref);
            ItemHelper.giveOrDrop(player, ItemBuilder.of(Material.GOLDEN_APPLE, ref).build(), player.getLocation());
            ChatHelper.sendMessage(player, "&aPoprawnie wyplacono " + i + " refili");
            player.openInventory(new DepositGui(player).build());
            return;
        }
        final int toDeposit = (i - 12) * -1;
        depositField.removeRefil(toDeposit);
        ItemHelper.giveOrDrop(player, ItemBuilder.of(Material.GOLDEN_APPLE, toDeposit).build(), player.getLocation());
        ChatHelper.sendMessage(player, "&aPoprawnie wyplacono " + toDeposit + " refili");
        player.openInventory(new DepositGui(player).build());
    }

    private void gapple() {
        final DepositField depositField = u.getDeposit();
        final int i = ItemUtil.getAmount(player, Material.GOLDEN_APPLE, 1);
        if (depositField.getKoxs() <= 0) {
            ChatHelper.sendMessage(player, "&cNie posiadasz koxow w schowku!");
            return;
        }
        if (i >= 1) {
            ChatHelper.sendMessage(player, "&COsiagnales limit koxow w eq!");
            return;
        }
        depositField.removeKox(1);
        ItemHelper.giveOrDrop(player, ItemBuilder.of(Material.GOLDEN_APPLE, 1, 1).build(), player.getLocation());
        ChatHelper.sendMessage(player, "&aPoprawnie wyplacono " + i + " koxow");
        player.openInventory(new DepositGui(player).build());
    }

    private void pearls() {
        final DepositField depositField = u.getDeposit();
        final int i = ItemUtil.getAmount(player, Material.ENDER_PEARL, 0);
        if (depositField.getPearls() <= 0) {
            ChatHelper.sendMessage(player, "&cNie posiadasz perel w schowku!");
            return;
        }
        if (i >= 4) {
            ChatHelper.sendMessage(player, "&COsiagnales limit perel w eq!");
            return;
        }
        final int val = depositField.getPearls();
        if (val <= 4) {
            depositField.removePearls(val);
            ItemHelper.giveOrDrop(player, ItemBuilder.of(Material.ENDER_PEARL, val).build(), player.getLocation());
            ChatHelper.sendMessage(player, "&aPoprawnie wyplacono " + i + " perel");
            player.openInventory(new DepositGui(player).build());
            return;
        }
        final int toDeposit = (i - 4) * -1;
        depositField.removeIce(toDeposit);
        ItemHelper.giveOrDrop(player, ItemBuilder.of(Material.ENDER_PEARL, toDeposit).build(), player.getLocation());
        ChatHelper.sendMessage(player, "&aPoprawnie wyplacono " + toDeposit + " perel");
        player.openInventory(new DepositGui(player).build());
    }

}
