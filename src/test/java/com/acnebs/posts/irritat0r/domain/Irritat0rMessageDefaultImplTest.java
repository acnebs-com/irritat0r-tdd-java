package com.acnebs.posts.irritat0r.domain;


import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class Irritat0rMessageDefaultImplTest {

    @Test
    public void test_getMessage() throws Exception {
        Irritat0rMessageDefaultImpl msg = new Irritat0rMessageDefaultImpl();

        final Person person = Person.Builder.of(" Jane ").build();
        String actual = msg.getMessage(Optional.of(person));
        assertEquals(
                "it is 8 times more likely to get killed by a pig than by a shark",
                actual);
    }


}
