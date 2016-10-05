package com.acnebs.posts.irritat0r.util;


import org.junit.Test;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

import static org.junit.Assert.assertEquals;

public class SysdateRealImplTest {

    @Test
    public void test_now() throws Exception {
        SysdateRealImpl sysdate = new SysdateRealImpl();
        long secondsDiff = sysdate.now().until(LocalTime.now(), ChronoUnit.SECONDS);
        assertEquals(
                "It should yield 0 seconds difference between System now and SysdateRealImpl.now",
                0, secondsDiff);
    }

}
