package net.sadcenter.guilds.basic.fields;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author sadcenter on 05.09.2020
 * @project LASTCORE
 */

@Data
@AllArgsConstructor
public final class KitField {

    private long food;
    private long player;
    private long vip;
    private long svip;

}
