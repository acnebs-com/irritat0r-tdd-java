package com.acnebs.posts.irritat0r.servlet;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Test Endpoint.
 * <p>
 * Created by andreas.czakaj on 08.10.2016
 *
 * @author andreas.czakaj
 */
@RunWith(Enclosed.class)
public class EndpointTest {
    public static class GeneralTest {
        @Test
        @Ignore
        public void test_servlet_sourceforge() throws Exception {
            try (final WebClient webClient = new WebClient()) {
                final HtmlPage page = webClient.getPage("http://htmlunit.sourceforge.net");
                assertEquals("HtmlUnit \u2013 Welcome to HtmlUnit", page.getTitleText());

                final String pageAsXml = page.asXml();
                assertTrue(pageAsXml.contains("<body class=\"composite\">"));

                final String pageAsText = page.asText();
                assertTrue(pageAsText.contains("Support for the HTTP and HTTPS protocols"));
            }
        }

        @Test

        public void test_servlet() throws Exception {
            new MyServer().start();

            try (final WebClient webClient = new WebClient()) {
                Page page = webClient.getPage("http://localhost:8080/inf0rm-me.html");

                assertEquals(
                        "Hey you, did you know that it is 8 times more likely to get killed by a pig than by a shark?\r\n",
                        page.getWebResponse().getContentAsString());
            }
        }
    }
}
