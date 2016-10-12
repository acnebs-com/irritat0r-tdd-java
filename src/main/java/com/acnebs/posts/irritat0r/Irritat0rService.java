package com.acnebs.posts.irritat0r;


import java.util.Optional;

class Irritat0rService {
    private final Irritat0rMessagePool messagePool;
    private static final String TEMPLATE = "Hey %s, did you know that %s?";
    private static final String FALLBACK_SALUTATION = "you";

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
        String salutation = maybeSalutation.orElse(FALLBACK_SALUTATION);
        salutation = salutation.trim();

        if (salutation.length() < 1) {
            salutation = FALLBACK_SALUTATION;
        }

        return salutation;
    }
}
