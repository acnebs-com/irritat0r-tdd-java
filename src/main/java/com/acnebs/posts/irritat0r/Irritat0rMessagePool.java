package com.acnebs.posts.irritat0r;

import java.security.SecureRandom;
import java.util.*;

class Irritat0rMessagePool {

    private static final Random RANDOM = new SecureRandom();

    private final String defaultMessage;

    private final List<String> messages;

    public Irritat0rMessagePool(final String... messages) {
        defaultMessage = "1+1=2";

        this.messages = new ArrayList<>(
                Arrays.asList(messages)
        );
    }

    public String getMessage() {
        if (messages.size() < 1) {
            return defaultMessage;
        } else {
            final int ix = RANDOM.nextInt(messages.size());
            return messages.get(ix);
        }
    }
}
