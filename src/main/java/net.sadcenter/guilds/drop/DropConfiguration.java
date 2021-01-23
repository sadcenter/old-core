package net.sadcenter.guilds.drop;

import lombok.Getter;
import lombok.SneakyThrows;
import net.sadcenter.guilds.LastCore;
import net.sadcenter.guilds.utils.JsonStorage;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * @author sadcenter on 03.09.2020
 * @project LASTCORE
 */

public final class DropConfiguration {

    @Getter
    private final File file;
    @Getter
    private DropStorage drops;

    public DropConfiguration(@NotNull File f) {
        this.file = f;
        if (!file.exists()) {
            LastCore.getInstance().saveResource(f.getName(), false);
            drops = new DropStorage();
            saveConfiguration();
        }
        loadConfiguration();
    }

    @SneakyThrows
    void loadConfiguration() {
        FileReader fileReader = new FileReader(file);
        drops = JsonStorage.GSON.fromJson(fileReader, DropStorage.class);
        fileReader.close();
    }

    @SneakyThrows
    void saveConfiguration() {
        FileWriter fileWriter = new FileWriter(file);
        JsonStorage.GSON.toJson(drops, fileWriter);
        fileWriter.flush();
        fileWriter.close();
    }

}
