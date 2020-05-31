package com.qman.web.utility;

import org.springframework.context.ApplicationEvent;

public class MasterDataReadyEvent extends ApplicationEvent {

    private static final long serialVersionUID = -8041454823298983509L;

    public MasterDataReadyEvent(Object source, String guestName) {
        super(source);
    }
}
