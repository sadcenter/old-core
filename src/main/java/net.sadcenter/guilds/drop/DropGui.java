package net.sadcenter.guilds.drop;

import com.google.common.base.Joiner;
import net.sadcenter.commons.helper.ChatHelper;
import net.sadcenter.guilds.LastCore;
import net.sadcenter.guilds.basic.Guild;
import net.sadcenter.guilds.basic.User;
import net.sadcenter.guilds.guis.drop.DropMenuGui;
import net.sadcenter.guilds.utils.DateUtil;
import net.sadcenter.guilds.utils.GuildUtil;
import net.sadcenter.guilds.utils.MathUtils;
import net.sadcenter.guilds.utils.UserUtil;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.jetbrains.annotations.NotNull;
import pl.meme.gui.GuiBuilder;
import pl.meme.gui.GuiItem;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author sadcenter on 03.09.2020
 * @project LASTCORE
 */

public final class DropGui extends GuiBuilder {

    public DropGui(@NotNull Player p) {
        super("&3&lDROP", InventoryType.CHEST);


        User u = UserUtil.get(p.getUniqueId());
        double turbo = 0.0;
        DropStorage storage = LastCore.getInstance().getDropConfiguration().getDrops();
        if (u.getTurboDrop() != null && u.getTurboDrop().getExpire() > System.currentTimeMillis()) {
            turbo *= storage.getTurbodropMultiplier();
        }
        Guild g = GuildUtil.getFromPlayer(p.getUniqueId());
        if ((g != null && g.getTurboDrop() != null) && g.getTurboDrop().getExpire() > System.currentTimeMillis()) {
            turbo *= storage.getTurbodropMultiplier();
        }
        double finalTurbo = turbo;
        LastCore.getInstance().getDropConfiguration().getDrops().getDrops().forEach((s, dropData) -> {
            double multi = dropData.getChance();
            if (p.hasPermission("core.svip")) {
                multi += MathUtils.math(multi, 35);
            } else if (p.hasPermission("core.vip")) {
                multi += MathUtils.math(multi, 25);
            }
            if(multi <= 0) multi = dropData.getChance();

            GuiItem.Builder item = new GuiItem.Builder(dropData.getMaterial())
                    .withItemName("&7&l" + s)
                    .withLore(ChatHelper.fixColor(
                            Arrays.asList(
                                    " &7Wlączony " + (u.getDrop().isEnable(dropData.getMaterial()) ? "&atak" : "&cnie"),
                                    " &7Szansa &3" + dropData.getChance() + "%" + " &8(&7Bonus: &3" + ((multi-dropData.getChance())-1 * (finalTurbo == 0 ? 1 : finalTurbo)) + "%&8)",
                                    " &7Wypada tylko od &3" + dropData.getMinimalY() + " &7do &3" + dropData.getMaximalY(),
                                    " &7Fortuna " + (dropData.isFortune() ? "&atak" : "&cnie"),
                                    " &7Exp &3" + dropData.getExp(),
                                    " &7Wypada tylko z &3" + Joiner.on("&7, &3").join(dropData.getAllowedItems().toArray())
                            )
                    ));


            item.withClickExecutor(e -> {
                System.out.println(e.getCurrentItem().getType());
                if (u.getDrop().isEnable(e.getCurrentItem().getType())) {
                    u.getDrop().getEnabledDrops().remove(e.getCurrentItem().getType());
                } else {
                    u.getDrop().getEnabledDrops().add(e.getCurrentItem().getType());
                }
                p.openInventory(new DropGui(p).build());
            });


            if (u.getDrop().isEnable(dropData.getMaterial())) {
                item.withEnchantment(Enchantment.DURABILITY, 1);
            }
            addItem(item.build());
        });
        setItem(18, new GuiItem.Builder(Material.COBBLESTONE).withItemName(ChatHelper.fixColor("&7&lCOBBLESTONE"))
                .withLore(ChatHelper.fixColor(" &7Status: " + (u.getDrop().isEnable(Material.COBBLESTONE) ? "&atak" : "&cnie")))
                .withClickExecutor(e -> {
                    if (u.getDrop().isEnable(Material.COBBLESTONE)) {
                        u.getDrop().getEnabledDrops().remove(Material.COBBLESTONE);
                    } else {
                        u.getDrop().getEnabledDrops().add(Material.COBBLESTONE);
                    }
                    p.openInventory(new DropGui(p).build());
                }).build());

        setItem(25, new GuiItem.Builder(Material.WOOL)
                .withItemData((short) 5)
                .withItemName("&a&lWLACZ WSZYSTKO")
                .withClickExecutor(e -> {
                    u.getDrop().getEnabledDrops().clear();
                    u.getDrop()
                            .getEnabledDrops().addAll(
                            LastCore.getInstance()
                                    .getDropConfiguration()
                                    .getDrops()
                                    .getDrops()
                                    .values()
                                    .stream()
                                    .map(DropData::getMaterial)
                                    .collect(Collectors.toList()));
                    u.getDrop().getEnabledDrops().add(Material.COBBLESTONE);
                    p.openInventory(new DropGui(p).build());
                })
                .build());

        setItem(24, new GuiItem.Builder(Material.WOOL)
                .withItemData((short) 14)
                .withItemName("&c&lWYLACZ WSZYSTKO")
                .withClickExecutor(e -> {
                            u.getDrop().getEnabledDrops().clear();
                            p.openInventory(new DropGui(p).build());
                        }
                )
                .build());

        setItem(23, new GuiItem.Builder(Material.REDSTONE_TORCH_ON)
                .withItemName("&c&lZMIEN STATUS POWIADOMIEN!")
                .withLore(Arrays.asList(" ", " &8>> &7Status: &3" + u.getDrop().isNotifications(), ""))
                .withClickExecutor(e -> {
                            u.getDrop().toggleNotifications();
                            p.openInventory(new DropGui(p).build());
                        }
                )
                .build());

        setItem(22, new GuiItem.Builder(Material.FENCE_GATE)
                .withItemName("&c&lWROC DO DROPU")
                .withClickExecutor(e -> p.openInventory(new DropMenuGui(p).build())
                )
                .build());

        setItem(26, new GuiItem.Builder(Material.PAPER).withItemName(ChatHelper.fixColor("&7&lTURBODROP"))
                .withLore(ChatHelper.fixColor(
                        Arrays.asList(
                                " &7Bonus dla ciebie (turbo) &3" + turbo,
                                " ",
                                "&8          &3&lTY",
                                " &8>> &7Wlączony " + (u.getTurboDrop() == null ? "&cnie" : "&atak"),
                                " &8>> &7Wygasa &3" + (u.getTurboDrop() == null ? "&cnie" : DateUtil.getDurationBreakdown(u.getTurboDrop().getExpire() - System.currentTimeMillis())),
                                " ",
                                "&8          &3&lGIDIA",
                                " &8>> &7Wlączony " + (g == null || g.getTurboDrop() == null ? "&cnie" : "&atak"),
                                " &8>> &7Wygasa &3" + (g == null || g.getTurboDrop() == null ? "&cnie" : DateUtil.getDurationBreakdown(g.getTurboDrop().getExpire() - System.currentTimeMillis()))
                        )
                )).build());

        //this.click = e -> e.getWhoClicked().openInventory(new DropGui(p).build());
    }


}



