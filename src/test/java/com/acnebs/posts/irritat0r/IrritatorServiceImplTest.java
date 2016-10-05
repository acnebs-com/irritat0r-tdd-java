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
public class IrritatorServiceImplTest {

    public static class GeneralTest {
        IrritatorServiceImpl service;

        @Before
        public void setUp() {
            service = new IrritatorServiceImpl();
        }

        @Test
        public void test_getText() throws Exception {
            String actual = service.getText(Optional.of(new Person(" Jane ")));
            assertEquals(
                    "It should yield the default text for a known user with a valid addressText",
                    "Hey Jane, did you know that it is 8 times more likely to get killed by a pig than by a shark?",
                    actual);
        }
    }


    @RunWith(Parameterized.class)
    public static class ParamTest {

        @Parameterized.Parameters
        public static Collection<Object[]> data() {
            return Arrays.asList(new Object[][]{
                    {new Fixtr("It should yield the default text with generic addressing for an anonymous person") {{
                        maybePerson = Optional.empty();
                        expAddressing = "you";
                    }}},
                    {new Fixtr("It should yield the default text with personalized addressing for a known person") {{
                        maybePerson = Optional.of(new Person("Joni"));
                        expAddressing = "Joni";
                    }}},
                    {new Fixtr("It should yield the default text with generic addressing for a known person without a proper addressText") {{
                        maybePerson = Optional.of(new Person(null));
                        expAddressing = "you";
                    }}},
                    {new Fixtr("It should yield the default text with generic addressing for a know person without a proper addressText") {{
                        maybePerson = Optional.of(new Person(""));
                        expAddressing = "you";
                    }}},
                    {new Fixtr("It should yield the default text with generic addressing for a known person with an addressText containing only whitespace") {{
                        maybePerson = Optional.of(new Person(" "));
                        expAddressing = "you";
                    }}},
                    {new Fixtr("It should yield the default text with a trimmed generic addressing for a known person with padded addressText") {{
                        maybePerson = Optional.of(new Person(" Joni "));
                        expAddressing = "Joni";
                    }}}
            });
        }

        IrritatorServiceImpl service;

        @Before
        public void setUp() {
            service = new IrritatorServiceImpl();
        }

        @Test
        public void test_getAddressing() throws Exception {
            String actual = service.getAddressing(fixture.maybePerson);
            assertEquals(
                    fixture.message,
                    fixture.expAddressing,
                    actual);
        }


        public static class Fixtr {
            Fixtr(final String message) {
                this.message = message;
            }

            Optional<Person> maybePerson;
            String expAddressing;
            String message;
        }

        private Fixtr fixture;

        public ParamTest(final Fixtr fixture) {
            this.fixture = fixture;
        }
    }


}
