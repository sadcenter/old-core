package net.sadcenter.guilds.guis.guilds;


import net.sadcenter.commons.helper.ItemBuilder;
import net.sadcenter.guilds.basic.Member;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import pl.meme.gui.GuiBuilder;
import pl.meme.gui.GuiItem;

import java.util.Arrays;

public final class GuildPermissionGui extends GuiBuilder {

    public GuildPermissionGui(Player p, Member member, boolean fromMemberList) {
        super("&3UPRAWNIENIA", 4);
        this.click = e -> {
            if (e.getCurrentItem() != null && e.getCurrentItem().getType() != Material.FENCE_GATE)
                p.openInventory(new GuildPermissionGui(p, member, fromMemberList).build());
        };
        setItem(10, new GuiItem.Builder(Material.PAPER)
                .withItemName("&3&lUPRAWNIENIA DO NISZCZENIA")
                .withClickExecutor(e -> {
                    member.setBreakPerm(!member.isBreakPerm());
                })
                .withLore(Arrays.asList(" ", " &8>> &7Posiada permisje? " + (member.isBreakPerm() ? "&aTAK" : "&4NIE"), " &8>> &7Podstawowa wartosc: &aTAK", " "))
                .build());

        setItem(11, new GuiItem.Builder(Material.PAPER)
                .withItemName("&3&lUPRAWNIENIA DO BUDOWANIA")
                .withClickExecutor(e -> {
                    member.setPlacePerm(!member.isPlacePerm());
                })
                .withLore(Arrays.asList(" ", " &8>> &7Posiada permisje? " + (member.isPlacePerm() ? "&aTAK" : "&4NIE"), " &8>> &7Podstawowa wartosc: &aTAK", " "))
                .build());

        setItem(12, new GuiItem.Builder(Material.PAPER)
                .withItemName("&3&lUPRAWNIENIA DO SKRZYNEK")
                .withClickExecutor(e -> {
                    member.setChestPerm(!member.isChestPerm());
                })
                .withLore(Arrays.asList(" ", " &8>> &7Posiada permisje? " + (member.isChestPerm() ? "&aTAK" : "&4NIE"), " &8>> &7Podstawowa wartosc: &4NIE", " "))
                .build());

        setItem(13, new GuiItem.Builder(Material.PAPER)
                .withItemName("&3&lUPRAWNIENIA DO LAPIZU")
                .withClickExecutor(e -> {
                    member.setLapizPerm(!member.isLapizPerm());
                })
                .withLore(Arrays.asList(" ", " &8>> &7Posiada permisje? " + (member.isLapizPerm() ? "&aTAK" : "&4NIE"), " &8>> &7Podstawowa wartosc: &4NIE", " "))
                .build());

        setItem(14, new GuiItem.Builder(Material.PAPER)
                .withItemName("&3&lUPRAWNIENIA DO POWIEKSZANIA TERENU")
                .withClickExecutor(e -> {
                    member.setEnlargePerm(!member.isEnlargePerm());
                })
                .withLore(Arrays.asList(" ", " &8>> &7Posiada permisje? " + (member.isEnlargePerm() ? "&aTAK" : "&4NIE"), " &8>> &7Podstawowa wartosc: &4NIE", " "))
                .build());

        setItem(15, new GuiItem.Builder(Material.PAPER)
                .withItemName("&3&lUPRAWNIENIA DO WYRZUCANIA")
                .withClickExecutor(e -> {
                    member.setKickPerm(!member.isKickPerm());
                })
                .withLore(Arrays.asList(" ", " &8>> &7Posiada permisje? " + (member.isKickPerm() ? "&aTAK" : "&4NIE"), " &8>> &7Podstawowa wartosc: &4NIE", " "))
                .build());

        setItem(16, new GuiItem.Builder(Material.PAPER)
                .withItemName("&3&lUPRAWNIENIA DO SCHOWKA")
                .withClickExecutor(e -> {
                    member.setDepositPerm(!member.isDepositPerm());
                })
                .withLore(Arrays.asList(" ", " &8>> &7Posiada permisje? " + (member.isDepositPerm() ? "&aTAK" : "&4NIE"), " &8>> &7Podstawowa wartosc: &4NIE", " "))
                .build());

        setItem(20, new GuiItem.Builder(Material.PAPER)
                .withItemName("&3&lUPRAWNIENIA DO REGENERACJI")
                .withClickExecutor(e -> {
                    member.setRegenerationPerm(!member.isRegenerationPerm());
                })
                .withLore(Arrays.asList(" ", " &8>> &7Posiada permisje? " + (member.isRegenerationPerm() ? "&aTAK" : "&4NIE"), " &8>> &7Podstawowa wartosc: &4NIE", " "))
                .build());

        setItem(21, new GuiItem.Builder(Material.PAPER)
                .withItemName("&3&lUPRAWNIENIA DO WODY")
                .withClickExecutor(e -> {
                    member.setWaterPerm(!member.isWaterPerm());
                })
                .withLore(Arrays.asList(" ", " &8>> &7Posiada permisje? " + (member.isWaterPerm() ? "&aTAK" : "&4NIE"), " &8>> &7Podstawowa wartosc: &ATAK", " "))
                .build());

        setItem(22, new GuiItem.Builder(Material.PAPER)
                .withItemName("&3&lUPRAWNIENIA DO LAVY")
                .withClickExecutor(e -> {
                    member.setLavaPerm(!member.isLavaPerm());
                })
                .withLore(Arrays.asList(" ", " &8>> &7Posiada permisje? " + (member.isLavaPerm() ? "&aTAK" : "&4NIE"), " &8>> &7Podstawowa wartosc: &4NIE", " "))
                .build());

        setItem(23, new GuiItem.Builder(Material.PAPER)
                .withItemName("&3&lUPRAWNIENIA DO TNT")
                .withClickExecutor(e -> {
                    member.setTntPerm(!member.isTntPerm());
                })
                .withLore(Arrays.asList(" ", " &8>> &7Posiada permisje? " + (member.isTntPerm() ? "&aTAK" : "&4NIE"), " &8>> &7Podstawowa wartosc: &4NIE", " "))
                .build());

        setItem(24, new GuiItem.Builder(Material.PAPER)
                .withItemName("&3&lUPRAWNIENIA DO USTAWIANIA BAZY")
                .withClickExecutor(e -> {
                    member.setSetBasePerm(!member.isSetBasePerm());
                })
                .withLore(Arrays.asList(" ", " &8>> &7Posiada permisje? " + (member.isSetBasePerm() ? "&aTAK" : "&4NIE"), " &8>> &7Podstawowa wartosc: &4NIE", " "))
                .build());

        setItem(27, new GuiItem.Builder(Material.WOOL)
                .withItemData((short) 14)
                .withItemName("&4&lWYLACZ WSZYSTKO!")
                .withClickExecutor(e -> {
                    member.setInvitePerm(false);
                    member.setPvpPerm(false);
                    member.setLapizPerm(false);
                    member.setKickPerm(false);
                    member.setRegenerationPerm(false);
                    member.setDepositPerm(false);
                    member.setWaterPerm(false);
                    member.setLavaPerm(false);
                    member.setTntPerm(false);
                    member.setSetBasePerm(false);
                    member.setChestPerm(false);
                    member.setPlacePerm(false);
                    member.setBreakPerm(false);
                    member.setEnlargePerm(false);
                })
                .withLore(Arrays.asList(" ", " ", " "))
                .build());

        if (fromMemberList)
            setItem(31, new GuiItem.Builder(Material.FENCE_GATE)
                    .withItemName("&c&lPOWROC DO LISTY CZLONKOW!")
                    .withClickExecutor(e -> {
                        e.getWhoClicked().closeInventory();
                        e.getWhoClicked().openInventory(new GuildMembersListGui(p, member.getGuild()).build());
                    })
                    .build());

        setItem(35, new GuiItem.Builder(Material.WOOL)
                .withItemData((short) 5)
                .withItemName("&a&lWLACZ WSZYSTKO!")
                .withClickExecutor(e -> {
                    member.setInvitePerm(true);
                    member.setPvpPerm(true);
                    member.setLapizPerm(true);
                    member.setKickPerm(true);
                    member.setRegenerationPerm(true);
                    member.setDepositPerm(true);
                    member.setWaterPerm(true);
                    member.setLavaPerm(true);
                    member.setTntPerm(true);
                    member.setSetBasePerm(true);
                    member.setChestPerm(true);
                    member.setEnlargePerm(true);
                    member.setPlacePerm(true);
                    member.setBreakPerm(true);
                })
                .withLore(Arrays.asList(" ", " ", " "))
                .build());

        Inventory inv = this.build();
        ItemStack of = ItemBuilder.of(Material.STAINED_GLASS_PANE, 1, 0).build();
        for (int i = 0; i < 36; i++) {
            ItemStack o = inv.getItem(i);
            if (o == null || o.getType() == Material.AIR) {
                setItem(i, new GuiItem.Builder(of).build());
            }
        }
    }

}
