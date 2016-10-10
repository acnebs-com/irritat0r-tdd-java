package com.acnebs.posts.irritat0r.servlet;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import javax.servlet.http.HttpSession;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Test Irritat0rHttpSessionAdapter.
 * <p>
 * Created by andreas.czakaj on 07.10.2016
 *
 * @author andreas.czakaj
 */
@RunWith(Enclosed.class)
public class Irritat0rHttpSessionAdapterTest {
    public static class GeneralTest {
        HttpSession httpSession;
        Irritat0rHttpSessionAdapter adapter;

        @Before
        public void setUp() throws Exception {
            httpSession = new HttpSessionStub();
            adapter = new Irritat0rHttpSessionAdapter(httpSession);
        }

        @Test
        public void test_getPrincipalId_ok() throws Exception {
            adapter.setPrincipalId("abc");

            assertEquals(
                    "It should yield the id that is set in the session",
                    "abc", adapter.getPrincipalId());
        }

        @Test
        public void test_getPrincipalId_none() throws Exception {
            assertNull(
                    "It should yield null because no id is set in the session",
                    adapter.getPrincipalId());
        }
    }
}
