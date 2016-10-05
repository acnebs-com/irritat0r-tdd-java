package com.acnebs.posts.irritat0r.util;


import java.time.LocalTime;
import java.time.temporal.Temporal;

public class SysdateRealImpl implements Sysdate {

    @Override
    public Temporal now() {
        return LocalTime.now();
    }

}
