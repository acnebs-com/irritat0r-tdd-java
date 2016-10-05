package com.acnebs.posts.irritat0r.domain;
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
public class Irritat0rSalutationImplTest {

    @RunWith(Parameterized.class)
    public static class ParamTest {

        @Parameterized.Parameters
        public static Collection<Object[]> data() {
            return Arrays.asList(new Object[][]{
                    {new Fixtr("It should yield the default text with generic addressing for an anonymous person") {{
                        maybePerson = Optional.empty();
                        expectedSalutation = "you";
                    }}},
                    {new Fixtr("It should yield the default text with personalized addressing for a known person") {{
                        maybePerson = Optional.of(new Person("Joni"));
                        expectedSalutation = "Joni";
                    }}},
                    {new Fixtr("It should yield the default text with generic addressing for a known person without a proper addressText") {{
                        maybePerson = Optional.of(new Person(null));
                        expectedSalutation = "you";
                    }}},
                    {new Fixtr("It should yield the default text with generic addressing for a know person without a proper addressText") {{
                        maybePerson = Optional.of(new Person(""));
                        expectedSalutation = "you";
                    }}},
                    {new Fixtr("It should yield the default text with generic addressing for a known person with an addressText containing only whitespace") {{
                        maybePerson = Optional.of(new Person(" "));
                        expectedSalutation = "you";
                    }}},
                    {new Fixtr("It should yield the default text with a trimmed generic addressing for a known person with padded addressText") {{
                        maybePerson = Optional.of(new Person(" Joni "));
                        expectedSalutation = "Joni";
                    }}}
            });
        }

        Irritat0rSalutationImpl service;

        @Before
        public void setUp() {
            service = new Irritat0rSalutationImpl();
        }

        @Test
        public void test_getSalutation() throws Exception {
            String actual = service.getSalutation(fixture.maybePerson);
            assertEquals(
                    fixture.message,
                    fixture.expectedSalutation,
                    actual);
        }


        public static class Fixtr {
            Fixtr(final String message) {
                this.message = message;
            }

            Optional<Person> maybePerson;
            String expectedSalutation;
            String message;
        }

        private Fixtr fixture;

        public ParamTest(final Fixtr fixture) {
            this.fixture = fixture;
        }
    }


}
