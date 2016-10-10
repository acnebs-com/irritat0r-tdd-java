package com.acnebs.posts.irritat0r.output;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import java.io.ByteArrayOutputStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Test Irritat0rJsonOutputImplTest.
 * <p>
 * Created by andreas.czakaj on 09.10.2016
 *
 * @author andreas.czakaj
 */
@RunWith(Enclosed.class)
public class Irritat0rJsonOutputImplTest {
    public static class GeneralTest {

        Irritat0rJsonOutputImpl jsonOutput;

        @Before
        public void setUp() throws Exception {
            jsonOutput = new Irritat0rJsonOutputImpl();
        }


        @Test
        public void test_getJson_ok() throws Exception {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            jsonOutput.toJson("Hey ho, let's go", baos);
            String actual = baos.toString("UTF-8");
            assertEquals(
                    "{\"text\":\"Hey ho, let's go\"}",
                    actual
            );
        }

        @Test
        public void test_getJson_null_text() throws Exception {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            jsonOutput.toJson(null, baos);
            String actual = baos.toString("UTF-8");
            assertEquals(
                    "{\"text\":null}",
                    actual
            );
        }

        @Test
        public void test_getJson_error() throws Exception {
            try {
                jsonOutput.toJson("abc", null);
                fail("It should have failed with a RTE/NPE");
            } catch (RuntimeException e) {
                assertEquals("toJson: Exception", e.getMessage());
                assertEquals(NullPointerException.class, e.getCause().getClass());
            }
        }
    }
}
