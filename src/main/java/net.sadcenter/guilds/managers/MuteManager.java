package net.sadcenter.guilds.managers;

import com.mongodb.DBCollection;
import lombok.RequiredArgsConstructor;
import net.sadcenter.commons.helper.ChatHelper;
import net.sadcenter.guilds.LastCore;
import net.sadcenter.guilds.basic.Mute;
import net.sadcenter.guilds.utils.DateUtil;
import net.sadcenter.guilds.utils.JsonStorage;
import org.bson.Document;
import org.bson.json.JsonWriterSettings;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author sadcenter on 17.08.2020
 * @project LASTCORE
 */

public final class MuteManager {

    private final Map<String, Mute> mutes = new ConcurrentHashMap<>();
    private final DBCollection collection = LastCore.getInstance().getMongoDatabase().getCollection("mutes");

    public void create(Mute mute) {
        mutes.put(mute.getName(), mute);
        Bukkit.broadcast(ChatHelper.fixColor("&cGracz " + mute.getName() + " zostal wyciszony przez " + mute.getAdmin() + " za " + mute.getReason() + " na " + (mute.getExpire() == 0L ? "zawsze" : DateUtil.getDate(mute.getExpire()) + " &8(&c" + DateUtil.getDurationBreakdown(mute.getExpire()) + "&8)")), "core.helper");
        Player player = Bukkit.getPlayerExact(mute.getName());
        if (player != null)
            ChatHelper.sendMessage(player, "&cZostales wyciszony przez " + mute.getAdmin() + " za '" + mute.getReason() + "'" +
                    " mute wygasa " + (mute.getExpire() == 0L ? "nigdy" : DateUtil.getDate(mute.getExpire()) + " &8(&c" + DateUtil.getDurationBreakdown(mute.getExpire()) + "&8)"));
        collection.insert(mute.getDBObject(mute.getClass()));
    }

    public void remove(@NotNull Mute mute) {
        mutes.remove(mute.getName());
        Player player = Bukkit.getPlayerExact(mute.getName());
        Bukkit.broadcast(ChatHelper.fixColor("&aGracz " + mute.getName() + " zostal odmutowany!"), "");
        if (player != null)
            ChatHelper.sendMessage(player, "&aZostales odmutowany!");
    }

    public void load() {
        JsonWriterSettings settings = JsonWriterSettings.builder()
                .int64Converter((value, writer) -> writer.writeNumber(value.toString()))
                .build();
        collection.find().forEach(document -> {
            final Mute u = JsonStorage.GSON.fromJson(Document.parse(document.toString()).toJson(settings), Mute.class);
            getMutes().put(u.getName(), u);
        });

        Bukkit.getLogger().info("Loaded " + getMutes().size() + " mutes from database!");
    }


    public @NotNull Map<String, Mute> getMutes() {
        return mutes;
    }
}
