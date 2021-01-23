package net.sadcenter.guilds.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.sadcenter.guilds.json.serializer.LocationSerializer;
import org.bukkit.Location;

/**
 * @author sadcenter on 16.08.2020
 * @project LASTCORE
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class JsonStorage {

    public static final JsonParser PARSER = new JsonParser();
    public static final Gson GSON = new GsonBuilder().setPrettyPrinting().registerTypeAdapter(Location.class, new LocationSerializer()).disableHtmlEscaping().create();
}
