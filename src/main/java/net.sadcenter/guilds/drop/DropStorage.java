package net.sadcenter.guilds.drop;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.bukkit.Material;

import java.util.Map;
import java.util.Set;

/**
 * @author sadcenter on 03.09.2020
 * @project LASTCORE
 */

public final class DropStorage {

    private final Set<Material> cancelled;
    private final double vipMultiplier;
    private final String vipPermission;
    private final Map<String, DropData> drops;
    private final double turbodropMultiplier;


    public DropStorage() {
        drops = Maps.newHashMap(ImmutableMap.of(
                "DIAMOND", new DropData(50, Material.DIAMOND, Sets.newHashSet(Material.DIAMOND_PICKAXE), 0, true, 90, 1, 2, 3),
                "GOLD", new DropData(25, Material.GOLD_INGOT, Sets.newHashSet(Material.DIAMOND_PICKAXE, Material.IRON_PICKAXE), 0, true, 90, 1, 4, 5),
                "EMERALD", new DropData(15, Material.EMERALD, Sets.newHashSet(Material.DIAMOND_PICKAXE, Material.IRON_PICKAXE), 0, true, 90, 2, 4, 7)
        ));
        cancelled = Sets.newHashSet(Material.IRON_ORE);
        vipPermission = "core.vip";
        vipMultiplier = 1.50;
        turbodropMultiplier = 3.0;
    }

    public double getTurbodropMultiplier() {
        return turbodropMultiplier;
    }

    public double getVipMultiplier() {
        return vipMultiplier;
    }

    public Map<String, DropData> getDrops() {
        return drops;
    }

    public Set<Material> getCancelled() {
        return cancelled;
    }

    public String getVipPermission() {
        return vipPermission;
    }

}
