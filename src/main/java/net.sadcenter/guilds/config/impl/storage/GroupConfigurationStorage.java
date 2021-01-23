package net.sadcenter.guilds.config.impl.storage;

import com.google.common.collect.Sets;
import net.sadcenter.guilds.basic.Group;

import java.util.HashSet;
import java.util.Set;

/**
 * @author sadcenter on 23.11.2020
 * @project LastCoree
 */

public final class GroupConfigurationStorage {

    private final Set<Group> groups;

    public GroupConfigurationStorage() {
        this.groups = Sets.newHashSet(
                new Group("GRACZ", "", "", new HashSet<>(), true),
                new Group("VIP", "&6", "&6VIP", new HashSet<>(), false),
                new Group("SVIP", "&5", "&5SVIP", new HashSet<>(), false)
        );
    }

    public Set<Group> getGroups() {
        return groups;
    }
}
