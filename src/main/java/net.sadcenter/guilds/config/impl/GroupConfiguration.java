package net.sadcenter.guilds.config.impl;

import net.sadcenter.guilds.basic.Group;
import net.sadcenter.guilds.config.Configuration;
import net.sadcenter.guilds.config.impl.storage.GroupConfigurationStorage;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.logging.Level;

/**
 * @author sadcenter on 23.11.2020
 * @project LastCoree
 */


public class GroupConfiguration extends Configuration<GroupConfigurationStorage> {

    public GroupConfiguration(@NotNull File file) {
        super(file, new GroupConfigurationStorage(), GroupConfigurationStorage.class);
    }

    @Override
    public void loadConfiguration(Class<GroupConfigurationStorage> storageClass) {
        super.loadConfiguration(storageClass);
        if(getStorage().getGroups().stream().filter(Group::isDeafult).count() != 1) {
            throw new IllegalStateException("Wystapil blad podczas ladowania grup: Wykryto wiecej niz 1 deafultowa ranga!");
        }
    }
}
