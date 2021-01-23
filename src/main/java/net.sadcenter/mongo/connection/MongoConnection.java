package net.sadcenter.mongo.connection;


import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import lombok.Getter;
import net.sadcenter.mongo.config.util.ConfigurationStorage;


public final class MongoConnection {
    @Getter
    private final MongoClient mongoClient;

    public MongoConnection(ConfigurationStorage storage) {
        /*mongoClient = MongoClients
                .create(
                        MongoClientSettings
                                .builder()
                                .applyConnectionString(new ConnectionString("mongodb://" + storage.getHost() + ":27017"))
                                .credential(MongoCredential
                                        .createPlainCredential(storage.getUser(), "&3&lLAST&F&LCORE&7", storage.getPass().toCharArray()))
                                .build()
                );

         */
        mongoClient = new MongoClient(storage.getHost());
        MongoCredential.createPlainCredential(storage.getUser(), "Sentria", storage.getPass().toCharArray());
    }

}

