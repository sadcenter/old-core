package net.sadcenter.guilds.drop.turbodrops;

import lombok.AllArgsConstructor;

/**
 * @author sadcenter on 04.11.2020
 * @project LastCore
 */
@AllArgsConstructor
public class TurboDrop {

    private final long expire;

    public long getExpire() {
        return expire;
    }
}
