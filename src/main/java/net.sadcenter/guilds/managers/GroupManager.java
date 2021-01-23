package net.sadcenter.guilds.managers;

import net.sadcenter.guilds.LastCore;
import net.sadcenter.guilds.basic.Group;
import net.sadcenter.guilds.basic.User;
import net.sadcenter.guilds.config.impl.GroupConfiguration;
import net.sadcenter.guilds.utils.UserUtil;
import org.bukkit.entity.Player;

import java.util.UUID;

/**
 * @author sadcenter on 23.11.2020
 * @project LastCoree
 */

public final class GroupManager {

    private final static GroupConfiguration groupConfiguration = LastCore.getInstance().getGroupConfiguration();
    private static Group deafult = null;

    static {
        try {
            deafult = groupConfiguration.getStorage().getGroups().stream().filter(Group::isDeafult).findAny().orElseThrow(Exception::new);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Group getDeafultGroup() {
        return deafult;
    }

    public static Group findGroup(String name) {
        return groupConfiguration.getStorage().getGroups().stream().filter(group -> group.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
    }

    public static void setGroup(Player player, String group) {
        User user = UserUtil.get(player.getUniqueId());
        user.setGroup(group);
    }

    public static void addPermission(String group, String permission) {
        findGroup(group).getPermissions().add(permission);
    }



}
