package net.sadcenter.guilds.config.impl.storage;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import net.sadcenter.guilds.basic.Warp;
import net.sadcenter.guilds.basic.fields.CustomCommand;
import net.sadcenter.guilds.basic.fields.ShopProductField;
import net.sadcenter.guilds.drop.turbodrops.TurboDrop;
import net.sadcenter.guilds.utils.Worlds;
import org.bukkit.Location;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public final class MainConfigurationStorage {

    private final Map<String, ShopProductField> mapOfServices;
    private final Map<String, CustomCommand> customCommandMap;
    private final Map<String, Warp> warps;
    private final List<String> guildItems;
    private TurboDrop turboDropServer;


    public MainConfigurationStorage() {
        mapOfServices = Collections.singletonMap("vip", new ShopProductField("VIP", "&3&lVOUCHER NA VIPA", "BOOK", 1, "say xd", Arrays.asList("&7Ranga: &3VIP", "&7Zakupiles: &3{DATE}"), Arrays.asList("&7Gracz &3{NICK} &7zakupil &3VIPA", "&7Odwiedz nasza strone: &3https://&3&lLAST&F&LCORE&7.pl")));
        warps = Maps.newHashMap(ImmutableMap.of("PVP", new Warp(new Location(Worlds.DEAFULT_WORLD, 40, 70, 40))));
        customCommandMap = Maps.newHashMap(ImmutableMap.of("POMOC", new CustomCommand(Arrays.asList("&7Test", "&ctak!"))));
        turboDropServer = null;
        guildItems = Arrays.asList("material:DIAMOND amount:64 data:0 ", "material:EMERALD amount:64 data:0 ");
    }

    public TurboDrop getTurboDropServer() {
        return turboDropServer;
    }

    public void setTurboDropServer(TurboDrop turboDropServer) {
        this.turboDropServer = turboDropServer;
    }

    public List<String> getGuildItems() {
        return guildItems;
    }

    @NotNull
    @Contract("_ -> new")
    public Optional<CustomCommand> getCommand(String key) {
        return customCommandMap.entrySet().stream().filter(stringCustomCommandEntry -> stringCustomCommandEntry.getKey().equalsIgnoreCase(key)).map(Map.Entry::getValue).findFirst();
    }

    public Map<String, Warp> getWarps() {
        return warps;
    }

    @NotNull
    @Contract("_ -> new")
    public Optional<Warp> getWarp(String key) {
        return warps.entrySet().stream().filter(stringCustomCommandEntry -> stringCustomCommandEntry.getKey().equalsIgnoreCase(key)).map(Map.Entry::getValue).findFirst();
    }

    public Map<String, ShopProductField> getMapOfServices() {
        return mapOfServices;
    }
}
