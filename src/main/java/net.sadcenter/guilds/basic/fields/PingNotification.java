package net.sadcenter.guilds.basic.fields;

import lombok.Data;
import org.bukkit.Location;

/**
 * @author sadcenter on 01.10.2020
 * @project LASTCORE
 */

@Data
public final class PingNotification {

    private final Location location;
    private final int ping;

}
