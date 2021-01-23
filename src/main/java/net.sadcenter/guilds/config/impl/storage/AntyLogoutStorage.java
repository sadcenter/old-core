package net.sadcenter.guilds.config.impl.storage;

import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;

/**
 * @author sadcenter on 15.10.2020
 * @project LASTCORE
 */
@Getter
@Setter
public final class AntyLogoutStorage {

    private final boolean titleEnd;
    private final String title;
    private final String subtitle;
    private final String antyLogoutMessage;
    private final String antyLogoutEnd;
    private final List<String> allowCommands;
    private final String blockedCommandMessage;
    private final List<String> blockedBlocks;
    private final String blockedMessage;
    private final int seconds;
    private final boolean spawn;
    private final int minX;
    private final int minZ;
    private final int maxX;
    private final int maxZ;

    public AntyLogoutStorage() {
        this.titleEnd = true;
        this.title = "&3&lWALKA";
        this.subtitle = "&7Walka sie skonczyla mozesz sie wylogowac!";
        this.antyLogoutMessage = "&7AntyLogout: &3{SECONDS}";
        this.antyLogoutEnd = "&7AntyLogout: &ABRAK";
        this.allowCommands = Arrays.asList("helpop", "efekty");
        this.blockedCommandMessage = "&cKomenda {COMMAND} jest zablokowana podczas walki!";
        this.blockedBlocks = Arrays.asList("WORKBENCH", "ANVIL");
        this.blockedMessage = "Interakcja z blokiem {BLOCK} jest zablokowane!";
        this.seconds = 30;
        this.spawn = false;
        this.minX = 0;
        this.minZ = 0;
        this.maxX = 0;
        this.maxZ = 0;
    }


}
