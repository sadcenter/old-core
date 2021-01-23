package net.sadcenter.guilds.basic;

/**
 * @author sadcenter on 16.08.2020
 * @project LASTCORE
 */

import lombok.Data;
import net.sadcenter.mongo.helper.MongoHelper;

import java.util.Set;

@Data
public final class ItemShop implements MongoHelper {
    private final int id;
    private final String nick;
    private final String name;
    private final int amount;
    private final String command;
    private final String material;
    private final Set<String> lores;
    private final long date;


}