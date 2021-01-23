package net.sadcenter.guilds.managers;


import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.RemovalCause;
import com.github.benmanes.caffeine.cache.RemovalListener;
import lombok.RequiredArgsConstructor;
import net.sadcenter.guilds.basic.AntyLogout;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author sadcenter on 23.08.2020
 * @project AntyLogout
 */

public final class AntyLogoutManager {

    private final Cache<UUID, AntyLogout> antylogouts = Caffeine.newBuilder().expireAfterWrite(30, TimeUnit.SECONDS).removalListener(new RemovalListener<UUID, AntyLogout>() {
        @Override
        public void onRemoval(@Nullable UUID uuid, @Nullable AntyLogout antyLogout, @NonNull RemovalCause removalCause) {
            System.out.println("cl");
        }
    }).build();

    public void create(final @NotNull AntyLogout antyLogout) {
        antylogouts.put(antyLogout.getUUID(), antyLogout);
    }

    public void remove(@NotNull UUID key) {
        antylogouts.invalidate(key);
    }

    public final @NotNull Cache<UUID, AntyLogout> getMap() {
        return antylogouts;
    }

}
