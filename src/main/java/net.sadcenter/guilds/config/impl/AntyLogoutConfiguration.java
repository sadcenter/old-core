package net.sadcenter.guilds.config.impl;

import net.sadcenter.guilds.config.Configuration;
import net.sadcenter.guilds.config.impl.storage.AntyLogoutStorage;

import java.io.File;

/**
 * @author sadcenter on 15.10.2020
 * @project LASTCORE
 */

public class AntyLogoutConfiguration extends Configuration<AntyLogoutStorage> {


    public AntyLogoutConfiguration(File file) {
        super(file, new AntyLogoutStorage(), AntyLogoutStorage.class);
    }

}
