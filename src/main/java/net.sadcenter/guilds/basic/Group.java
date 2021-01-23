package net.sadcenter.guilds.basic;

/**
 * @author sadcenter on 23.11.2020
 * @project LastCoree
 */

import lombok.Data;

import java.util.Set;

@Data
public final class Group {

    private final String name;
    private final String prefix;
    private final String suffix;
    private final Set<String> permissions;
    private final boolean deafult;

}
