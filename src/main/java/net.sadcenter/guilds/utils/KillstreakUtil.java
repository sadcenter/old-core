package net.sadcenter.guilds.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

/**
 * @author sadcenter on 01.09.2020
 * @project LASTCORE
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class KillstreakUtil {

    @NotNull
    public static String getKillStreak(int killstreak) {
        switch (killstreak) {
            case 1:
                return "KILL";
            case 2:
                return "DOUBLE-KILL";
            case 3:
                return "TRIPLE-KILL";
            case 4:
                return "QUADRA-KILL";
            case 5:
                return "PENTA-KILL";
        }
        return "LEGENDARY-KILL";
    }
}
