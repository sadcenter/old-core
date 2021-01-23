package net.sadcenter.guilds.basic.fields;

import com.comphenix.protocol.wrappers.WrappedGameProfile;
import com.comphenix.protocol.wrappers.WrappedSignedProperty;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;


public final class IncognitoField {
    private final String playerName;
    private final WrappedGameProfile gameProfile;
    @Getter
    @Setter
    private String incognito;


    public IncognitoField(@NotNull Player player, String random) {
        this.playerName = player.getName();
        gameProfile = new WrappedGameProfile(UUID.randomUUID(), random);
        this.incognito = random;
        gameProfile.getProperties().removeAll("textures");
        gameProfile.getProperties().put("textures", WrappedSignedProperty.fromValues("textures", "ewogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJpZCIgOiAiOTYwZWU1NjJiM2QxNGVlM2JkM2Y0Njc4YTVmMzRkYWIiLAogICAgICAidHlwZSIgOiAiU0tJTiIsCiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzYxZmE4NzA4YjM1YTA2YmRjNWNlNDEyOWQ3YWJlZmExYzkzMGJkMDhlNDQ0MmUyZjFmMDU3NGM4OTJjYzYzOSIsCiAgICAgICJwcm9maWxlSWQiIDogIjdkYTJhYjNhOTNjYTQ4ZWU4MzA0OGFmYzNiODBlNjhlIiwKICAgICAgInRleHR1cmVJZCIgOiAiMzYxZmE4NzA4YjM1YTA2YmRjNWNlNDEyOWQ3YWJlZmExYzkzMGJkMDhlNDQ0MmUyZjFmMDU3NGM4OTJjYzYzOSIKICAgIH0KICB9LAogICJza2luIiA6IHsKICAgICJpZCIgOiAiOTYwZWU1NjJiM2QxNGVlM2JkM2Y0Njc4YTVmMzRkYWIiLAogICAgInR5cGUiIDogIlNLSU4iLAogICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS8zNjFmYTg3MDhiMzVhMDZiZGM1Y2U0MTI5ZDdhYmVmYTFjOTMwYmQwOGU0NDQyZTJmMWYwNTc0Yzg5MmNjNjM5IiwKICAgICJwcm9maWxlSWQiIDogIjdkYTJhYjNhOTNjYTQ4ZWU4MzA0OGFmYzNiODBlNjhlIiwKICAgICJ0ZXh0dXJlSWQiIDogIjM2MWZhODcwOGIzNWEwNmJkYzVjZTQxMjlkN2FiZWZhMWM5MzBiZDA4ZTQ0NDJlMmYxZjA1NzRjODkyY2M2MzkiCiAgfSwKICAiY2FwZSIgOiBudWxsCn0=", "xfkrHpy2QRiSAEtYe3hEABfxNO9TdhVAY4kgBNDv252BP3Obbe/44Ohhz3SZtufv7dzf+0bmi+OlkWz3QPAnjwxjqwWrlEg3htnN+Xb1n3uzRMI5NrpFK8afOzzlnMZGK5Z79wX24ZA3XYbH9s4/54wcDM6nAUJ/GD62VlMwM1DlPyFiKJrx2TJDbNWmjAqgK/qfONe2a0wjvghPERs06Xkk4le6aLVpP47mSNFVoTf05ZkrNFPXSovvBDlIA/KKGq107MPb6TAXG4hXk/aMsFQPXOxZwR2h1muC/rnDwXgHAD2ZMjL0AXyLWCKRA+ZW7147m95lCR4Y0s1AyvnRDdKRaxhqdTt5+tAhQPhyvaTu8Noxq6SzR8Wu58CLRBn2KZNad4NmT/QmyDKlZtlYIfYB2bO9NQiD1KC6EBGsuHVn7srQrjuF6glfp/L75vWAgytn9jVikzapJ/Gpv4zh8THXA104nVaYWwG8ImsCqyWuaA5IWx7Hxexj0WYIV1AOPMEGCcsic9OntjTwbCp0gTqfEZWAQ8nRSWkBRrzInA1QUpMTbZQUJmqHBgf3+X4fkWjh1lzLbIvXp5tXzDv+Rp8uVmLN+29Cfq+5TUePlnYPU5VpA2fOm9cbEHILsV6ArPgcu3RQt8tPTOK+LDdICg4lqxRfSn+srVMypsq4yWI="));
    }


    public WrappedGameProfile getGameProfile() {
        return gameProfile;
    }
}
