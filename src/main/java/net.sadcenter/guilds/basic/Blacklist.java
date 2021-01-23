package net.sadcenter.guilds.basic;

import lombok.Data;
import net.sadcenter.mongo.helper.MongoHelper;

import java.net.InetAddress;
import java.util.UUID;

/**
 * @author sadcenter on 10.09.2020
 * @project LASTCORE
 */

@Data
public final class Blacklist implements MongoHelper {

    private final UUID uuid;
    private final String nick;
    private final InetAddress ip;
    private final String reason;

}
