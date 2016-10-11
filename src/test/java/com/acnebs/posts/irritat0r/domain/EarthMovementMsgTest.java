package com.acnebs.posts.irritat0r.domain;


import com.acnebs.posts.irritat0r.util.SysdateFixedDateImpl;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class EarthMovementMsgTest {

    Irritat0rMessageEarthMovementImpl msg;

    @Before
    public void setUp() throws Exception {
        msg = new Irritat0rMessageEarthMovementImpl();
    }


    @Test
    public void test_getMessage_known_user_with_birthday() throws Exception {
        LocalDateTime now = LocalDateTime.of(2016, 10, 4, 23, 40, 36);
        SysdateFixedDateImpl sysdate = new SysdateFixedDateImpl(now);
        msg = new Irritat0rMessageEarthMovementImpl(sysdate);

        final Person person = Person.Builder
                .of("Joni")
                .withBirthday(LocalDateTime.of(2016, 10, 4, 23, 40, 33))
                .build();

        String actual = msg.getMessage(Optional.of(person));
        assertEquals(
                "earth has moved 90 km since the day you were born",
                actual);
    }


    @Test
    public void test_getMessage_known_user_without_birthday() throws Exception {
        LocalDateTime now = LocalDateTime.of(2016, 10, 4, 23, 40, 36);
        SysdateFixedDateImpl sysdate = new SysdateFixedDateImpl(now);
        msg = new Irritat0rMessageEarthMovementImpl(sysdate);

        final Person person = Person.Builder
                .of("Joni")
                .build();

        String actual = msg.getMessage(Optional.of(person));
        assertEquals(
                "you're moving at a speed of 30 km per second around the sun",
                actual);
    }


    @Test
    public void test_getKmsSinceDate_1_sec() throws Exception {
        LocalDateTime date = LocalDateTime.of(2016, 10, 4, 23, 40, 33);
        LocalDateTime now = LocalDateTime.of(2016, 10, 4, 23, 40, 34);
        long kms = msg.getKmsSinceDate(date, now);
        assertEquals(
                "It should yield 30 because the date diff is 1 second and " +
                        "earth moves 30km per second around the sun",
                30, kms);
    }

    @Test
    public void test_getKmsSinceDate_2_secs() throws Exception {
        LocalDateTime date = LocalDateTime.of(2016, 10, 4, 23, 40, 33);
        LocalDateTime now = LocalDateTime.of(2016, 10, 4, 23, 40, 35);
        long kms = msg.getKmsSinceDate(date, now);
        assertEquals(
                "It should yield 30 because the date diff is 2 seconds",
                60, kms);
    }


}
