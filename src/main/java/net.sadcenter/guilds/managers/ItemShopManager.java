package net.sadcenter.guilds.managers;

import com.mongodb.DBCollection;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.sadcenter.guilds.LastCore;
import net.sadcenter.guilds.basic.ItemShop;
import net.sadcenter.guilds.utils.JsonStorage;
import org.bson.Document;
import org.bson.json.JsonWriterSettings;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author sadcenter on 16.08.2020
 * @project LASTCORE
 */
public class ItemShopManager {

    @Getter
    private final Set<ItemShop> buyed = new HashSet<>();

    private final DBCollection dbCollection = LastCore.getInstance().getMongoDatabase().getCollection("itemsop");

    public void load() {
        JsonWriterSettings settings = JsonWriterSettings.builder()
                .int64Converter((value, writer) -> writer.writeNumber(value.toString()))
                .build();
        dbCollection.find().forEach(document -> {
            final ItemShop product = JsonStorage.GSON.fromJson(Document.parse(document.toString()).toJson(settings), ItemShop.class);
            getBuyed().add(product);
        });

        LastCore.getInstance().getLogger().info("Loaded " + getBuyed().size() + " products from database!");
    }

    public void create(ItemShop product) {
        buyed.add(product);
        dbCollection.insert(product.getDBObject(product.getClass()));
    }

    public Set<ItemShop> getShopItems(String nick) {
        return buyed.stream().filter(shopItem -> shopItem.getNick().equalsIgnoreCase(nick)).collect(Collectors.toSet());
    }


}
