package net.sadcenter.mongo.config;

import lombok.Getter;
import lombok.SneakyThrows;
import net.sadcenter.guilds.utils.JsonStorage;
import net.sadcenter.mongo.config.util.ConfigurationStorage;
import org.bukkit.Bukkit;

import java.io.*;
import java.util.Objects;
import java.util.logging.Level;

public final class Configuration {
    private final File file;

    @Getter
    private ConfigurationStorage configurationStorage;

    @SneakyThrows
    public Configuration(File file) {
        this.file = file;
        if (!file.getParentFile().exists())
            file.getParentFile().mkdir();
        if (!this.file.exists()) {
            file.createNewFile();
            this.configurationStorage = new ConfigurationStorage();
            this.saveConfig();
        }

        this.loadConfig();
    }

    public void loadConfig() {
        FileReader fileReader = null;

        try {
            fileReader = new FileReader(this.file);
        } catch (FileNotFoundException exception) {
            Bukkit.getLogger().log(Level.SEVERE, "Unexpected error occurred while trying create a new FileReader for provided file.", exception);
        }

        BufferedReader bufferedReader = new BufferedReader(Objects.requireNonNull(fileReader));

        this.configurationStorage = JsonStorage.GSON.fromJson(bufferedReader, ConfigurationStorage.class);

        try {
            bufferedReader.close();
            fileReader.close();
        } catch (IOException exception) {
            Bukkit.getLogger().log(Level.SEVERE, "Unexpected error occurred while trying close readers...", exception);
        }
    }

    public void saveConfig() {
        try {
            FileWriter fileWriter = new FileWriter(this.file);
            JsonStorage.GSON.toJson(this.configurationStorage, fileWriter);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException exception) {
            Bukkit.getLogger().log(Level.SEVERE, "Unexpected error occurred while trying save configuration..", exception);
        }
    }
}
