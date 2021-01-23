package net.sadcenter.guilds.config.impl;

import net.sadcenter.guilds.config.Configuration;
import net.sadcenter.guilds.config.impl.storage.ServerSettingsStorage;
import org.jetbrains.annotations.NotNull;

import java.io.File;

/**
 * @author sadcenter on 18.10.2020
 * @project LASTCORE
 */

public class ServerSettingsConfiguration extends Configuration<ServerSettingsStorage> {

    public ServerSettingsConfiguration(@NotNull File file) {
        super(file, new ServerSettingsStorage(), ServerSettingsStorage.class);
    }
}
