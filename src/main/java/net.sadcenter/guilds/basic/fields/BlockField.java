package net.sadcenter.guilds.basic.fields;

import lombok.Data;
import org.bukkit.Location;
import org.bukkit.Material;

/**
 * @author sadcenter on 18.08.2020
 * @project LASTCORE
 */

@Data
public final class BlockField {

    private final Material mat;
    private final Location location;
    private final byte data;

}
