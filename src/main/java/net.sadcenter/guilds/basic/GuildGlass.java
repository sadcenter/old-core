package net.sadcenter.guilds.basic;

import org.bukkit.ChatColor;

import java.util.Arrays;

/**
 * @author sadcenter on 03.11.2020
 * @project LastCore
 */
public enum GuildGlass {

    WHITE_GLASS("&7&LBIALA", 0), //= new GuildGlass("&7&LBIALA", (short)0);
    RED_GLASS("&4&lCZERWONA", 14), //= new GuildGlass("&4&lCZERWONA", (short)14);
    PINK_GLASS("&d&lROZOWA", 2), //= new GuildGlass("&d&lROZOWA", (short)2);
    BLUE_GLASS("&3&LNIEBIESKA", 11), //= new GuildGlass("&3&LNIEBIESKA", (short)11);
    YELLOW_GLASS("&E&LZOLTA", 4), //= new GuildGlass("&E&LZOLTA", (short)4);
    LIME_GLASS("&A&lZIELONA (lime)", 5), //= new GuildGlass("&A&lZIELONA (lime)", (short)5);
    PURPLE_GLASS("&5&LFIOLETOWA!", 10), //= new GuildGlass("&5&LFIOLETOWA!", (short)10);
    BROWN_GLASS("&7&LBRAZOWA", 12), //= new GuildGlass("&7&LBRAZOWA", (short)12);
    BLACK_GLASS("&0&LCZARNA", 15), //= new GuildGlass("&0&LCZARNA", (short)15);
    GREEN_GLASS("&2&LZIELONA", 13), //= new GuildGlass("&2&LZIELONA", (short)13);
    CYAN_GLASS("&B&LCYAN", 9), //= new GuildGlass("&B&LCYAN", (short)9);
    GRAY_GLASS("&8&LSZARA", 8); //= new GuildGlass("&8&LSZARA", (short)8);


    private final String customName;
    private final byte data;


    GuildGlass(String customName, byte data) {
        this.customName = customName;
        this.data = data;
    }

    GuildGlass(String customName, int data) {
        this.customName = customName;
        this.data = (byte) data;
    }

    public static GuildGlass find(String value) {
        return Arrays.stream(values()).filter(guildGlass -> {
            System.out.println(guildGlass.getCustomName());
            String replace = value.replace(" SZKLO!", "").replace(String.valueOf(ChatColor.COLOR_CHAR), "&");
            System.out.println(replace);
            boolean s = guildGlass.getCustomName()
                    .equalsIgnoreCase(replace);
            System.out.println(s);
            return s;
        }).findFirst().orElse(null);
    }

    public final byte getData() {
        return data;
    }

    public final String getCustomName() {
        return customName;
    }
}
