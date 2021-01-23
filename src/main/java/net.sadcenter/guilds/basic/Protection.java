package net.sadcenter.guilds.basic;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

/**
 * @author sadcenter on 19.08.2020
 * @project LASTCORE
 */

@AllArgsConstructor
@Getter
@Setter
public final class Protection {

    private final UUID nick;
    private long expire;

}
