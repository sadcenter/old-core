package net.sadcenter.guilds.basic.fields;

import lombok.Getter;
import net.sadcenter.guilds.LastCore;
import net.sadcenter.guilds.drop.DropData;
import org.bukkit.Material;

import java.util.HashSet;
import java.util.Set;

/**
 * @author sadcenter on 03.09.2020
 * @project LASTCORE
 */

@Getter
public final class DropField {

    private final Set<Material> enabledDrops;
    private boolean notifications;

    public DropField() {
        this.enabledDrops = new HashSet<>();
        for (DropData value : LastCore.getInstance().getDropConfiguration().getDrops().getDrops().values()) {
            enabledDrops.add(value.getMaterial());
        }
        notifications = true;
    }

    public boolean isEnable(Material material) {
        return enabledDrops.contains(material);
    }


    public void toggleNotifications() {
        this.notifications = !notifications;
    }
}
