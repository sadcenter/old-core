package net.sadcenter.commons.helper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class RandomHelper {

    private static final Random random = new Random();
    private static final String RANDOM_CHARACTER = "!@#$%^&*()_++_)(*&^%$#@!";


    public static int nextInt(int max) {
        return random.nextInt(max);
    }

    public static int nextInt() {
        return random.nextInt();
    }

    public static int nextInt(int min, int max) {
        return random.nextInt(max - min + 1) + min;
    }

    public static @NotNull String randomCharacters(int count) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < count; i++) {
            int character = nextInt(0, RANDOM_CHARACTER.length());
            builder.append(RANDOM_CHARACTER.charAt(character));
        }
        return builder.toString();
    }

    public static boolean hasChance(double chance) {
        return Math.random() * 100.0 <= chance;
    }

    public static boolean nextBoolean() {
        return random.nextBoolean();
    }
}
