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
                    new Irritat0rMessagePool("2+2=4")
            );
        }

        @Test
        public void test_getText_forUnknownUser() throws Exception {
            assertEquals(
                    "It should yield the text with 'you' as the salutation if no salutation is passed",
                    "Hey you, did you know that 2+2=4?", service.getText(Optional.empty()));
        }

        @Test
        public void test_getText_forLoggedInUser() throws Exception {
            final String actual = service.getText(Optional.of("Joni"));
            assertEquals(
                    "It should yield the text with the given salutation",
                    "Hey Joni, did you know that 2+2=4?", actual
            );
        }
    }


    @RunWith(Parameterized.class)
    public static class ConvertSalutationTest {

        @Parameterized.Parameters
        public static Collection<Object[]> data() {
            return Arrays.asList(new Object[][]{
                    {new Fixture("It should yield the text with as is if it is not padded") {{
                        input = Optional.of("Joni");
                        expected = "Joni";
                    }}},
                    {new Fixture("It should yield a trimmed version of the salutation if it is padded") {{
                        input = Optional.of(" Joni ");
                        expected = "Joni";
                    }}},
                    {new Fixture("It should yield the fallback salutation if it is empty") {{
                        input = Optional.of("");
                        expected = "you";
                    }}},
                    {new Fixture("It should yield the fallback salutation if it is empty") {{
                        input = Optional.empty();
                        expected = "you";
                    }}}
            });
        }

        @Test
        public void test_convertSalutation() throws Exception {
            final String actual = service.convertSalutation(fixture.input);

            assertEquals(
                    fixture.message,
                    fixture.expected,
                    actual);
        }


        private Irritat0rService service;

        @Before
        public void setUp() throws Exception {
            service = new Irritat0rService(new Irritat0rMessagePool(""));
        }

        public static class Fixture {
            Fixture(final String message) {
                this.message = message;
            }
            Optional<String> input;
            String expected;
            String message;
        }

        private Fixture fixture;

        public ConvertSalutationTest(final Fixture fixture) {
            this.fixture = fixture;
        }
    }
}
