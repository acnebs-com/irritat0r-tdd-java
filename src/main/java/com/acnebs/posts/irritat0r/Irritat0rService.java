package com.acnebs.posts.irritat0r;


import java.util.Optional;

class Irritat0rService {
    private final Irritat0rMessagePool messagePool;
    private static final String TEMPLATE = "Hey %s, did you know that %s?";

    public Irritat0rService(final Irritat0rMessagePool messagePool) {
        this.messagePool = messagePool;
    }

    public String getText(final Optional<String> maybeSalutation) {
        return String.format(
                TEMPLATE,
                convertSalutation(maybeSalutation),
                messagePool.getMessage()
        );
    }

    String convertSalutation(Optional<String> maybeSalutation) {
        final String fallback = "you";

        String salutation = maybeSalutation.orElse(fallback);
        salutation = salutation.trim();
        if (salutation.length() < 1) {
            salutation = fallback;
        }

        return salutation;
    }
}
