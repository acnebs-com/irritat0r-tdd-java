package com.acnebs.posts.irritat0r.util;


import java.time.temporal.Temporal;

public class SysdateStubImpl implements Sysdate {

    private final Temporal now;

    public SysdateStubImpl(Temporal now) {
        this.now = now;
    }

    @Override
    public Temporal now() {
        return now;
    }
}
