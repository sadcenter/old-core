package net.sadcenter.guilds.managers;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import net.sadcenter.commons.helper.ItemBuilder;
import net.sadcenter.commons.helper.ItemHelper;
import net.sadcenter.guilds.LastCore;
import net.sadcenter.guilds.basic.User;
import net.sadcenter.guilds.utils.JsonStorage;
import org.bson.Document;
import org.bson.json.JsonWriterSettings;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class UserManager {

    private final DBCollection collection;
    private final Map<UUID, User> userMap;

    public UserManager(DB db) {
        collection = db.getCollection("users");
        userMap = new HashMap<>();
    }

    public User get(UUID id) {
        return userMap.get(id);
    }

    public User get(String name) {
        return userMap.values().stream().filter(user -> user.getName().equals(name)).findFirst().orElse(null);
    }

    public User createIfAbsent(@NotNull Player p) {
        return userMap.computeIfAbsent(p.getUniqueId(), uuid -> {
            final User newUser = new User(uuid);
            userMap.put(uuid, newUser);
            newUser.getFirstConnection().setDate(System.currentTimeMillis());
            getCollection().insert(newUser.getDBObject(newUser.getClass()));
            Location location = p.getLocation();
            ItemHelper.giveOrDrop(p, new ItemStack(Material.STONE_PICKAXE), location);
            ItemHelper.giveOrDrop(p, new ItemStack(Material.ENDER_CHEST), location);
            ItemHelper.giveOrDrop(p, new ItemStack(Material.GRILLED_PORK, 64), location);
            ItemHelper.giveOrDrop(p, ItemBuilder.of(Material.FISHING_ROD, 1).setName("&3&l&3&lLAST&F&LCORE&7.PL").build(), location);
            ItemHelper.giveOrDrop(p, ItemBuilder.of(Material.WATER_BUCKET, 1).setName("&3&l&3&lLAST&F&LCORE&7.PL").build(), location);
            LastCore.getInstance().getProtectionManager().create(p);
            return newUser;
        });
    }

    public void saveUsers() {
        getUsers().values().forEach(user -> {
            DBObject dbObject = new BasicDBObject();
            dbObject.put("name", user.getName());
            user.setIncognito(null);
            LastCore.getInstance().getLogger().info("Saved user " + user.getName() + " with " + user.getId() + " uuid!");
            collection.update(dbObject, user.getDBObject(user.getClass()));
        });
        Bukkit.getLogger().info("Saved total " + userMap.size() + " users!");
    }

    public @NotNull Map<UUID, User> getUsers() {
        return userMap;
    }

    public void loadUsers() {
        JsonWriterSettings settings = JsonWriterSettings.builder()
                .int64Converter((value, writer) -> writer.writeNumber(value.toString()))
                .build();
        collection.find().forEach(document -> {
            final User u = JsonStorage.GSON.fromJson(Document.parse(document.toString()).toJson(settings), User.class);
            getUsers().put(u.getId(), u);
        });

        Bukkit.getLogger().info("Loaded " + getUsers().size() + " users from database!");
    }

    public DBCollection getCollection() {
        return collection;
    }
}
