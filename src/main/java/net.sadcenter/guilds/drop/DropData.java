package net.sadcenter.guilds.drop;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.bukkit.Material;

import java.util.Set;

/**
 * @author sadcenter on 03.09.2020
 * @project LASTCORE
 */

@Data @AllArgsConstructor
public final class DropData {

    private final double chance;
    private final Material material;
    private final Set<Material> allowedItems;
    private final double minimalY;
    private final boolean fortune;
    private final double maximalY;
    private final int minAmount;
    private final int maxAmount;
    private final int exp;

}
