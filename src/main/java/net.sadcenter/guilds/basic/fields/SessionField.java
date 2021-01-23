package net.sadcenter.guilds.basic.fields;

import lombok.Getter;
import lombok.Setter;

public final class SessionField {

    @Getter
    @Setter
    private long date, quit;

    public SessionField() {
        this.date = 0L;
        this.quit = 0L;
    }
}
