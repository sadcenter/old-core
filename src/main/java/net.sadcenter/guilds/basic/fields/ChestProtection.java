package net.sadcenter.guilds.basic.fields;

import lombok.Data;
import org.bukkit.Location;

import java.util.Set;
import java.util.UUID;

/**
 * @author sadcenter on 30.08.2020
 * @project LASTCORE
 */

@Data
public final class ChestProtection {

    private final Location chest;
    private final UUID owner;
    private final Set<String> access;
    private final String tag;


}
