package net.sadcenter.guilds.managers;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import net.sadcenter.commons.helper.ChatHelper;
import net.sadcenter.guilds.LastCore;
import net.sadcenter.guilds.basic.Guild;
import net.sadcenter.guilds.basic.Member;
import net.sadcenter.guilds.utils.GuildUtil;
import net.sadcenter.guilds.utils.JsonStorage;
import net.sadcenter.guilds.utils.NameTagUtil;
import org.bson.Document;
import org.bson.json.JsonWriterSettings;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitScheduler;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public final class GuildManager {
    private final DBCollection collection;
    private final Map<String, Guild> guildMap;
    private final Map<String, String> alliances;
    private final Map<UUID, Member> memberMap;
    private final Plugin plugin;
    private final BukkitScheduler scheduler;
    private final List<ItemStack> itemStacks = new ArrayList<>();

    public GuildManager(LastCore plugin) {
        // Settings.get().GuildItems.forEach(s -> itemStacks.add(ParserUtils.parseItemStack(s)));
        this.collection = plugin.getMongoDatabase().getCollection("guilds");
        this.guildMap = new ConcurrentHashMap<>();
        this.memberMap = new ConcurrentHashMap<>();
        this.alliances = new ConcurrentHashMap<>();
        this.plugin = plugin;
        this.scheduler = plugin.getServer().getScheduler();
    }


    public List<ItemStack> getItemStacks() {
        return itemStacks;
    }


    public Guild createGuild(@NotNull Player player, String name, String tag) {
        final Guild newGuild = new Guild(player, name, tag);
        getGuilds().put(newGuild.getTag(), newGuild);
        addToGuild(player, newGuild);
        NameTagUtil.init(player);
        Bukkit.getOnlinePlayers().forEach(NameTagUtil::init);
        scheduler.runTask(plugin, () -> GuildUtil.createRoomGuild(newGuild));
        collection.insert(newGuild.getDBObject(newGuild.getClass()));
        return newGuild;
    }


    public void deleteGuild(@NotNull Guild g) {
        g.getMembers()
                .values()
                .stream()
                .filter(member -> g.getTag().equals(member.getTag()))
                .forEach(member -> memberMap.remove(member.getUuid()));
        getGuilds().remove(g.getTag());
        collection.remove(new BasicDBObject("tag", g.getTag()));
        Bukkit.getOnlinePlayers().forEach(NameTagUtil::init);
    }


    public void leaveFromGuild(@NotNull Member member) {
        member.getGuild().getMembers().remove(member.getName());
        getMembers().remove(member.getUuid());
        collection.update(new BasicDBObject("tag", member.getTag()), member.getGuild().getDBObject(member.getGuild().getClass()));
        collection.update(new BasicDBObject("uuid", member.getUuid()), member.getDBObject(member.getClass()));
        Bukkit.getOnlinePlayers().forEach(NameTagUtil::init);
    }


    public @NotNull
    Map<String, Guild> getGuilds() {
        return guildMap;
    }


    public @NotNull Map<UUID, Member> getMembers() {
        return memberMap;
    }

    public @NotNull Map<String, String> getAlliances() {
        return alliances;
    }

    public void addToGuild(@NotNull Player player, @NotNull Guild guild) {
        Member member = new Member(player.getUniqueId(), guild.getTag(), player.getName());
        member.getGuild().addMember(member);
        getMembers().put(player.getUniqueId(), member);
        Bukkit.getOnlinePlayers().forEach(NameTagUtil::init);
    }

    public void registerAlliance(Guild first, Guild second) {
        alliances.put(first.getTag(), second.getTag());
        alliances.put(second.getTag(), first.getTag());
        first.getAlliances().add(second.getTag());
        second.getAlliances().add(first.getTag());
        ChatHelper.sendMessage(Bukkit.getOnlinePlayers(), "&7Gildia [&3" + first.getTag() + "&7] zawarla sojusz z [&3" + second.getTag() + "&7]");
    }


    public void saveGuilds() {
        getGuilds().values().forEach(guild -> {
            collection.update(new BasicDBObject("tag", guild.getTag()), guild.getDBObject(guild.getClass()));
            LastCore.getInstance().getLogger().info("Saved " + guild.getTag() + " guild");
        });
        LastCore.getInstance().getLogger().info("Saved total " + guildMap.size() + " guilds!");
    }

    public void loadGuilds() {
        JsonWriterSettings settings = JsonWriterSettings.builder()
                .int64Converter((value, writer) -> writer.writeNumber(value.toString()))
                .build();
        collection.find().forEach(document -> {
            final Guild u = JsonStorage.GSON.fromJson(Document.parse(document.toString()).toJson(settings), Guild.class);
            u.setRegenerating(false);
            getGuilds().put(u.getTag(), u);
            u.getMembers().values().forEach(member -> getMembers().put(member.getUuid(), member));
        });

        LastCore.getInstance().getLogger().info("Loaded " + getMembers().size() + " guilds from database with!");
    }
}
