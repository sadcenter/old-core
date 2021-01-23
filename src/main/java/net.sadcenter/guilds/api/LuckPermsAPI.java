package net.sadcenter.guilds.api;

import lombok.Getter;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;

/**
 * @author sadcenter on 20.08.2020
 * @project LASTCORE
 */

public class LuckPermsAPI {

    @Getter
    private static final LuckPerms luckPerms = LuckPermsProvider.get();
}
