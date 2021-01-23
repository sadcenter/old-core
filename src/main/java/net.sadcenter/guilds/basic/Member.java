package net.sadcenter.guilds.basic;

import lombok.Getter;
import lombok.Setter;
import net.sadcenter.guilds.utils.GuildUtil;
import net.sadcenter.mongo.helper.MongoHelper;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

@Getter
@Setter
public final class Member implements MongoHelper {

    private final UUID uuid;
    private final String name, tag;
    private final long added;
    private boolean placePerm;
    private boolean breakPerm;
    private boolean tntPerm;
    private boolean chestPerm;
    private boolean pvpPerm;
    private boolean invitePerm;
    private boolean lapizPerm;
    private boolean waterPerm;
    private boolean lavaPerm;
    private boolean regenerationPerm;
    private boolean kickPerm;
    private boolean setBasePerm;
    private boolean depositPerm;
    private boolean enlargePerm;

    public Member(final UUID uuid, final String tag, String namePlayer) {
        this.uuid = uuid;
        this.tag = tag;
        this.name = namePlayer;
        this.placePerm = true;
        this.breakPerm = true;
        this.waterPerm = true;
        this.added = System.currentTimeMillis();
        this.chestPerm = true;
    }

    public @NotNull
    Guild getGuild() {
        return GuildUtil.get(tag);
    }


   /*
     @Override
    public String toJson() {
        final JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("uuid", getUuid().toString());
        jsonObject.addProperty("name", getName());
        jsonObject.addProperty("guild", getGuild().toJson());
        return jsonObject.toString();
    }

    */

    @Override
    public String toString() {
        return name;
    }

    @NotNull
    public UUID getUuid() {
        return uuid;
    }
}
