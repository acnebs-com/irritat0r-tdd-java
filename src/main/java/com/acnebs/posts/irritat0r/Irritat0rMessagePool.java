package com.acnebs.posts.irritat0r;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Irritat0rMessagePool {
    private final String fallbackMessage;
    private final List<String> messages;

    private static final SecureRandom RANDOM = new SecureRandom();

    public Irritat0rMessagePool(final String fallbackMessage,
                                final String... messages) {
        this.fallbackMessage = fallbackMessage;
        this.messages = new ArrayList<>(
                Arrays.asList(messages)
        );
    }

    public String getMessage() {
        final int size = messages.size();

        if (size > 0) {
            final int ix = RANDOM.nextInt(size);
            return messages.get(ix);
        } else {
            return fallbackMessage;
        }
    }
}
