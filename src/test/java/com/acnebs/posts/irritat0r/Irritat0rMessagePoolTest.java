package com.acnebs.posts.irritat0r;

import org.junit.Test;

import java.util.*;
import java.util.function.Supplier;

import static org.junit.Assert.*;

public class Irritat0rMessagePoolTest {
    
    @Test
    public void test_getMessage_emptyPool() throws Exception {
        Irritat0rMessagePool pool = new Irritat0rMessagePool();
        assertEquals(
                "It should yield the default message if pool is empty",
                "1+1=2",
                pool.getMessage());
    }

    @Test
    public void test_getMessage_poolWith1Message() throws Exception {
        Irritat0rMessagePool pool = new Irritat0rMessagePool("message 1");
        assertEquals(
                "If pool contains 1 message then each call should yield that message.",
                "message 1",
                pool.getMessage());
    }

    @Test
    public void test_getMessage_poolWith2Messages() throws Exception {
        Irritat0rMessagePool pool = new Irritat0rMessagePool(
                "message 1", "message 2"
        );

        final int times = 10000;
        final Map<String, Integer> messages = getMessageNtimes(pool, times);

        assertMessageDistribution(() -> messages.get("message 1"), times, 2);
        assertMessageDistribution(() -> messages.get("message 2"), times, 2);

        assertFalse(
                "It should not yield the default message " +
                        "if 'real' messages exist in the pool",
                messages.containsKey("1+1=2")
        );
    }

    private Map<String, Integer> getMessageNtimes(final Irritat0rMessagePool pool, final int times) {
        final Map<String, Integer> messages = new HashMap<>();
        for (int i = 0; i < times; i++) {
            final String actual = pool.getMessage();
            final Optional<Integer> count = Optional.ofNullable(messages.get(actual));
            messages.put(actual, count.orElse(0) + 1);
        }
        return messages;
    }

    private void assertMessageDistribution(final Supplier<Integer> messageCountSupplier,
                                           final int times,
                                           final int numberOfVariants) {
        final double allowedVariation = .10;
        assertEquals(
                "It should yield message about half of the times " +
                        "(with 10% variation).",
                times / numberOfVariants,
                (double)messageCountSupplier.get(),
                times * allowedVariation
        );
    }
}
