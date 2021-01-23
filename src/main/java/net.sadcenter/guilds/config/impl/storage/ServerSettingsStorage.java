package net.sadcenter.guilds.config.impl.storage;

import lombok.Getter;
import lombok.Setter;

/**
 * @author sadcenter on 18.10.2020
 * @project LASTCORE
 */
@Getter
@Setter
public final class ServerSettingsStorage {

    private boolean enabledGuilds;
    private boolean enabledDiamondsItems;
    private boolean enabledEnchanting;
    private boolean enabledTnt;
    private boolean enabledCase;
    private boolean enabledGuildItems;
    private boolean enabledPvp;

}
