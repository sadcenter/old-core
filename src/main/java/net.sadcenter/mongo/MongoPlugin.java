package net.sadcenter.mongo;

import lombok.Getter;
import net.sadcenter.mongo.config.Configuration;
import net.sadcenter.mongo.connection.MongoConnection;

import java.io.File;

public final class MongoPlugin {

    @Getter
    private final Configuration configuration;

    @Getter
    private final MongoConnection connection;


    public MongoPlugin(File folder) {
        configuration = new Configuration(new File(folder, "mongo.json"));
        connection = new MongoConnection(configuration.getConfigurationStorage());

    }
}
