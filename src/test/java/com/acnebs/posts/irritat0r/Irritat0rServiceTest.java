package com.acnebs.posts.irritat0r;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

@RunWith(Enclosed.class)
public class Irritat0rServiceTest {

    public static class GeneralTest {
        Irritat0rService service;

        @Before
        public void setUp() throws Exception {
            service = new Irritat0rService(
                    new Irritat0rMessagePool(/*"2+2=4"*/)
            );
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
    }


    @RunWith(Parameterized.class)
    public static class ConvertSalutationTest {
        Irritat0rService service;
        private Fixture fixture;

        public ConvertSalutationTest(final Fixture fixture) {
            this.fixture = fixture;
        }

        @Before
        public void setUp() throws Exception {
            service = new Irritat0rService(new Irritat0rMessagePool());
        }

        @Parameterized.Parameters
        public static Collection<Object[]> data() {
            return Arrays.asList(new Object[][]{
                    {new Fixture() {{
                        message     = "It should yield the salutation with " +
                                        "'as is' if it is not padded";
                        input       = Optional.of("Joni");
                        expected    = "Joni";
                    }}},
                    {new Fixture() {{
                        message     = "It should yield a trimmed version of the " +
                                        "salutation if it is padded";
                        input       = Optional.of(" Joni ");
                        expected    = "Joni";
                    }}},
                    {new Fixture() {{
                        message     = "It should yield the fallback salutation " +
                                        "if given salutation is an empty string";
                        input       = Optional.of("");
                        expected    = "you";
                    }}},
                    {new Fixture() {{
                        message     = "It should yield the fallback salutation " +
                                        "if given salutation is nothing but whitespace";
                        input       = Optional.of(" \t");
                        expected    = "you";
                    }}},
                    {new Fixture() {{
                        message     = "It should yield the fallback salutation " +
                                        "if given salutation is an empty Optional";
                        input       = Optional.empty();
                        expected    = "you";
                    }}}
            });
        }


        @Test
        public void test_convertSalutation() {
            assertEquals(
                    fixture.message,
                    fixture.expected,
                    service.convertSalutation(fixture.input));
        }


        public static class Fixture {
            Optional<String> input;
            String expected;
            String message;
        }
    }
}
