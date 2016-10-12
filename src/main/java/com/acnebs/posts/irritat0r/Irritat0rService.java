package com.acnebs.posts.irritat0r;


import java.util.Optional;

class Irritat0rService {
    public String getText(final Optional<String> maybeSalutation) {
        final String template = "Hey %s, did you know that 1+1=2?";
        return String.format(template, convertSalutation(maybeSalutation));
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
