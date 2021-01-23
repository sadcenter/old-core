package net.sadcenter.guilds.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.sadcenter.guilds.LastCore;
import net.sadcenter.guilds.basic.Member;
import net.sadcenter.guilds.managers.GuildManager;

import java.util.UUID;

/**
 * @author sadcenter on 30.08.2020
 * @project LASTCORE
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class MemberUtil {

    private static final GuildManager guildManager = LastCore.getInstance().getGuildManager();

    public static Member get(UUID uuid) {
        return guildManager.getMembers().get(uuid);
    }

    public static Member get(String name) {
        return guildManager.getMembers().values().stream().filter(member -> member.getName().equals(name)).findFirst().orElse(null);
    }


}
