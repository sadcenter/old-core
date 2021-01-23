package net.sadcenter.guilds.basic.fields;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author sadcenter on 13.09.2020
 * @project LASTCORE
 */

@Data
@AllArgsConstructor
public final class ChatOptionsField {

    private boolean chatMessages;
    private boolean deathMessages;
    private boolean caseMessages;
    private boolean autoMessages;


}
