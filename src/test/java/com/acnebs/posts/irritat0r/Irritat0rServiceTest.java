package com.acnebs.posts.irritat0r;

import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class Irritat0rServiceTest {
    Irritat0rService service;

    @Before
    public void setUp() throws Exception {
        service = new Irritat0rService();
    }

    @Test
    public void test_getText_forUnknownUser() throws Exception {
        assertEquals(
                "It should yield the text with 'you' as the salutation if no salutation is passed",
                "Hey you, did you know that 1+1=2?", service.getText(Optional.empty()));
    }

    @Test
    public void test_getText_forLoggedInUser() throws Exception {
        final String actual = service.getText(Optional.of("Joni"));
        assertEquals(
                "It should yield the text with the given salutation",
                "Hey Joni, did you know that 1+1=2?", actual
        );
    }

    @Test
    public void test_convertSalutation_forNormalString() throws Exception {
        final String actual = service.convertSalutation(Optional.of("Joni"));
        assertEquals(
                "It should yield the text with as is if it is not padded",
                "Joni", actual
        );
    }

    @Test
    public void test_convertSalutation_forPaddedString() throws Exception {
        final String actual = service.convertSalutation(Optional.of(" Joni "));
        assertEquals(
                "It should yield a trimmed version of the salutation if it is padded",
                "Joni", actual
        );
    }

    @Test
    public void test_convertSalutation_forEmtpyString() throws Exception {
        final String actual = service.convertSalutation(Optional.of(""));
        assertEquals(
                "It should yield the fallback salutation if it is empty",
                "you", actual
        );
    }

    @Test
    public void test_convertSalutation_forNull() throws Exception {
        final String actual = service.convertSalutation(Optional.empty());
        assertEquals(
                "It should yield the fallback salutation if it is empty",
                "you", actual
        );
    }
}
