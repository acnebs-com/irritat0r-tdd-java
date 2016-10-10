package com.acnebs.posts.irritat0r.servlet;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import javax.servlet.ServletOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Test WebControllerTest.
 * <p>
 * Created by andreas.czakaj on 07.10.2016
 *
 * @author andreas.czakaj
 */
@RunWith(Enclosed.class)
public class WebControllerTest {
    public static class GeneralTest {

        WebController webController;
        Irritat0rHttpSessionAdapter sessionAdapter;

        @Before
        public void setUp() throws Exception {
            webController = new WebController((maybeUser) -> "abc: " + maybeUser.orElse(""));
            sessionAdapter = new Irritat0rHttpSessionAdapter(new HttpSessionStub());
            sessionAdapter.setPrincipalId("123");
        }


        @Test
        public void test_getText() throws Exception {
            String text = webController.getText(sessionAdapter);
            assertEquals("abc: 123", text);
        }

        @Test
        public void test_println_ok() throws Exception {
            final HttpServletResponseStub response = new HttpServletResponseStub();
            final List<Exception> errors = new ArrayList<>();
            Irritat0rHttpResponseAdapter responseAdapter = new Irritat0rHttpResponseAdapter(response, errors::add);

            webController.println(sessionAdapter, responseAdapter);
            assertEquals("There should be no errors", 0, errors.size());
            assertEquals("abc: 123\r\n", response.getOutputAsString());
        }

        @Test
        public void test_println_error() throws Exception {
            final HttpServletResponseStub response = new HttpServletResponseStub() {
                @Override
                public ServletOutputStream getOutputStream() throws IOException {
                    throw new IOException("oops");
                }
            };
            final List<Exception> errors = new ArrayList<>();
            Irritat0rHttpResponseAdapter responseAdapter = new Irritat0rHttpResponseAdapter(response, errors::add);

            webController.println(sessionAdapter, responseAdapter);
            assertEquals("There should be 1 error", 1, errors.size());
            assertEquals("oops", errors.iterator().next().getMessage());
            assertEquals("", response.getOutputAsString());
        }

        @Test
        public void test_queueValue_ok() throws Exception {
            final HttpServletRequestStub request = new HttpServletRequestStub();
            Irritat0rHttpRequestAdapter requestAdapter = new Irritat0rHttpRequestAdapter(request);

            assertFalse("Ensure there are no attributes yet", request.getAttributeNames().hasMoreElements());
            webController.queueValue(sessionAdapter, requestAdapter);

            assertTrue("It should contain an attribute", request.getAttributeNames().hasMoreElements());
            final String attrName = request.getAttributeNames().nextElement();
            assertEquals(Irritat0rHttpRequestAdapter.KEY_TEXT, attrName);
            assertEquals(
                    "It should have queued the value into request attributes for use in e.g. JSP.",
                    "abc: 123", request.getAttribute(attrName));
        }
    }
}
