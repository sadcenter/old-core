package net.sadcenter.guilds.config.impl;

import com.google.gson.GsonBuilder;
import net.sadcenter.guilds.config.Configuration;
import net.sadcenter.guilds.config.impl.storage.MainConfigurationStorage;
import net.sadcenter.guilds.json.serializer.LocationSerializer;
import net.sadcenter.guilds.utils.JsonStorage;
import org.bukkit.Location;

import java.io.File;

public class MainConfiguration extends Configuration<MainConfigurationStorage> {

    public MainConfiguration(File file) {
        super(file, new MainConfigurationStorage(), MainConfigurationStorage.class, JsonStorage.GSON);
    }
}
