package net.sadcenter.guilds.basic;

import lombok.Data;
import net.sadcenter.mongo.helper.MongoHelper;

/**
 * @author sadcenter on 17.08.2020
 * @project LASTCORE
 */

@Data
public final class Mute implements MongoHelper {

    private final String admin;
    private final String name;
    private final long expire;
    private final long date;
    private final String reason;

}
