package net.sadcenter.guilds.json.serializer;

import com.google.gson.*;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.lang.reflect.Type;

/**
 * @author sadcenter on 05.10.2020
 * @project LASTCORE
 */

public final class LocationSerializer implements JsonDeserializer<Location>, JsonSerializer<Location> {

    @Override
    public Location deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        int x = jsonObject.get("x").getAsInt();
        int y = jsonObject.get("y").getAsInt();
        int z = jsonObject.get("z").getAsInt();
        String world = jsonObject.get("world").getAsString();
        return new Location(Bukkit.getWorld(world), x, y, z);
    }

    @Override
    public JsonElement serialize(Location location, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("x", location.getBlockX());
        jsonObject.addProperty("y", location.getBlockY());
        jsonObject.addProperty("z", location.getBlockZ());
        jsonObject.addProperty("world", location.getWorld().getName());
        return jsonObject;
    }
}
