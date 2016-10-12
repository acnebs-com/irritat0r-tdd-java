package com.acnebs.posts.irritat0r;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class Irritat0rMessagePoolTest {
    @Test
    public void test_getMessage() throws Exception {
        Irritat0rMessagePool pool = new Irritat0rMessagePool("mydefault");
        assertEquals("mydefault", pool.getMessage());
    }

    @Test
    public void test_getMessage_fromPoolOf2() throws Exception {
        final String fallbackMessage = "mydefault";

        Irritat0rMessagePool pool = new Irritat0rMessagePool(
                fallbackMessage,
                "abc", "def"
        );

        final int times = 10000;
        final Map<String, Integer> messages = getMessageNtimes(pool, times);

        assertMessageDistribution(() -> messages.get("abc"), times, 2);
        assertMessageDistribution(() -> messages.get("def"), times, 2);

        assertFalse(
                "It should not yield the fallback message if 'real' messages exist in the pool",
                messages.containsKey(fallbackMessage)
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
        assertEquals(
                "It should yield message about half of the times (with 5% variation).",
                times / numberOfVariants,
                (double)messageCountSupplier.get(),
                times * .05
        );
    }
}
