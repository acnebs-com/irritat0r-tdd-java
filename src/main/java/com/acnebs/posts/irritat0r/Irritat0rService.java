package com.acnebs.posts.irritat0r;


import java.util.Optional;

class Irritat0rService {

    private static final String TEMPLATE = "Hey %s, did you know that %s?";

    private static final String FALLBACK_SALUTATION = "you";

    private final Irritat0rMessagePool messagePool;


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
        final String salutation = maybeSalutation.orElse(FALLBACK_SALUTATION).trim();
        return salutation.length() < 1 ? FALLBACK_SALUTATION : salutation;
    }
}
