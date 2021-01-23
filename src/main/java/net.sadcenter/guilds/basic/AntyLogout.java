package net.sadcenter.guilds.basic;

import lombok.Data;

import java.util.UUID;

/**
 * @author sadcenter on 23.08.2020
 * @project AntyLogout
 */
@Data
public final class AntyLogout {

    private final UUID UUID;
    private final long expire;

}
