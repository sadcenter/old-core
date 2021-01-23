package net.sadcenter.commons;

import lombok.Getter;
import net.sadcenter.commons.command.CommandManager;
import net.sadcenter.commons.helper.CommandManagerImpl;
import org.bukkit.Bukkit;
import org.bukkit.command.SimpleCommandMap;

import java.lang.reflect.Field;

/**
 * @author sadcenter on 05.08.2020
 * @project LASTCORE
 */

public class CommonPlugin {

    @Getter
    private final CommandManager commandManager;

    public CommonPlugin() throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
        String ver = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
        Class<?> craftServerClass = Class.forName("org.bukkit.craftbukkit." + ver + ".CraftServer");
        Object craftServerObject = craftServerClass.cast(Bukkit.getServer());
        Field commandMapField = craftServerClass.getDeclaredField("commandMap");
        commandMapField.setAccessible(true);
        SimpleCommandMap simpleCommandMap = (SimpleCommandMap) commandMapField.get(craftServerObject);
        commandManager = new CommandManagerImpl(simpleCommandMap);
    }

}
