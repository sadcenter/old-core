package net.sadcenter.guilds.basic.fields;

/**
 * @author sadcenter on 16.08.2020
 * @project LASTCORE
 */

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public final class ShopProductField {
    private final String name;
    private final String customName;
    private final String material;
    private final int amount;
    private final String command;
    private final List<String> customLores;
    private final List<String> messages;
}