package net.sadcenter.guilds.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author sadcenter on 15.10.2020
 * @project LASTCORE
 */

public abstract class Configuration<A> {

    private final File file;
    private A storage;
    private Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();

    public Configuration(@NotNull File file, @NotNull A storage, @NotNull Class<A> storageClass) {
        this.storage = storage;
        this.file = file;
        initConfiguration(storageClass);
    }

    public Configuration(@NotNull File file, @NotNull A storage, @NotNull Class<A> storageClass, @NotNull Gson gson) {
        this.storage = storage;
        this.file = file;
        this.gson = gson;
        initConfiguration(storageClass);
    }

    private void initConfiguration(Class<A> storageClass) {
        if (!file.getParentFile().exists())
            file.getParentFile().mkdir();

        if (!file.exists()) {
            this.saveConfiguration();
        } else
            this.loadConfiguration(storageClass);
    }

    public void saveConfiguration() {
        try (FileWriter fileWriter = new FileWriter(file)) {
            gson.toJson(storage, fileWriter);
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadConfiguration(Class<A> storageClass) {
        try (FileReader fileReader = new FileReader(file)) {
            this.storage = gson.fromJson(fileReader, storageClass);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public final A getStorage() {
        return storage;
    }

    public final File getFile() {
        return file;
    }
}
