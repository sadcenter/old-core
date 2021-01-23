package net.sadcenter.mongo.helper;

import com.google.gson.JsonObject;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import lombok.SneakyThrows;
import net.sadcenter.guilds.utils.JsonStorage;

import java.lang.reflect.Field;

/**
 * @author sadcenter on 16.08.2020
 * @project LASTCORE
 */
public interface MongoHelper {


    @SneakyThrows
    default DBObject getDBObject(Class<?> clazz) {
        JsonObject jsonObject = JsonStorage.PARSER.parse(JsonStorage.GSON.toJson(this)).getAsJsonObject();
        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(DontExport.class)) {
                jsonObject.remove(field.getName());
            }
        }
        return BasicDBObject.parse(jsonObject.toString());
    }

}
