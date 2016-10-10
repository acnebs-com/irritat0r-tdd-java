package com.acnebs.posts.irritat0r.servlet;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class MyServerTest {

    @Before
    public void setUp() throws Exception {
        MyServer.setMyServerFactory(MyServer.createDefaultMyServerFactory());
        MyServer.setThrowingConsumer(MyServer.createDefaultThrowingConsumer());
        MyServer.setErrorConsumer(MyServer.createDefaultErrorConsumer());
    }


    @Test
    public void test_main_ok() throws Exception {
        final Boolean[] valueHolder = new Boolean[]{false};

        MyServer.setMyServerFactory(() ->
            new MyServer() {
                @Override
                public void start() throws Exception {
                    valueHolder[0] = true;
                }
            }
        );

        MyServer.main(new String[0]);

        assertTrue(
                "It should call the start method",
                valueHolder[0]);
    }

    @Test
    public void test_main_error() throws Exception {
        final Boolean[] valueHolder = new Boolean[]{false};

        MyServer.setMyServerFactory(() ->
                        new MyServer() {
                            @Override
                            public void start() throws Exception {
                                valueHolder[0] = true;
                                throw new Exception("Oops");
                            }
                        }
        );

        try {
            MyServer.main(new String[0]);
            fail("It should have failed with a RTE");
        } catch (RuntimeException e) {
            assertTrue(
                    "It should call the start method",
                    valueHolder[0]);
            assertEquals("main: Exception e", e.getMessage());
            assertEquals("Oops", e.getCause().getMessage());
        }
    }

    @Test
    public synchronized void test_jetty_error() throws Exception {
        final Map<String, Throwable> errors = new HashMap<>();
        synchronized (errors) {
            MyServer.setThrowingConsumer(server -> {
                throw new Exception("Oops");
            });
            MyServer.setErrorConsumer(
                    (error, message) -> {
                        synchronized (errors) {
                            errors.put(message, error);
                            errors.notify();
                        }
                    }
            );
            assertEquals("Ensure there are no errors yet", 0, errors.size());
            MyServer.main(new String[0]);

            errors.wait();
        }

        assertEquals("It should contain 1 error", 1, errors.size());
        final Throwable throwable = errors.get("Exception when starting jetty");
        assertNotNull("It should yield the right message", throwable);
        assertEquals("Oops", throwable.getMessage());
    }
}
